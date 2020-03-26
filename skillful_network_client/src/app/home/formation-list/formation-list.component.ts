import {Component, OnInit, ViewChild} from '@angular/core';

import {MatPaginator} from '@angular/material/paginator';

import {MatSort} from '@angular/material/sort';

import {MatTableDataSource} from '@angular/material/table';

​

​

​

export interface PeriodicElement {

  id: number;

  nom: string;

  organisme: string;

  commence: Date;

  mise_en_ligne:Date;

}

​

const ELEMENT_DATA: PeriodicElement[] = [

  {id: 1, nom: 'Nom1', organisme: 'Hydrogen1', commence: new Date(2020,4,20), mise_en_ligne:new Date(2020,2,17)},

  {id: 2, nom: 'Nom2', organisme: 'Helium1', commence: new Date(2020,6,25), mise_en_ligne:new Date(2022,6,23)},

  {id: 3, nom: 'Nom3', organisme: 'Lithium1', commence: new Date(2020,2,12), mise_en_ligne:new Date(2022,7,23)},

  {id: 4, nom: 'Nom4', organisme: 'Beryllium1', commence: new Date(2020,9,20), mise_en_ligne:new Date(2022,9,23)},

  {id: 5, nom: 'Nom5', organisme: 'Hydrogen2', commence: new Date(2021,2,20), mise_en_ligne:new Date(2022,6,23)},

  {id: 6, nom: 'Nom6', organisme: 'Helium2', commence: new Date(2022,4,20), mise_en_ligne:new Date(2022,6,23)},

  {id: 7, nom: 'Nom7', organisme: 'Lithium2', commence: new Date(2020,3,23), mise_en_ligne:new Date(2022,6,23)},

  {id: 8, nom: 'Nom8', organisme: 'Beryllium2', commence: new Date(2020,11,29), mise_en_ligne:new Date(2022,4,20)},

  {id: 9, nom: 'Nom9', organisme: 'Hydrogen3', commence: new Date(2020,12,25), mise_en_ligne:new Date(2022,4,20)},

  {id: 10, nom: 'Nom10', organisme: 'Helium3', commence: new Date(2022,6,23), mise_en_ligne:new Date(2022,4,20)},

  {id: 11, nom: 'Nom11', organisme: 'Lithium3', commence: new Date(2021,8,29), mise_en_ligne:new Date(2022,4,20)},

  {id: 12, nom: 'Nom12', organisme: 'Beryllium3', commence: new Date(2020,2,17), mise_en_ligne:new Date(2022,4,20)},

];

​

/** Constants used to fill up our data base. */

/**const COLORS: string[] = [

  'maroon', 'red', 'orange', 'yellow', 'olive', 'green', 'purple', 'fuchsia', 'lime', 'teal',

  'aqua', 'blue', 'navy', 'black', 'gray'

];

const NAMES: string[] = [

  'Maia', 'Asher', 'Olivia', 'Atticus', 'Amelia', 'Jack', 'Charlotte', 'Theodore', 'Isla', 'Oliver',

  'Isabella', 'Jasper', 'Cora', 'Levi', 'Violet', 'Arthur', 'Mia', 'Thomas', 'Elizabeth'

];*/

​

/**

 * @title Data table with sorting, pagination, and filtering.

 */

@Component({

  selector: 'app-formation-list',

  templateUrl: './formation-list.component.html',

  styleUrls: ['./formation-list.component.scss']

})

export class FormationListComponent implements OnInit {

  displayedColumns: string[] = ['nom', 'organisme', 'commence', 'mise_en_ligne', 'plus_info'];

  dataSource = new MatTableDataSource(ELEMENT_DATA);

​

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  @ViewChild(MatSort, {static: true}) sort: MatSort;

​

  constructor() {

    // Create 100 users

    //const users = Array.from({length: 100}, (_, k) => createNewUser(k + 1));

​

    // Assign the data to the data source for the table to render

    //this.dataSource = new MatTableDataSource(users);

  }

​

  ngOnInit() {

    this.dataSource.paginator = this.paginator;

    this.dataSource.sort = this.sort;

  }

​

  applyFilter(event: Event) {

    const filterValue = (event.target as HTMLInputElement).value;

    this.dataSource.filter = filterValue.trim().toLowerCase();

​

    if (this.dataSource.paginator) {

      this.dataSource.paginator.firstPage();

    }

  }

}
