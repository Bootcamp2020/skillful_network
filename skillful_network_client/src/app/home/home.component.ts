import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../shared/services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  constructor(private router: Router, private userService: UserService) {
  }

  ngOnInit() {
  }

  logOut() {
    this.userService.disconnect();
    this.router.navigate(['/login']);
  }
}
