import { Component, OnInit, Input } from '@angular/core';
import { MOCK_CANDIDATURE, IPost } from 'src/app/shared/models/mock.candidature';
import { Candidature } from 'src/app/shared/models/candidature';


@Component({
  selector: 'app-candidature',
  templateUrl: './candidature.component.html',
  styleUrls: ['./candidature.component.scss']
})


export class CandidatureComponent implements OnInit {
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


