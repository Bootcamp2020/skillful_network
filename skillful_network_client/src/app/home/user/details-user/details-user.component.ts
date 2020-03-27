import {Component, Input, OnInit} from '@angular/core';
import {UserService} from '../../../shared/services/user.service';
import {User} from '../../../shared/models/user';
import {ActivatedRoute} from "@angular/router";



@Component({
  selector: 'app-details-user',
  templateUrl: './details-user.component.html',
  styleUrls: ['./details-user.component.scss']
})
export class DetailsUserComponent implements OnInit {
  public user: User;
  @Input() public firstName: string;
  @Input() public name: string;
  @Input() public email: number;
  @Input() public birthDate: string;
  @Input() public status: string;
  @Input() public urlImage: string;
  @Input() public careerGoal: string;


  constructor(private userService: UserService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.user = this.userService.findById(this.route.snapshot.params.id);
  }

}
