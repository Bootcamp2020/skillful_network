import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/shared/models/user';
import { TokenStorageService } from 'src/app/shared/services/token-storage.service';
import { ApiHelperService } from 'src/app/shared/services/api-helper.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  public user: User;
  constructor( public tokenstorage: TokenStorageService, private api: ApiHelperService ) { }

  ngOnInit(): void {
     this.api.get({endpoint: `/user`})
                  .then(data => {
                  console.log(data);
                      this.user = data;
                  })
                  .catch((error) => {
                      console.log(error);
     });
     console.log(this.user);
  }

}


