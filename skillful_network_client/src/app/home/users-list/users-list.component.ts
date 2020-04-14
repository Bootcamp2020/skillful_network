import {Component, HostListener, OnInit, ViewChild} from '@angular/core';
import {User} from '../../shared/models/user';
import {UserService} from '../../shared/services/user.service';
import {MatTableDataSource} from '@angular/material/table';
import {MatSort} from '@angular/material/sort';
import {FormControl} from '@angular/forms';
import {FormGroup} from '@angular/forms';
import {MatPaginator} from '@angular/material/paginator';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.scss']
})

export class UsersListComponent implements OnInit {
  public users: User[];
  public isLoading: Boolean;
  private page: number;
  private size: number;
  private order: string;
  private keyword: string;
  private pageIndex = 1;
  private field: string;
  pageSize = 10;
  pageSizeOptions: number[] = [10, 25, 50, 100];
  length: number;
  hidePageSize = false;
  showFirstLastButtons = true;
  keyEvent = true;
  private event: KeyboardEvent;

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  search = new FormGroup({
    keyword: new FormControl(''),
  });

  constructor(private userService: UserService) {
  }

  displayedColumns: string[] = ['firstName', 'lastName', 'birthDate', 'details'];
  dataSource;

  ngOnInit(): void  {
    this.isLoading = true;
    this.userService.findAll(this.page = this.pageIndex, this.size = this.pageSize, this.checkOrder(), this.field = 'firstName').then(res => {
      this.length = res.totalElements;
      this.dataSource = new MatTableDataSource<User>(res.content);
    }).finally(() => this.isLoading = false);

  }
  @HostListener('matSortChange', ['$event']) change(event) {
    this.order = event.direction;
    this.field = event.active;
    this.onSearchByFirstNameOrLastName();
  }
  @HostListener('document:keydown', ['$event']) handleKeyboardEvent(event) {
    this.event = event;
    this.keyEvent = true;

  }

  onSearchByFirstNameOrLastName() {
    if (event !== undefined) {
      this.pageSize = this.paginator.pageSize;
      this.pageIndex = this.paginator.pageIndex + 1;
    }

    const keyword = this.search.value.keyword;
    this.userService.getUsersBySearch(keyword, this.page = this.pageIndex, this.size = this.pageSize, this.checkOrder(), this.checkField()).then((res: { totalElements: number; content: User[]; }) => {
      this.length = res.totalElements;
      this.dataSource = new MatTableDataSource<User>(res.content);
    }).finally(() => this.isLoading = false);
  }
  checkField() {
    if (this.field == null) {
      return this.field = 'firstName';
    } else {
      return this.field;
    }
  }
  checkOrder() {
    if (this.order == null || this.order === 'asc') {
      this.order = 'ASCENDING';
    } else {
      this.order = 'DESCENDING';
    }
    return this.order;
  }
}
