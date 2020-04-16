import { TrainingService } from './../../services/training.service';
import {Component, OnInit, ViewChild, Input, HostListener} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort, Sort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {PageEvent} from '@angular/material/paginator';
import { UserService } from '../../services/user.service';
import { CandidatureService } from '../../services/candidature.service';
import { JobOfferService } from '../../services/job-offer.service';
import { Training } from '../../models/training';
import {NgForm} from '@angular/forms';
import { JobOffer } from '../../models/job-offer';



@Component({
  selector: 'app-page-data',
  templateUrl: './page-data.component.html',
  styleUrls: ['./page-data.component.scss']
})
export class PageDataComponent implements OnInit {
  displayedTrainingColumns: string[] = ['name', 'organisation', 'description', 'financer', 'dateBeg', 'dateEnd', 'durationHours', 'plus_info'];
  displayedUserColumns: string[] = ['firstName', 'lastName', 'birthDate', 'plus_info'];
  displayedJobColumns: string[] = ['name', 'company', 'type', 'dateBeg', 'dateUpload', 'statut', 'plus_info'];
  // Affichage des données à l'aide du  service depuis un MOCK, cela pour test sans Backend
  //  dataSource = new MatTableDataSource(this.data.findAllMock());
  // Affichage des données à l'aide du  service depuis le Backend
  dataSource;
  displayedColumns: string[] = [];
     // MatPaginator Inputs
     length = 100;
     pageSize = 10;
     pageSizeOptions: number[] = [5, 10, 25, 100];
  
     //default page value
     pageIndex=1;
     pageRow=10;
     fieldName = 'name';
     sortOrder = 'ASCENDING';
   
     // MatPaginator Output
     pageEvent: PageEvent;

     keyEvent: boolean = false;
     keyword: string;
     form: NgForm;
     
    @Input() entityIn: String;
  
    @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
    @ViewChild(MatSort, {static: true}) sort: MatSort;


  constructor(private trainingService: TrainingService, 
    private userService: UserService, 
    public candidatureService: CandidatureService, 
    public jobOfferService: JobOfferService) { }
    

  ngOnInit(): void {
     const sortState: Sort = {active: 'name', direction: 'desc'};
     this.sort.active = sortState.active;
     this.sort.direction = sortState.direction;
     this.sort.sortChange.emit(sortState);

    this.dataHandler(this.entityIn);
  }

  
  paginatorHandler(event: PageEvent) {
    this.pageRow = event.pageSize;
    this.pageIndex = (event.pageIndex <= 0) ? 1 : event.pageIndex ; 
    this.dataHandler(this.entityIn);
  }

  sortHandler(){
    this.fieldName = this.sort.active;
    this.sortOrder = ("asc" === this.sort.direction)? 'ASCENDING' : 'DESCENDING';
    this.dataHandler(this.entityIn);

  }

  search(form: NgForm) {
    if (this.keyEvent) {
      this.keyword = form.value.keyword;
    }else{
      this.keyword = "";
    }
    return this.keyword;
  }
  
  @HostListener('document:keyup', ['$event']) handleKeyboardEvent(event: KeyboardEvent) {
    this.keyEvent = true;
  }

  dataHandler(entityIn:String){
    switch (entityIn) {
      case "training":
       this.onSearch (this.form);
       this.findformationByPage(this.pageIndex, this.pageRow, this.sortOrder, this.fieldName);
       this.displayedColumns = this.displayedTrainingColumns;
      break;
    
      case "job":
       this.searchByKeyword(this.form);
       //this.findJobByPage(this.pageIndex, this.pageRow, this.sortOrder, this.fieldName);
       this.displayedColumns = this.displayedJobColumns;
      break;

      case "user":
       this.findUserByPage(this.pageIndex, this.pageRow, this.sortOrder, this.fieldName);         
       this.displayedColumns = this.displayedUserColumns;
      break;

      default:

        break;
    }
 }

 findformationByPage(pageIndex: number, pageRow: number, sortOrder: string, fieldName: String){
    this.trainingService.findAllByPage(pageIndex, pageRow, sortOrder, fieldName).then(res => {
      this.dataSource = new MatTableDataSource<Training>(res.content);
      this.dataSource.sort = this.sort;
    })
  }

  findUserByPage(pageIndex: number, pageRow: number, sortOrder: string, fieldName: String){
    this.userService.findAllByPage(pageIndex, pageRow, sortOrder, fieldName).then(res => {
      this.dataSource = new MatTableDataSource<Training>(res);
      this.dataSource.sort = this.sort;
    })
  }

  findJobByPage(pageIndex: number, pageRow: number, sortOrder: string, fieldName: String){
    this.jobOfferService.findAllByPage(pageIndex, pageRow, sortOrder, fieldName).then(res => {
      this.dataSource = new MatTableDataSource<Training>(res.content);
      this.dataSource.sort = this.sort;
    })
  }
  
  onSearch(form: NgForm){
    switch (this.entityIn) {
      case "training":
        this.trainingService.getTrainingBySearch(this.search(form), this.pageIndex, this.pageSize, this.sortOrder, this.fieldName).then(res => {
          this.length = res.totalElements;
          this.dataSource = new MatTableDataSource<Training>(res.content);
        });
      break;
    
      case "user":
        this.userService.getUsersBySearch(this.search(form), this.pageIndex, this.pageSize, this.sortOrder, this.fieldName).then(res => {
          this.length = res.totalElements;
          this.dataSource = new MatTableDataSource<Training>(res.content);
        });
      break;

      case "job":
        this.jobOfferService.getOffersBySearch(this.search(form), this.pageIndex, this.pageSize, this.sortOrder, this.fieldName).then(res => {
          this.length = res.totalElements;
          this.dataSource = new MatTableDataSource<Training>(res.content);
        });
      break;

      default:
        break;
    }
  }

  searchByKeyword(form: NgForm) {
    this.jobOfferService.getOffersBySearch(this.search(form), this.pageIndex, this.pageSize, this.sortOrder, this.fieldName).then(res => {
      this.length = res.totalElements;
      this.dataSource = new MatTableDataSource<JobOffer>(res.content);
    });
  }
}
