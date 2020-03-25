import {Component, Input, OnInit} from '@angular/core';
import {Post} from '../offre';
import {MOCK_OFFRE} from '../offre.mock';

@Component({
  selector: 'app-candidater',
  templateUrl: './candidater.component.html',
  styleUrls: ['./candidater.component.scss']
})
export class CandidaterComponent implements OnInit {
  @Input() public nomFormation: string;
  @Input() public duree: number;
  public listPosts: Post[];
  agree: any;
  constructor() { }

  ngOnInit(): void {
    this.listPosts = [];

    MOCK_OFFRE.forEach((post) => {
      this.listPosts.push(new Post(post));
    });
  }

}
