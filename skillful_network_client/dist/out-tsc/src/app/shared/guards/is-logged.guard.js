import { __decorate } from "tslib";
import { Injectable } from '@angular/core';
let IsLoggedGuard = class IsLoggedGuard {
    constructor(user, router) {
        this.user = user;
        this.router = router;
    }
    canActivate(next, state) {
        if (this.user.actualUser) {
            return true;
        }
        else {
            this.router.navigate(['/login']);
            return false;
        }
    }
};
IsLoggedGuard = __decorate([
    Injectable({
        providedIn: 'root'
    })
], IsLoggedGuard);
export { IsLoggedGuard };
//# sourceMappingURL=is-logged.guard.js.map