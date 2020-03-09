import { __decorate } from "tslib";
import { Injectable } from '@angular/core';
import { User } from '../models/user';
let UserService = class UserService {
    constructor(api) {
        this.api = api;
        this.actualUser = null;
    }
    findById(id) {
        return this.api.get({ endpoint: '/user' }).then((res) => {
            // Conversion du retour du serveur (un objet brut) en un objet de type User
            this.actualUser = new User(res);
            return this.actualUser;
        });
    }
    findCurrentlyAuthenticatedUser() {
        return this.findById(this.actualUser.id);
    }
    findAll() {
        return this.api.get({ endpoint: '/users' }).then((res) => {
            return res.map((userObject) => new User(userObject));
        });
    }
    disconnect() {
        this.actualUser = null;
        localStorage.removeItem('token');
    }
};
UserService = __decorate([
    Injectable({
        providedIn: 'root'
    })
], UserService);
export { UserService };
//# sourceMappingURL=user.service.js.map