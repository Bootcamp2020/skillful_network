import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './shared/services/token-storage.service';
import { Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';
import { pipe } from 'rxjs';
import { ApiHelperService } from './shared/services/api-helper.service';
import { UserService } from './shared/services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  public stillLogged:Boolean;
  public isLoading :Boolean;
  constructor(private tokenStorageService: TokenStorageService, private router: Router , private api:ApiHelperService , private userService : UserService) {
    
  }
  ngOnInit(): void {
        this.stillLogged = true
        this.api.post({endpoint:"/authentication/whoami" , data:this.tokenStorageService.getToken()}).then(
          data=>{
            this.userService.updateUser(data);
            this.stillLogged = false
          }
          )
    .catch(err=>{
            localStorage.clear()
            this.router.navigate(['/login']);
            this.stillLogged = false
          })
  }
  
}

