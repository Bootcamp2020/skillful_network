import { Component, OnInit, Input } from '@angular/core';
import { MOCK_POSTS } from './post.mock';
import {Post} from './post';
@Component({
  selector: 'app-fil-actualites',
  templateUrl: './fil-actualites.component.html',
  styleUrls: ['./fil-actualites.component.scss']
})
export class FilActualitesComponent implements OnInit {
  @Input() public actu: string;
  @Input() public lien: string;
  public listPosts: Post[];

  constructor() { }

  ngOnInit(): void {
    this.listPosts = [];

    MOCK_POSTS.forEach((post) => {
      this.listPosts.push(new Post(post));
    });
  }

}
