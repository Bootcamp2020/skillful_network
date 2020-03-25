import {Component, Input, OnInit} from '@angular/core';
import {Post} from '../offre';
import {MOCK_OFFRE} from '../offre.mock';

@Component({
  selector: 'app-exigences',
  templateUrl: './exigences.component.html',
  styleUrls: ['./exigences.component.scss']
})
export class ExigencesComponent implements OnInit {
  @Input() public niveau: string;
  @Input() public environement: string;
  public listPosts: Post[];
  constructor() { }

  ngOnInit(): void {
    this.listPosts = [];

    MOCK_OFFRE.forEach((post) => {
      this.listPosts.push(new Post(post));
    });
  }
}
