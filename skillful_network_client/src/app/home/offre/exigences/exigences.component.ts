import {Component, Input, OnInit} from '@angular/core';
import {JobDetails, Trainings} from '../offre';
import {ApiHelperService} from '../../../shared/services/api-helper.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-exigences',
  templateUrl: './exigences.component.html',
  styleUrls: ['./exigences.component.scss']
})
export class ExigencesComponent implements OnInit {
    @Input() post;
    constructor(private api: ApiHelperService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    }
}
