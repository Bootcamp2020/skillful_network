import {Component, Input, OnInit} from '@angular/core';
import {Post} from '../offre';
import {MOCK_OFFRE} from '../offre.mock';

@Component({
  selector: 'app-formations-associees',
  templateUrl: './formations-associees.component.html',
  styleUrls: ['./formations-associees.component.scss']
})
export class FormationsAssocieesComponent implements OnInit {
  @Input() public nomFormation: string;
  @Input() public presRequis: string;
  @Input() public competence: string;
  @Input() public duree: number;
  @Input() public post : Post;

  constructor() { }

  ngOnInit(): void {

  }

}
