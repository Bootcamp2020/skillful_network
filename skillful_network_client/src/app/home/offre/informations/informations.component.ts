import {Component, Input, OnInit} from '@angular/core';
import {Post} from '../offre';
import {MOCK_OFFRE} from '../offre.mock';

@Component({
  selector: 'app-informations',
  templateUrl: './informations.component.html',
  styleUrls: ['./informations.component.scss']
})
export class InformationsComponent implements OnInit {
  @Input() public entreprise: string;
  @Input() public description: string;
  @Input() public motsCles: string;
  @Input() public post : Post;

  constructor() { }

  ngOnInit(): void {
  }

}
