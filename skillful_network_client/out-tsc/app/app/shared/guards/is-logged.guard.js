import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
let IsLoggedGuard = class IsLoggedGuard {
    constructor(user, router) {
        this.user = user;
        this.router = router;
    }
    canActivate(next, state) {
        this.router.navigate(['/login']);
        return false;
    }
};
IsLoggedGuard = tslib_1.__decorate([
    Injectable({
        providedIn: 'root'
    })
], IsLoggedGuard);
export { IsLoggedGuard };
//# sourceMappingURL=is-logged.guard.js.map