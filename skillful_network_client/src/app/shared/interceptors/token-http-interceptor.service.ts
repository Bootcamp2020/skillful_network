import {Injectable} from '@angular/core';
import {HttpInterceptor, HttpRequest, HttpHandler, HttpEvent} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import { TokenStorageService } from '../services/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class TokenHttpInterceptorService implements HttpInterceptor {
  constructor(private token: TokenStorageService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let token = this.token.getToken();
    console.log('token récupéré dans interceptor : ' + token);
    if (!token) {
      return next.handle(request);
    }

   // token = btoa(token);
   // console.log('token récupéré dans interceptor btoa : ' + token);

    const updatedRequest = request.clone({
      headers: request.headers.set('Authorization', `Bearer ${token}`)
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
