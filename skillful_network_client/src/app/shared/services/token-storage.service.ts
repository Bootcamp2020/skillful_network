import { Injectable } from '@angular/core';

// Ce service permettra de manipuler les données relatives au token/utilisateur de la session

const TOKEN_KEY = 'token';
const USERNAME_KEY = 'username';
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

  public saveTokenSession(token: string) {
    sessionStorage.removeItem(TOKEN_KEY);
    sessionStorage.setItem(TOKEN_KEY, token);
    console.log('token sauvé : ' + localStorage.getItem(TOKEN_KEY));
  }

  public saveTokenAndCurrentUsername(token: string, username: string , authorities: string[], storage: string) {
    // On enlève toutes les infos dans les storage
    localStorage.removeItem(TOKEN_KEY);
    sessionStorage.removeItem(TOKEN_KEY);
    localStorage.removeItem(USERNAME_KEY );
    sessionStorage.removeItem(USERNAME_KEY );
    localStorage.removeItem(AUTHORITIES_KEY);
    sessionStorage.removeItem(AUTHORITIES_KEY);

  // En fonction du choix donné en argument, on sauvegarde les informations dans le storage approprié
    if (storage === LOCAL_STORAGE) {
      localStorage.setItem(TOKEN_KEY, token);
      localStorage.setItem(USERNAME_KEY , JSON.stringify(username));
      localStorage.setItem(AUTHORITIES_KEY, JSON.stringify(authorities));
      console.log('token sauvé dans le local storage : ' + localStorage.getItem(TOKEN_KEY));
      console.log('data sauvées concernant l\'utilisateur courant dans le local storage : ' + localStorage.getItem(USERNAME_KEY));
      console.log('authorities sauvées concernant l\'utilisateur courant dans le local storage : ' + localStorage.getItem(AUTHORITIES_KEY));

    } else {
      sessionStorage.setItem(TOKEN_KEY, token);
      sessionStorage.setItem(USERNAME_KEY, JSON.stringify(username));
      sessionStorage.setItem(AUTHORITIES_KEY, JSON.stringify(authorities));
      console.log('token sauvé dans le session storage : ' + sessionStorage.getItem(TOKEN_KEY));
      console.log('data sauvées concernant l\'utilisateur courant dans le session storage : ' + localStorage.getItem(USERNAME_KEY));
      console.log('data sauvées concernant l\'utilisateur courant dans le local storage : ' + localStorage.getItem(AUTHORITIES_KEY));
    }
  }

  public getToken(): string {
    return localStorage.getItem(TOKEN_KEY) || sessionStorage.getItem(TOKEN_KEY);
  }

  public getCurrentUser() {
    return JSON.parse(localStorage.getItem(USERNAME_KEY) || JSON.parse(sessionStorage.getItem(USERNAME_KEY)));
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
