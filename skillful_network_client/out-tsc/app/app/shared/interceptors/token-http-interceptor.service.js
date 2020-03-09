import { __decorate } from "tslib";
import { Injectable } from '@angular/core';
import { tap } from 'rxjs/operators';
let TokenHttpInterceptorService = class TokenHttpInterceptorService {
    constructor() {
    }
    intercept(request, next) {
        let token = localStorage.getItem('token');
        if (!token) {
            return next.handle(request);
        }
        token = btoa(token);
        const updatedRequest = request.clone({
            headers: request.headers.set('Authorization', `Basic ${token}`)
        });
        return next.handle(updatedRequest).pipe(tap((event) => {
        }, (error) => {
        }));
    }
};
TokenHttpInterceptorService = __decorate([
    Injectable({
        providedIn: 'root'
    })
], TokenHttpInterceptorService);
export { TokenHttpInterceptorService };
//# sourceMappingURL=token-http-interceptor.service.js.map