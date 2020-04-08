import { Component, OnInit, Input } from '@angular/core';
import { Simulation } from 'src/app/shared/models/simulation';

@Component({
  selector: 'app-sim-syn-info',
  templateUrl: './sim-syn-info.component.html',
  styleUrls: ['./sim-syn-info.component.scss']
})
export class SimSynInfoComponent implements OnInit {

  public componentName: String = "app-sim-syn-info";
  @Input() public simulation: Simulation;
  public isLoaded: boolean = false;
​
  constructor() { }
​
  ngOnInit(): void {
   this.simulationInfo();
  }
​
  ngOnChanges() {
   this.simulationInfo();
  }
​
  public simulationInfo(): boolean {
  this.isLoaded = false;
   let info: String = ">>> " + this.componentName + ": simulation" ;
   if (this.simulation == null || this.simulation.id == -1 ) {
     this.simulation = null;
     info = info + " is undefined yet";
   } else {
     this.isLoaded = true;
     info = info + " updated ! its id: " + this.simulation.id;
    }
   console.log(info)
   return this.isLoaded;
  }
}

​
// async function wait (delay) {
//   await new Promise(resolve => setTimeout(resolve, delay));
// }
