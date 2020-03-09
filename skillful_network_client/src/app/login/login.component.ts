import {Component, OnInit} from '@angular/core';
import {ApiHelperService} from '../shared/services/api-helper.service';
import {UserService} from '../shared/services/user.service';
import {User} from '../shared/models/user';
import {Router} from '@angular/router';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
    public username: string;
    public error: boolean;

    constructor(private api: ApiHelperService, private userService: UserService, private router: Router) {
    }

    ngOnInit() {
    }

    login() {
        // Permet de vider le local storage
        localStorage.clear(); // Plus d'infos sur le local storage ici : https://www.alsacreations.com/article/lire/1402-web-storage-localstorage-sessionstorage.html
        this.api.post({endpoint: '/login', data: this.username})
            .then((id: number) => {
                console.log(id);
                if (id === -1) {
                    this.error = true;
                } else {
                    localStorage.setItem('token', 'X'); // TODO Gérer le token
                    this.userService.actualUser = new User({id});
                    this.router.navigate(['/home']);
                }
            });
    }
}
