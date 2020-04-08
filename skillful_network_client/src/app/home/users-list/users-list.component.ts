import {Component, OnInit, ViewChild} from '@angular/core';
import {User} from '../../shared/models/user';
import {UserService} from '../../shared/services/user.service';
import {MatTableDataSource} from '@angular/material/table';
import {MatSort} from '@angular/material/sort';
import { NgForm } from '@angular/forms';
 

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.scss']
})
export class UsersListComponent implements OnInit {
  public users: User[];
  public isLoading : Boolean;
  @ViewChild (MatSort) sort : MatSort;

  constructor(private userService: UserService) {
  }

  displayedColumns = ['firstName', 'lastName','birthDate', 'details'];
  dataSource;
  
  ngOnInit() {
    this.isLoading =true
    this.userService.findAll().then(res => {
      this.dataSource = new MatTableDataSource<User>(res);
      this.dataSource.sort = this.sort;
      
    }).finally(()=>this.isLoading = false);
  }

  onSearchByFirstNameOrLastName(form:NgForm) {
    this.userService.getUsersBySearch(form.value.keyword,form.value.page,form.value.size).then(res => {
      this.dataSource = new MatTableDataSource<User>(res.content);
      this.dataSource.sort = this.sort;
    });
  }

}
