import { Component, OnInit, Input } from '@angular/core';
import { Simulation } from 'src/app/shared/models/simulation';

@Component({
  selector: 'app-sim-training-list',
  templateUrl: './sim-training-list.component.html',
  styleUrls: ['./sim-training-list.component.scss']
})
export class SimTrainingListComponent implements OnInit {

  public componentName: String = "app-sim-training-list";
  @Input() public simulation: Simulation;
  public isLoaded: boolean = false;

  constructor() { }

  ngOnInit(): void {
    this.simulationInfo();
  }

  ngOnChanges() {
    this.simulationInfo();
  }

  public simulationInfo(): boolean {
    this.isLoaded = false;
    // let info: String = this.route.component['name'] + ": simulation" ;
    let info: String = ">>> " + this.componentName + ": simulation" ;
    if (this.simulation == null || this.simulation.id == -1 ) {
      this.simulation = null;
      //this.simulation = new Simulation(null);
      info = info + " is undefined yet";
    } else {
      this.isLoaded = true;
      info = info + " updated ! its id: " + this.simulation.id;
    }
    console.log(info)
    return this.isLoaded;
  }
}

// async function wait (delay) {
//   await new Promise(resolve => setTimeout(resolve, delay));
// }