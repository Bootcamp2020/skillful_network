import {Component, OnInit, ViewChild} from '@angular/core';
import {User} from '../../shared/models/user';
import {UserService} from '../../shared/services/user.service';
import {MatTableDataSource} from '@angular/material/table';
import {MatSort} from '@angular/material/sort';
import {FormControl} from '@angular/forms';
import {FormGroup} from '@angular/forms';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.scss']
})

export class UsersListComponent implements OnInit {
  public users: User[];
  public isLoading: Boolean;
  @ViewChild(MatSort) sort: MatSort;
  private page: number;
  private size: number;

  search = new FormGroup({
    keyword: new FormControl(''),
  });

  constructor(private userService: UserService) {
  }

  displayedColumns = ['firstName', 'lastName', 'birthDate', 'details'];
  dataSource;

  ngOnInit() {
    this.isLoading = true;
    this.userService.findAll( this.page = 1, this.size = 10).then(res => {
      this.dataSource = new MatTableDataSource<User>(res.content);
      this.dataSource.sort = this.sort;
    }).finally(() => this.isLoading = false);

  }

  onSearchByFirstNameOrLastName() {
    const keyword = this.search.value.keyword;
    this.userService.getUsersBySearch( keyword, this.page = 1, this.size = 10).then(res => {
      this.dataSource = new MatTableDataSource<User>(res.content);
      this.dataSource.sort = this.sort;
    });
  }

}
