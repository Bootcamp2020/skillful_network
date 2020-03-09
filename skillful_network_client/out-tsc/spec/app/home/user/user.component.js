import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
let UserComponent = class UserComponent {
    constructor(userService, route) {
        this.userService = userService;
        this.route = route;
    }
    ngOnInit() {
        this.userService.findById(this.route.snapshot.params.id).then(data => this.user = data);
    }
};
UserComponent = tslib_1.__decorate([
    Component({
        selector: 'app-user',
        templateUrl: './user.component.html',
        styleUrls: ['./user.component.scss']
    })
], UserComponent);
export { UserComponent };
//# sourceMappingURL=user.component.js.map