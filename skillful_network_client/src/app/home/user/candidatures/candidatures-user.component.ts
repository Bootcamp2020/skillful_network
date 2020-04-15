import { Component, OnInit, Input } from '@angular/core';
import { MOCK_CANDIDATURE, IPost } from 'src/app/shared/models/mock.candidature';
import { Candidature } from 'src/app/shared/models/candidature';

@Component({
  selector: 'app-candidatures-user',
  templateUrl: './candidatures-user.component.html',
  styleUrls: ['./candidatures-user.component.scss']
})
export class CandidaturesUserComponent implements OnInit {


  @Input() public listCandidature: Candidature[];


  constructor() {

  }

  ngOnInit(): void {

  }
}
