import {Component, Input, OnInit} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {Post} from './simulation-synthesis';
import {MOCK_SIMULATION_SYNTHESIS} from './simulation-synthesis.mock';

@Component({
  selector: 'app-simulation-synthesis',
  templateUrl: './simulation-synthesis.component.html',
  styleUrls: ['./simulation-synthesis.component.scss']
})
export class SimulationSynthesisComponent implements OnInit {

  @Input() public status: string;
  @Input() public titreSimulation: string;
  public simulationId : number;
  public post: Post;
  
  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = this.route.snapshot.params.id;
    this.simulationId = Number(id);
    this.post = new Post(MOCK_SIMULATION_SYNTHESIS);
  }

}

