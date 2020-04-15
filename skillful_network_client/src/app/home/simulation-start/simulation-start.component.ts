import { Component, OnInit } from '@angular/core';
import {ExerciceSetService} from '../../shared/services/exerciceSet.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-simulation-start',
  templateUrl: './simulation-start.component.html',
  styleUrls: ['./simulation-start.component.scss']
})
export class SimulationStartComponent implements OnInit {
  //  Test avec MOCK !!
    //public data = this.sim.findAllMock();
    public goal: String;
    public data: any;
  
    constructor(public sim: ExerciceSetService, private route: ActivatedRoute) {
    }

    ngOnInit(): void {
      this.route.queryParams.subscribe(param => {
        console.log("param.goal: " + param.goal);
        if ( param.goal != null) {
          this.goal = param.goal
        }
        if (param.userId != null)
        {this.sim.userId= param.userId}
      }) ;

        // import du data depuis le back !!
        this.sim.findAll().then(res => {
           this.data = res;
            console.log(this.data);
        });
    }
  }
