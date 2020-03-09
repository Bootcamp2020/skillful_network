import {Injectable} from '@angular/core';
import {HttpInterceptor, HttpRequest, HttpHandler, HttpEvent} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TokenHttpInterceptorService implements HttpInterceptor {
  constructor() {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let token = localStorage.getItem('token');

    if (!token) {
      return next.handle(request);
    }

    token = btoa(token);

    const updatedRequest = request.clone({
      headers: request.headers.set('Authorization', `Basic ${token}`)
    });

    return next.handle(updatedRequest).pipe(
      tap(
        (event) => {
        },

        (error) => {
        }
      )
    );
  }
}
