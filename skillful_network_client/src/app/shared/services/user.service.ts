import {HttpClient, HttpHeaders} from '@angular/common/http';

import {Observable} from 'rxjs';

import {Injectable} from '@angular/core';
import {User} from '../models/user';
import {ApiHelperService} from './api-helper.service';
import {MOCK_USERS} from '../models/mock.users';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  public users: User[];
  constructor(private api: ApiHelperService) {
    this.users = [];
    MOCK_USERS.forEach((user) => {
      this.users.push(new User(user));
    });
  }

  public findById(id: number): User {
    return this.users[id];
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
