import { MOCK_CANDIDATURE } from './../../shared/models/mock.candidature';
import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import { CandidatureService } from 'src/app/shared/services/candidature.service';
import { IPost } from 'src/app/shared/models/mock.candidature';
import {JobOfferService} from '../../shared/services/job-offer.service';

@Component({
  selector: 'app-job-offer-list',
  templateUrl: './job-offer-list.component.html',
  styleUrls: ['./job-offer-list.component.scss']
})
export class JobOfferListComponent implements OnInit {
  displayedColumns: string[] = ['name', 'company', 'type', 'dateBeg', 'dateUpload', 'statut', 'plus_info'];
// Affichage des données à l'aide du  service depuis un MOCK, cela pour test sans Backend
  dataSource = new MatTableDataSource(this.data.findAllMock());
// Affichage des données à l'aide du  service depuis le Backend
//  dataSource = new MatTableDataSource(this.data.findAll());
  public listCandidature: IPost[] = MOCK_CANDIDATURE;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(public cs: CandidatureService, public data: JobOfferService) { }

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

}
