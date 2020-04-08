import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/shared/models/user';
import { TokenStorageService } from 'src/app/shared/services/token-storage.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  public user: User;
  constructor( public tokenstorage: TokenStorageService) { }

  ngOnInit(): void {
    this.user = this.tokenstorage.getCurrentUser();
  }

}


