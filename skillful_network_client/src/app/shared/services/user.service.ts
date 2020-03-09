import {HttpClient, HttpHeaders} from '@angular/common/http';

import {Observable} from 'rxjs';

import {Injectable} from '@angular/core';
import {User} from '../models/user';
import {ApiHelperService} from './api-helper.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private api: ApiHelperService) {
  }

  public findById(id: string): Promise<User> {
    return null;
  }

  public findCurrentlyAuthenticatedUser(): Promise<User> {
    return null;
  }

  public findAll(): Promise<User[]> {
    return null;
  }

  public disconnect() {

  }
}
