import { Component, OnInit, Input } from '@angular/core';
import { MOCK_CANDIDATURE, IPost } from 'src/app/shared/models/mock.candidature';
import { Candidature } from 'src/app/shared/models/candidature';

@Component({
  selector: 'app-candidatures',
  templateUrl: './candidatures.component.html',
  styleUrls: ['./candidatures.component.scss']
})
export class CandidaturesComponent implements OnInit {


  @Input() public listCandidature: Candidature[];


  constructor() {

  }

  ngOnInit(): void {

  }
}
