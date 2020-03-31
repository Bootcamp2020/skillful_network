import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {TrainingService} from '../../shared/services/training.service'

@Component({
  selector: 'app-formation-list',
  templateUrl: './formation-list.component.html',
  styleUrls: ['./formation-list.component.scss']
})

​export class FormationListComponent implements OnInit {
  displayedColumns: string[] = ['name', 'organisation', 'description', 'financer', 'dateBeg', 'dateEnd', 'durationHours', 'plus_info'];
// Affichage des données à l'aide du  service depuis un MOCK, cela pour test sans Backend
  dataSource = new MatTableDataSource(this.data.findAllMock());
// Affichage des données à l'aide du  service depuis le Backend
//  dataSource = new MatTableDataSource(this.data.findAll());
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(public data: TrainingService) { }

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }
}


​

