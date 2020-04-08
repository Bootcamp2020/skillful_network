import { CandidatureService } from 'src/app/shared/services/candidature.service';
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



  constructor(public cs: CandidatureService) {

  }

  ngOnInit(): void {
    // import depuis le back-end
    this.getCandidatures();
  }

  async getCandidatures(){
    this.listCandidature = await this.cs.getAllUserApllications(1);
    console.log(this.listCandidature);

  }
 
   
}


