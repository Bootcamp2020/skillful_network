import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { User } from '../shared/models/user';
let LoginComponent = class LoginComponent {
    constructor(api, userService, router) {
        this.api = api;
        this.userService = userService;
        this.router = router;
    }
    ngOnInit() {

    }
    login() {
        // Permet de vider le local storage
        localStorage.clear(); // Plus d'infos sur le local storage ici : https://www.alsacreations.com/article/lire/1402-web-storage-localstorage-sessionstorage.html
        this.api.post({ endpoint: '/authentication/login', data: this.username })
            .then((id) => {
                console.log(id);
                if (id === -1) {
                    this.error = true;
                } else {
                    localStorage.setItem('token', 'X'); // TODO Gérer le token
                    this.userService.actualUser = new User({ id });
                    this.router.navigate(['/home']);
                }
            });
    }
};
LoginComponent = tslib_1.__decorate([
    Component({
        selector: 'app-login',
        templateUrl: './login.component.html',
        styleUrls: ['./login.component.scss']
    })
], LoginComponent);
export { LoginComponent };
//# sourceMappingURL=login.component.js.map