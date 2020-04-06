import { Injectable } from '@angular/core';
import { ApiHelperService } from '../services/api-helper.service';
import { JwtResponse } from '../models/jwt-response';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private api: ApiHelperService) { }

  login(credentials): Promise<JwtResponse> {
    console.log('password récupéré de le méthode login : ' + credentials.password);
    console.log('email récupéré de la méthode login : ' + credentials.emailLogin);
    return this.api.post({endpoint: '/login', data: {password: credentials.password, email: credentials.emailLogin}});
}

  register(credentials): Promise<any> {
    console.log('email récupéré de le méthode register : ' + credentials.email);
    console.log('rôle récupéré de la méthode login : ' + credentials.role);
    return this.api.post({ endpoint: '/register', data: { email: credentials.email, role: credentials.role} });
}

}
