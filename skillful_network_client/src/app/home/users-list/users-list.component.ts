import {Component, OnInit, ViewChild} from '@angular/core';
import {User} from '../../shared/models/user';
import {UserService} from '../../shared/services/user.service';
import {MatTableDataSource} from '@angular/material/table';
import {MatSort} from '@angular/material/sort';
 

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.scss']
})
export class UsersListComponent implements OnInit {
  public users: User[];

  @ViewChild (MatSort) sort : MatSort;

  constructor(private userService: UserService) {
  }

  displayedColumns = ['firstName', 'lastName','birthDate', 'details'];
  dataSource;
  
  ngOnInit() {
    this.userService.findAll().then(res => {
      this.dataSource = new MatTableDataSource<User>(res);
      this.dataSource.sort = this.sort;
    });
  }
}
