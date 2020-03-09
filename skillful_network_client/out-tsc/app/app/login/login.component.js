import { __decorate } from "tslib";
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
        localStorage.clear();
        this.api.post({ endpoint: '/login', data: this.username })
            .then((id) => {
            console.log(id);
            if (id === -1) {
                this.error = true;
            }
            else {
                localStorage.setItem('token', 'X'); // TODO Gérer le token
                this.userService.actualUser = new User({ id });
                this.router.navigate(['/home']);
            }
        });
    }
};
LoginComponent = __decorate([
    Component({
        selector: 'app-login',
        templateUrl: './login.component.html',
        styleUrls: ['./login.component.scss']
    })
], LoginComponent);
export { LoginComponent };
//# sourceMappingURL=login.component.js.map