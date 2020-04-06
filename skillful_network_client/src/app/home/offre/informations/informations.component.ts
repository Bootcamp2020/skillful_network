import {Component, Input, OnInit} from '@angular/core';
import {JobDetails, Trainings} from '../offre';
import {MOCK_OFFRE} from '../offre.mock';
import {ApiHelperService} from '../../../shared/services/api-helper.service';
import {CandidatureService} from '../../../shared/services/candidature.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-informations',
  templateUrl: './informations.component.html',
  styleUrls: ['./informations.component.scss']
})
export class InformationsComponent implements OnInit {
    @Input() post;
    @Input() choixListe;

    constructor(private api: ApiHelperService, private route: ActivatedRoute) { }
    ngOnInit(): void {
  }
}
