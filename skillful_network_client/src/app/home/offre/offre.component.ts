import {Component, Input, OnInit} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {JobDetails, Trainings} from './offre';
import {MOCK_OFFRE} from './offre.mock';
import { CandidatureService } from '../../shared/services/candidature.service';
import { IPost, MOCK_CANDIDATURE } from '../../shared/models/mock.candidature';
import {JobDetailsService} from '../../shared/services/job-details.service';
import {ApiHelperService} from '../../shared/services/api-helper.service';

@Component({
  selector: 'app-offre',
  templateUrl: './offre.component.html',
  styleUrls: ['./offre.component.scss']
})
export class OffreComponent implements OnInit {
  @Input() public status: string;
  @Input() public titreOffre: string;
  public jobOfferId: number;
  public choixListe: string;


  public trainings: Trainings;
  public jobDetails: JobDetails;
  listCandidature: IPost[];

  constructor(private api: ApiHelperService, public cs: CandidatureService, private route: ActivatedRoute) { }

  ngOnInit(): void {
      this.choixListe = this.route.snapshot.data.type;
      const {id} = this.route.snapshot.params;
      if (this.choixListe == 'emploi') {

          this.api.get({endpoint: `/offers/${id}`})
              .then(data => {
                  this.jobDetails = data;
                  console.log(this.jobDetails);
              })
              .catch((error) => {
                  console.log('cette offre n\'existe pas');
              });
      } else {
          this.api.get({endpoint: `/trainings/${id}`})
              .then(data => {
                  this.jobDetails = data;
              })
              .catch((error) => {
                  console.log('cette offre n\'existe pas');
              });
      }
  }

}
