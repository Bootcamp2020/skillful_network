import { Component, OnInit, ViewChild, HostListener } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { CandidatureService } from 'src/app/shared/services/candidature.service';
import { JobOfferService } from '../../shared/services/job-offer.service';
import { JobOffer } from '../../shared/models/job-offer';
import { Candidature } from '../../shared/models/candidature';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-job-offer-list',
  templateUrl: './job-offer-list.component.html',
  styleUrls: ['./job-offer-list.component.scss']
})
export class JobOfferListComponent implements OnInit {

  dataSource;
  displayedColumns: string[] = ['name', 'company', 'type', 'dateBeg', 'dateUpload', 'statut', 'plus_info'];
  pageEvent: PageEvent;
  pageSize: number = 10;
  pageSizeOptions: number[] = [10, 25, 50, 100];
  pageIndex: number = 1;
  length: number;
  hidePageSize: boolean = false;
  showFirstLastButtons: boolean = false;
  order: string;
  field: string;
  form: NgForm;
  keyEvent: boolean = false;
  keyword: string;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(public candidatureService: CandidatureService, public offerService: JobOfferService) {
  }

  ngOnInit(): void {
    this.searchByNameOrCompany(this.form);
  }

  @HostListener('matSortChange', ['$event']) change(event) {
    this.order = event.direction;
    this.field = event.active;
    this.searchByNameOrCompany(this.form);
  }

  @HostListener('document:keydown', ['$event']) handleKeyboardEvent(event: KeyboardEvent) {
    this.keyEvent = true;
  }

  getJobOffers(event: PageEvent, page: number, size: number, order: string, field: string) {
    this.offerService.findAll(size, page, this.checkOrder(order), this.checkField(field)).then(res => {
      this.length = res.totalElements;
      this.dataSource = new MatTableDataSource<JobOffer>(res.content);
    });
  }

  searchByNameOrCompany(form: NgForm) {
    if (event != undefined) {
      this.pageSize = this.paginator.pageSize;
      this.pageIndex = this.paginator.pageIndex + 1;
    }
    this.offerService.getOffersBySearch(this.search(form), this.pageIndex, this.pageSize, this.checkOrder(this.order), this.checkField(this.field)).then(res => {
      this.length = res.totalElements;
      this.dataSource = new MatTableDataSource<JobOffer>(res.content);
    });
  }

  checkField(field: string) {
    if (field == null) {
      return field = "dateBeg";
    } else {
      return field;
    }
  }
  checkOrder(order: string) {
    if (order == null || order === 'asc') {
      order = "ASCENDING";
    } else {
      order = "DESCENDING";
    }
    return order;
  }
  search(form: NgForm) {
    if (this.keyEvent == false) {
      this.keyword ="";
    } else {
      this.keyword = form.value.keyword;
    }
    return this.keyword;
  }
}