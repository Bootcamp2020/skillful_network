import { Component, OnInit, Input } from '@angular/core';
import { Simulation } from 'src/app/shared/models/simulation';

@Component({
  selector: 'app-sim-syn-question-list',
  templateUrl: './sim-syn-question-list.component.html',
  styleUrls: ['./sim-syn-question-list.component.scss']
})

export class SimSynQuestionListComponent implements OnInit {
  
  panelOpenState = false;
  public componentName: String = "app-sim-syn-question-list";
  @Input() public simulation: Simulation;
  @Input() public isLoaded: boolean;
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