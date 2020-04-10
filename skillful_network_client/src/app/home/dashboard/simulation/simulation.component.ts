import { Component, OnInit, Input, ViewChild } from "@angular/core";
import { Simulation } from "src/app/shared/models/simulation";
import { MatTableDataSource } from "@angular/material/table";
import { ApiHelperService } from "../../../shared/services/api-helper.service";
import { ActivatedRoute, Router } from "@angular/router";
import { MatSort } from "@angular/material/sort";
import { MatDialog } from "@angular/material/dialog";
import { GoalValidationModalComponent } from "./goal-validation-modal/goal-validation-modal.component";
import { UserService } from "src/app/shared/services/user.service";

@Component({
  selector: "app-simulation",
  templateUrl: "./simulation.component.html",
  styleUrls: ["./simulation.component.scss"],
})
export class SimulationComponent implements OnInit {
  public listSimulation: Simulation[];
  displayedColumns: string[] = [
    "jobaccess",
    "jobgoal",
    "date",
    "simdetail",
    "reloadsim",
  ];
  public dataSource: MatTableDataSource<Simulation>;
  public careerGoal: String;

  @ViewChild(MatSort, { static: true }) sort: MatSort;

  constructor(
    private api: ApiHelperService,
    public dialog: MatDialog,
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  async ngOnInit() {
    // Get connected user from route params
    let { userId } = this.route.snapshot.params;

    // Get connected user from userService if not provided
    if (userId == null) {
      console.log(">> user ID not provided.. trying to find active logged user...");
      if ( this.userService.userLogged == null 
        || this.userService.userLogged == undefined ) {
        // exit if no user available
        console.log(">> ERR: Active user not found.");
        return;
      } else {
        userId = this.userService.userLogged.id;
        this.careerGoal = this.userService.userLogged.careerGoal;
        console.log(">> Active user found !");
      }
    }
    if (userId == null) { return; } // exit if no user available
    userId = userId == null ? 2 : userId;
    console.log(">> userId: " + userId);

    // GET careerGoal if needed
    if (this.careerGoal == null) {
      await this.api.get({ endpoint: `users/usersbyId/${userId}` }).then((user) => {
        console.log(">> REST : Get user.careerGoal.");
        this.careerGoal = user.careerGoal;
      });
    }
    console.log(">> user.careerGoal: " + this.careerGoal);

    // GET simulations list of provided user
    await this.api
      .get({ endpoint: `/simulations?userid=${userId}` })
      .then((data) => (this.listSimulation = data));

    this.dataSource = new MatTableDataSource(this.listSimulation);
    this.dataSource.sort = this.sort;
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(GoalValidationModalComponent, {
      // data: {goal: "Enculeur de mouches"}
      data: { goal: this.careerGoal },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result != null) {
        console.log(">> goal: " + result);
        
        // update active user if provided goal from modal dialog differs from careerGoal
        if (result != this.careerGoal) {
          this.userService.userLogged.careerGoal = result;
          // Boom !
          this.userService.updateUser(this.userService.userLogged);
          console.log(">> active user updated with new careerGoal: " + this.userService.userLogged.careerGoal);
        }

        // navigate to simulation-start component to effectively launch the submitted goal
        this.router.navigate(["/home/simulation-start"], {
          queryParams: { goal: result },
        });
      }
    });
  }
}
