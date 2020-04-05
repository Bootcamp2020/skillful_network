import { Component, OnInit, Input } from '@angular/core';
import { Simulation } from 'src/app/shared/models/simulation';

@Component({
  selector: 'app-sim-question-list',
  templateUrl: './sim-question-list.component.html',
  styleUrls: ['./sim-question-list.component.scss']
})
export class SimQuestionListComponent implements OnInit {

  public componentName: String = "app-sim-question-list";
  @Input() public simulation: Simulation;
  public isLoaded: boolean = false;

  constructor() { }

  ngOnInit(): void {
    this.simulationInfo();
  }

  ngOnChanges() {
    if (this.simulationInfo()) {
      if (this.simulation.trainingsSuggested == null) {
        console.log(">>> " + this.componentName + ".trainingSuggested is EMPTY");
      }
    }
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