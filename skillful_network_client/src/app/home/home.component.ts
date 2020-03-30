import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../shared/services/user.service';
import { MatBottomSheet, MatBottomSheetRef } from '@angular/material/bottom-sheet';
import { MatDialog } from '@angular/material/dialog';
import { BottomSheetOverviewExample } from '../bottom-sheet-overview-example/bottom-sheet-overview-example';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  constructor(private router: Router, private userService: UserService, public dialog: MatDialog) {
  }

  ngOnInit() {
  }

 

  logOut() {
    this.userService.disconnect();
    this.router.navigate(['/login']);
  }
}
