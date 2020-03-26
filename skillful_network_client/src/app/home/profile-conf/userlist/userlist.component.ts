import { Component, OnInit, OnDestroy } from '@angular/core';
import { User } from 'src/app/shared/models/user';
import { Subscription } from 'rxjs';
import { UserService } from 'src/app/shared/services/user.service';

@Component({
  selector: 'app-userlist',
  templateUrl: './userlist.component.html',
  styleUrls: ['./userlist.component.scss']
})
export class UserlistComponent implements OnInit, OnDestroy {

  userLogged: User;
  userLSubscription: Subscription;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userLSubscription = this.userService.userLoggedSubject.subscribe(
      (userTemp: User) => {
        this.userLogged = userTemp;
      });
    this.userService.emitUsers();
  }

  ngOnDestroy() {
    this.userLSubscription.unsubscribe();
  }
}
