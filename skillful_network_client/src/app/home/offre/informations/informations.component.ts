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
  public listPosts: Post[];
  constructor() { }

  ngOnInit(): void {
    this.listPosts = [];

    MOCK_OFFRE.forEach((post) => {
      this.listPosts.push(new Post(post));
    });
  }

}
