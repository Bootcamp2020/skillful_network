import {Component, Input, OnInit} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../../shared/services/user.service';
import { Simulation } from 'src/app/shared/models/simulation';
import { ApiHelperService } from '../../shared/services/api-helper.service';
​
@Component({
  selector: 'app-simulation-synthesis',
  templateUrl: './simulation-synthesis.component.html',
  styleUrls: ['./simulation-synthesis.component.scss']
})
export class SimulationSynthesisComponent implements OnInit {
​
  public componentName: String = "app-simulation-synthesis";
  public simulation: Simulation;
  public isLoaded: boolean = false;
​
  constructor(private api: ApiHelperService, private userService: UserService, private route: ActivatedRoute) {
  }
​
  async ngOnInit() {
    // get simulatin Id from route parsing /home/simulation/{simID}
    const { simId } = this.route.snapshot.params;
    console.log(">> simId from route /home/simulation/{simId} : " + simId);
​
    await this.api.get({ endpoint: `/simulations/${simId}` })
      .then(data => {
        this.simulation = data;
        this.isLoaded = true;
      })
      .catch(() => {
        console.log(">>> cette simulation n'existe pas");
        return;
      });
​
      this.simulationInfo();
​
    // Get connected user from userService
    //if (this.userService.findAll.length != 0) {
     // console.log("some users are listed !");
       //if (this.userService.userLogged != undefined) {
    //      this.user = this.userService.userLogged;
    //    }
    // } else {
    //   console.log(">> no users found");
    // }
  }
​
   public simulationInfo(): boolean {
     this.isLoaded = false;
    // let info: String = this.route.component['name'] + ": simulation" ;
      let info: String = ">> " + this.componentName + ": simulation";
      if (this.simulation == null || this.simulation.id == -1) {
        this.simulation = null;
      //this.simulation = new Simulation(null);
        info = info + " is undefined yet";
      } else {
        this.isLoaded = true;
        info = info + " loaded ! its id: " + this.simulation.id;
    }
    console.log(info)
    return this.isLoaded;
  }
}
​
// async function wait (delay) {
//   await new Promise(resolve => setTimeout(resolve, delay));
// }