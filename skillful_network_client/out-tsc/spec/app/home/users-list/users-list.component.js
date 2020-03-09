import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
let UsersListComponent = class UsersListComponent {
    constructor(userService) {
        this.userService = userService;
    }
    ngOnInit() {
        this.userService.findAll().then((users) => this.users = users);
    }
};
UsersListComponent = tslib_1.__decorate([
    Component({
        selector: 'app-users-list',
        templateUrl: './users-list.component.html',
        styleUrls: ['./users-list.component.scss']
    })
], UsersListComponent);
export { UsersListComponent };
//# sourceMappingURL=users-list.component.js.map