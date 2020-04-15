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


    constructor(private api: ApiHelperService, private route: ActivatedRoute) { }
    @Input() post;
    @Input() choixListe;
    myMap = {
        MODERATE: 'Modéré',
        CRITICAL: 'Critique',
        SIMPLE: 'Simple'
    };
    myMap2 = {
        MODERATE: 'Modéré',
        CRITICAL: 'Critique',
        SIMPLE: 'Simple'
    };
    ngOnInit(): void {
        console.log(this.post);
    }
}
