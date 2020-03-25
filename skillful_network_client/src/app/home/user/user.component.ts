import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {User} from '../../shared/models/user';
import {UserService} from '../../shared/services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  public user: User;

  constructor(private userService: UserService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    //this.userService.findById(this.route.snapshot.params.id).then(data => this.user = data);
  }
}
