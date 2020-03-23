import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs';

import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { ApiHelperService } from './api-helper.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  public user: User;

  constructor(private api: ApiHelperService) {
    this.user = new User({
      id: 1, name: 'Jobs', firstName: 'Steve', email: 'SteveJobs@gmail.com', qualification: 'Ingenieur', competences: ['JAVA/JEE', ' Angular', ' Management'], photoProfile: 'https://cdn.profoto.com/cdn/053149e/contentassets/d39349344d004f9b8963df1551f24bf4/profoto-albert-watson-steve-jobs-pinned-image-original.jpg?width=2840&quality=75&format=jpg'
    });
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
