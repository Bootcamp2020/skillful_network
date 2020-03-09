import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiHelperService {
  constructor(private http: HttpClient) {
  }

  public get({endpoint, queryParams = {}}: { endpoint: string, queryParams?: any }): Promise<any> {
    return this.request({endpoint, method: 'GET', queryParams});
  }

  public post({endpoint, data = {}, queryParams = {}}: { endpoint: string, data?: any, queryParams?: any }): Promise<any> {
    return this.request({endpoint, method: 'POST', data, queryParams});
  }

  public put({endpoint, data = {}, queryParams = {}}: { endpoint: string, data?: any, queryParams?: any }): Promise<any> {
    return this.request({endpoint, method: 'PUT', data, queryParams});
  }

  public delete({endpoint, data = {}, queryParams = {}}: { endpoint: string, data?: any, queryParams?: any }): Promise<any> {
    return this.request({endpoint, method: 'DELETE', data, queryParams});
  }

  public request({endpoint, method = 'GET', data = {}, queryParams = {}}:
                   { endpoint: string; method?: string; data?: object; queryParams?: any; }): Promise<any> {
    const methodWanted = method.toLowerCase();

    const url = environment.base_url + endpoint;

    const requestOptions = {
      params: queryParams
    };

    let req: Observable<any> = null;
    if (methodWanted === 'get') {
      req = this.http.get(url, {...requestOptions, observe: 'response'});
    } else if (methodWanted === 'post') {
      req = this.http.post(url, data, {...requestOptions, observe: 'response'});
    } else if (methodWanted === 'put') {
      req = this.http.put(url, data, {...requestOptions, observe: 'response'});
    } else if (methodWanted === 'delete') {
      req = this.http.delete(url, {...requestOptions, observe: 'response'});
    }

    if (!req) {
      throw new Error(`error calling ${url} with method ${methodWanted}`);
    }

    // Conversion du résultat en promesse
    return req.toPromise().then((res) => res.body);
  }
}
