import {HttpClient, HttpHeaders} from '@angular/common/http';

import {Observable} from 'rxjs';

import {Injectable} from '@angular/core';
import {User} from '../models/user';
import {ApiHelperService} from './api-helper.service';
import { Skill } from '../models/skill';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private api: ApiHelperService) {
  }
  
  public findByContain(option:String ,contain: String): Promise<Skill>{
    return this.api.get( {endpoint : `/${option}/candidates` , queryParams:{"contain": contain }})
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
