import { Component, OnInit, Input } from '@angular/core';
import { Simulation } from 'src/app/shared/models/simulation';

@Component({
  selector: "app-sim-syn-info",
  templateUrl: "./sim-syn-info.component.html",
  styleUrls: ["./sim-syn-info.component.scss"],
})

export class SimSynInfoComponent implements OnInit {

  public componentName: String = "app-sim-syn-info";
  @Input() public simulation: Simulation;
  @Input() public isLoaded: boolean = false;
  
  constructor() {}

  ngOnInit(): void {
    // console.log(">>> " + this.componentName + ": ngOnInit()");
    this.simulationInfo();
  }
  
  ngOnChanges() {
    // console.log(">>> " + this.componentName + ": ngOnChanges()");
    this.simulationInfo();
  }
  
  public simulationInfo(): void {
    let info: String = ">>>> " + this.componentName + ": simulation";
    if ( ! this.isLoaded) {
      info = info + " is undefined yet";
    } else {
      if (this.simulation == null) {
        info = info + " is not found!";
      } else {
        info = info + " is loaded! its id: " + this.simulation.id;
      }
    }
    console.log(info);
  }
}

​
// async function wait (delay) {
//   await new Promise(resolve => setTimeout(resolve, delay));
// }
