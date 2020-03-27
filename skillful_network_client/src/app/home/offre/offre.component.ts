import {Component, Input, OnInit} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {Post} from './offre';
import {MOCK_OFFRE} from './offre.mock';

@Component({
  selector: 'app-offre',
  templateUrl: './offre.component.html',
  styleUrls: ['./offre.component.scss']
})
export class OffreComponent implements OnInit {
  @Input() public status: string;
  @Input() public titreOffre: string;
  public listPosts: Post[];
  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = this.route.snapshot.params.id;
    console.log(id);
    this.listPosts = [];

    MOCK_OFFRE.forEach((post) => {
      this.listPosts.push(new Post(post));
    });
  }

}
