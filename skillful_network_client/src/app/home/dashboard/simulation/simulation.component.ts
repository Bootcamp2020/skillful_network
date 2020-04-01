import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MOCK_SIMULATION, IPost } from 'src/app/shared/models/mock.simulation';
import { Simulation } from 'src/app/shared/models/simulation';
import {MatTableDataSource} from '@angular/material/table';
import {MatSort} from '@angular/material/sort';

const ELEMENT_DATA: IPost[] =MOCK_SIMULATION;

@Component({
  selector: 'app-simulation',
  templateUrl: './simulation.component.html',
  styleUrls: ['./simulation.component.scss']
})
export class SimulationComponent implements OnInit {
  displayedColumns: string[] = ['nbexercice', 'date', 'jobgoal', 'simdetail', 'reloadsim'];
  dataSource = new MatTableDataSource(ELEMENT_DATA);
  public listSimulation: Simulation[];
  @Input() public nbexercice: number;
  @Input() public date: Date;
  @Input() public caeerGoal: String;
  @Input() public details: String;
  @Input() public reloadSim: String;

  
  @ViewChild(MatSort, {static: true}) sort: MatSort;
  
  constructor() {
  }

  ngOnInit(): void {
    this.listSimulation = [];
    MOCK_SIMULATION.forEach((simulation: IPost) => {
      this.listSimulation.push(new Simulation(simulation));    
      this.dataSource.sort = this.sort;
    });  
  }   
}
