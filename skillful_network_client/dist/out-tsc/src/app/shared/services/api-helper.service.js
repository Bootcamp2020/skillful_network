import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
let ApiHelperService = class ApiHelperService {
    constructor(http) {
        this.http = http;
    }
    get({ endpoint, queryParams = {} }) {
        return this.request({ endpoint, method: 'GET', queryParams });
    }
    post({ endpoint, data = {}, queryParams = {} }) {
        return this.request({ endpoint, method: 'POST', data, queryParams });
    }
    put({ endpoint, data = {}, queryParams = {} }) {
        return this.request({ endpoint, method: 'PUT', data, queryParams });
    }
    delete({ endpoint, data = {}, queryParams = {} }) {
        return this.request({ endpoint, method: 'DELETE', data, queryParams });
    }
    request({ endpoint, method = 'GET', data = {}, queryParams = {} }) {
        const methodWanted = method.toLowerCase();
        const url = environment.base_url + endpoint;
        const requestOptions = {
            params: queryParams
        };
        let req = null;
        if (methodWanted === 'get') {
            req = this.http.get(url, Object.assign({}, requestOptions, { observe: 'response' }));
        }
        else if (methodWanted === 'post') {
            req = this.http.post(url, data, Object.assign({}, requestOptions, { observe: 'response' }));
        }
        else if (methodWanted === 'put') {
            req = this.http.put(url, data, Object.assign({}, requestOptions, { observe: 'response' }));
        }
        else if (methodWanted === 'delete') {
            req = this.http.delete(url, Object.assign({}, requestOptions, { observe: 'response' }));
        }
        if (!req) {
            throw new Error(`error calling ${url} with method ${methodWanted}`);
        }
        // Conversion du résultat en promesse
        return req.toPromise().then((res) => res.body);
    }
};
ApiHelperService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root'
    })
], ApiHelperService);
export { ApiHelperService };
//# sourceMappingURL=api-helper.service.js.map