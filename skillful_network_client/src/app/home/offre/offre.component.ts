import {Component, Input, OnInit} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {Post} from './offre';
import {MOCK_OFFRE} from './offre.mock';
import { CandidatureService } from '../../shared/services/candidature.service';
import { IPost, MOCK_CANDIDATURE } from '../../shared/models/mock.candidature';

@Component({
  selector: 'app-offre',
  templateUrl: './offre.component.html',
  styleUrls: ['./offre.component.scss']
})
export class OffreComponent implements OnInit {
  @Input() public status: string;
  @Input() public titreOffre: string;
  public jobOfferId : number;
  public post: Post;
  public listCandidature: IPost[] = MOCK_CANDIDATURE;
  constructor(private route: ActivatedRoute, public cs: CandidatureService) { }

  ngOnInit(): void {
    const id = this.route.snapshot.params.id;
    this.jobOfferId = Number(id);
    this.post = new Post(MOCK_OFFRE);
  }

}
