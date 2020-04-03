import { Injectable } from '@angular/core';

// Ce service permettra de manipuler les données relatives au token/utilisateur de la session

const TOKEN_KEY = 'token';
const USER_KEY = 'auth-user';
const LOCAL_STORAGE = 'local';
const AUTHORITIES_KEY = 'authorities';
const IS_LOGGED_IN = 'isLoggedIn';



@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  signOut() {
    localStorage.clear();
    sessionStorage.clear();
  }

  public saveTokenAndCurrentUser(token: string, user: string , authorities: string[], storage: string) {
    // On enlève toutes les infos dans les storage
    localStorage.removeItem(TOKEN_KEY);
    sessionStorage.removeItem(TOKEN_KEY);
    localStorage.removeItem(USER_KEY);
    sessionStorage.removeItem(USER_KEY);
    localStorage.removeItem(AUTHORITIES_KEY);
    sessionStorage.removeItem(AUTHORITIES_KEY);

  // En fonction du choix donné en argument, on sauvegarde les informations dans le storage approprié
    if (storage === LOCAL_STORAGE) {
      localStorage.setItem(TOKEN_KEY, token);
      localStorage.setItem(USER_KEY, JSON.stringify(user));
      localStorage.setItem(AUTHORITIES_KEY, JSON.stringify(authorities));
      console.log('token sauvé dans le local storage : ' + localStorage.getItem(TOKEN_KEY));
      console.log('data sauvées concernant l\'utilisateur courant dans le local storage : ' + localStorage.getItem(USER_KEY));
      console.log('authorities sauvées concernant l\'utilisateur courant dans le local storage : ' + localStorage.getItem(AUTHORITIES_KEY));

    } else {
      sessionStorage.setItem(TOKEN_KEY, token);
      sessionStorage.setItem(USER_KEY, JSON.stringify(user));
      sessionStorage.setItem(AUTHORITIES_KEY, JSON.stringify(authorities));
      console.log('token sauvé dans le session storage : ' + sessionStorage.getItem(TOKEN_KEY));
      console.log('data sauvées concernant l\'utilisateur courant dans le session storage : ' + localStorage.getItem(USER_KEY));
      console.log('data sauvées concernant l\'utilisateur courant dans le local storage : ' + localStorage.getItem(AUTHORITIES_KEY));
    }
  }

  public getToken(): string {
    return localStorage.getItem(TOKEN_KEY) || sessionStorage.getItem(TOKEN_KEY);
  }

  public getCurrentUser() {
    return JSON.parse(localStorage.getItem(USER_KEY)) || JSON.parse(sessionStorage.getItem(USER_KEY));
  }

  public getAuthorities(): string[] {
    let roles: string [];
    let authorities: any;
    roles = [];

    authorities = sessionStorage.getItem(AUTHORITIES_KEY) || localStorage.getItem(AUTHORITIES_KEY);
    JSON.parse(authorities).forEach(authority => {
        roles.push(authority.authority);
      });
    return roles;
  }


  public isLogged(): boolean {
    return localStorage.getItem(IS_LOGGED_IN) === 'true' || sessionStorage.getItem(IS_LOGGED_IN) === 'true' ;

  }

}
