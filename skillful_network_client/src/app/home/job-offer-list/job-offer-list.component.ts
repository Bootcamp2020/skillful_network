import { MOCK_CANDIDATURE } from './../../shared/models/mock.candidature';
import { Component, OnInit, ViewChild, Input, EventEmitter, HostListener } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { CandidatureService } from 'src/app/shared/services/candidature.service';
import { IPost } from 'src/app/shared/models/mock.candidature';
import { JobOfferService } from '../../shared/services/job-offer.service';
import { JobOffer } from '../../shared/models/job-offer';
import { Candidature } from '../../shared/models/candidature';
import { Observable } from 'rxjs';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-job-offer-list',
  templateUrl: './job-offer-list.component.html',
  styleUrls: ['./job-offer-list.component.scss']
})
export class JobOfferListComponent implements OnInit {

  jobOfferId: number;
  dataSource;

  //initial page size
  displayedColumns: string[] = ['name', 'company', 'type', 'dateBeg', 'dateUpload', 'statut', 'plus_info'];
  pageEvent: PageEvent;
  pageSize: number = 10;
  pageSizeOptions: number[] = [10, 25, 50, 100];
  pageIndex: number = 1;
  length: number;
  hidePageSize: boolean = false;
  showFirstLastButtons: boolean = false;
  total: number;
  order: string;
  field: string;
  event: PageEvent;
  //Pagination variables’
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(public candidatureService: CandidatureService, public offerService: JobOfferService) {
  }

  ngOnInit(): void {
    this.getJobOffers(this.paginator, this.pageSize, this.pageIndex, this.checkOrder(this.order), this.checkField(this.field));
  }

  @HostListener('matSortChange', ['$event']) change(event) {
    this.getJobOffers(this.paginator, this.pageSize, this.pageIndex, this.checkOrder(event.direction), this.checkField(event.active));
  }

  getJobOffers(event: PageEvent, page: number, size: number, order: string, field: string) {
    if (event != null) {
      page = event.pageSize;
      size = event.pageIndex + 1;
    }
    this.offerService.findAll(size, page, order, field).then(res => {
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
    if (order == null) {
      return order = "ASCENDING";
    } else {
      if (order = 'asc') {
        return order = "ASCENDING";
      } else {
        return order = "DESCENDING";
      }
      return order;
    }
  }
}
  /*
  searchByNameOrCompany(form: NgForm) {
    this.offerService.getOffersBySearch(form.value.keyword, form.value.page, form.value.size).then(res => {
      this.dataSource = new MatTableDataSource<JobOffer>(res.content);
      this.dataSource.sort = this.sort;
    });
  }*/

