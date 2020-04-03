import { Component, OnInit } from '@angular/core';
import {ExerciceSetService} from '../../shared/services/exerciceSet.service';

@Component({
  selector: 'app-simulation-start',
  templateUrl: './simulation-start.component.html',
  styleUrls: ['./simulation-start.component.scss']
})

export class SimulationStartComponent implements OnInit {
//  Test avec MOCK !!
  public data = this.sim.findAllMock();

  constructor(public sim: ExerciceSetService) {
  }

  ngOnInit(): void {
  }
}
