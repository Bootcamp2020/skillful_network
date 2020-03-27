import { MOCK_CANDIDATURE } from './../../shared/models/mock.candidature';
import { MOCK_JOBS, IJobOffer } from 'src/app/shared/models/mock.job-offers';
import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import { CandidatureService } from 'src/app/shared/services/candidature.service';
import { IPost } from 'src/app/shared/models/mock.candidature';


// export interface PeriodicElement {
//   id: number;
//   nom: string;
//   entreprise: string;
//   type: string;
//   commence: string;
//   mise_en_ligne: string;
// }
const ELEMENT_DATA: IJobOffer[] =MOCK_JOBS;
// const ELEMENT_DATA: PeriodicElement[] = [
//   {id: 1, nom: 'Nom1', entreprise: 'Hydrogen1', type: 'Type1', commence: '15/04/2020', mise_en_ligne: '21/03/2020'},
//   {id: 2, nom: 'Nom2', entreprise: 'Helium1', type: 'Type2', commence: '18/05/2020', mise_en_ligne: '22/03/2020'},
//   {id: 3, nom: 'Nom3', entreprise: 'Lithium1', type: 'Type3', commence: '17/06/2020', mise_en_ligne: '23/03/2020'},
//   {id: 4, nom: 'Nom4', entreprise: 'Beryllium1', type: 'Type1', commence: '19/04/2020', mise_en_ligne: '20/03/2020'},
//   {id: 5, nom: 'Nom5', entreprise: 'Hydrogen2', type: 'Type2', commence: '25/04/2020', mise_en_ligne: '22/03/2020'},
//   {id: 6, nom: 'Nom6', entreprise: 'Helium2', type: 'Type2', commence: '01/06/2020', mise_en_ligne: '21/03/2020'},
//   {id: 7, nom: 'Nom7', entreprise: 'Lithium2', type: 'Type1', commence: '05/07/2020', mise_en_ligne: '19/03/2020'},
//   {id: 8, nom: 'Nom8', entreprise: 'Beryllium2', type: 'Type1', commence: '12/04/2020', mise_en_ligne: '20/03/2020'},
//   {id: 9, nom: 'Nom9', entreprise: 'Hydrogen3', type: 'Type3', commence: '17/05/2020', mise_en_ligne: '23/03/2020'},
//   {id: 10, nom: 'Nom10', entreprise: 'Helium3', type: 'Type2', commence: '15/04/2020', mise_en_ligne: '20/03/2020'},
//   {id: 11, nom: 'Nom11', entreprise: 'Lithium3', type: 'Type3', commence: '18/04/2020', mise_en_ligne: '19/03/2020'},
//   {id: 12, nom: 'Nom12', entreprise: 'Beryllium3', type: 'Type2', commence: '24/03/2020', mise_en_ligne: '21/03/2020'},
// ];


@Component({
  selector: 'app-job-offer-list',
  templateUrl: './job-offer-list.component.html',
  styleUrls: ['./job-offer-list.component.scss']
})
export class JobOfferListComponent implements OnInit {
  displayedColumns: string[] = ['nom', 'entreprise', 'type', 'commence', 'mise_en_ligne','statut', 'plus_info'];
  dataSource = new MatTableDataSource(ELEMENT_DATA);
  public listCandidature: IPost[] = MOCK_CANDIDATURE;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(public cs: CandidatureService) { }

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

}
