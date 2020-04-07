import { Component, OnInit, Input, ViewChild } from "@angular/core";
import { Simulation } from "src/app/shared/models/simulation";
import { MatTableDataSource } from "@angular/material/table";
import { ApiHelperService } from "../../../shared/services/api-helper.service";
import { ActivatedRoute, Router } from "@angular/router";
import { MatSort } from "@angular/material/sort";
import { MatDialog } from "@angular/material/dialog";
import { GoalValidationModalComponent } from "./goal-validation-modal/goal-validation-modal.component";
import { UserService } from "src/app/shared/services/user.service";

//const ELEMENT_DATA: IPost[] = MOCK_SIMULATION;

@Component({
  selector: "app-simulation",
  templateUrl: "./simulation.component.html",
  styleUrls: ["./simulation.component.scss"],
})
export class SimulationComponent implements OnInit {
  public listSimulation: Simulation[];
  displayedColumns: string[] = [
    "jobgoal",
    "date",
    "synthesis",
    "simdetail",
    "reloadsim",
  ];
  public dataSource: MatTableDataSource<Simulation>;
  // @Input() public nbexercice: number;
  // @Input() public date: Date;
  // @Input() public careerGoal: String;
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
    if ( userId == null ) {
      if (this.userService.findAll.length == 0) {
        console.log(">> no users found");
      } else {
        console.log(">> some users are listed !");
        if (this.userService.userLogged == null || this.userService.userLogged == undefined) {
          console.log(">> no users logged");
        } else {
          this.careerGoal = this.userService.userLogged.careerGoal;
        }
      }
    }
    userId = userId == null ? 2 : userId;
    console.log(">> userId: " + userId);
    
    await this.api.get({ endpoint: `/usersbyId/${userId}` })
    .then((user) => {
      console.log("user found ! user.careerGoal: " + user.careerGoal);
      this.careerGoal = user.careerGoal;
    });
    
    //this.listSimulation = [];
    // MOCK_SIMULATION.forEach((simulation: IPost) => {
      //   this.listSimulation.push(new Simulation(simulation));
      //   this.dataSource.sort = this.sort;
      // });
    await this.api.get({ endpoint: `/simulations?userid=${userId}` })
    .then((data) => (this.listSimulation = data));

    this.dataSource = new MatTableDataSource(this.listSimulation);
    this.dataSource.sort = this.sort;

    console.log(this.listSimulation);
    console.log(this.dataSource);
    }

  openDialog(): void {
    const dialogRef = this.dialog.open(GoalValidationModalComponent, {
      //width: '600px',
      //data: {}
      data: { goal: this.careerGoal },
      // data: {goal: "Enculeur de poules"}
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log("result: " + result);
      if (result != null) {
        this.router.navigate(["/home/simulation-start"], {
          queryParams: { goal: result },
        });
      }
    });
  }
}
