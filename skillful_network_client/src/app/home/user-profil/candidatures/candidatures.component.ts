import { Component, OnInit, Input } from '@angular/core';
import { MOCK_CANDIDATURE, IPost } from 'src/app/shared/models/mock.candidature';
import { Candidature } from 'src/app/shared/models/candidature';

@Component({
  selector: 'app-candidatures',
  templateUrl: './candidatures.component.html',
  styleUrls: ['./candidatures.component.scss']
})
export class CandidaturesComponent implements OnInit {

  public listCandidature: Candidature[];
  @Input() public company: string;
  @Input() public status: String;
  @Input() public details: String;
  @Input() public job: String;



  constructor() {

  }

  ngOnInit(): void {
    this.listCandidature = [];
    MOCK_CANDIDATURE.forEach((condidature: IPost) => {
      this.listCandidature.push(new Candidature(condidature));
    });  
  }
}
