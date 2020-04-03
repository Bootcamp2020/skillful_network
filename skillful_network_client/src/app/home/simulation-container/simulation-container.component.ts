import { Component, OnInit } from '@angular/core';
import { ActivatedRoute} from '@angular/router';
import { User} from '../../shared/models/user';
import { UserService} from '../../shared/services/user.service';
import { ApiHelperService} from '../../shared/services/api-helper.service';
import { Simulation } from 'src/app/shared/models/simulation';

@Component({
  selector: 'app-simulation-container',
  templateUrl: './simulation-container.component.html',
  styleUrls: ['./simulation-container.component.scss']
})
export class SimulationContainerComponent implements OnInit {
  
  public user: User;
  public simulation: Simulation;

  constructor(private api: ApiHelperService, private userService: UserService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    const {simId} = this.route.snapshot.params;
    console.log(simId);

    this.api.get({endpoint: `/simulations/${simId}`})
    .then(data => {
        this.simulation = data;
    })
    .catch((error) => {
        console.log('cette simulation n\'existe pas');
    });
    console.log("simulation : " + this.simulation);
  }

}
