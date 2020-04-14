
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { ApiHelperService } from './api-helper.service';

import { Subject } from 'rxjs';
import { ChipValue } from '../models/chip-value';
import {MOCK_USERS} from '../models/mock.users';


// const optionRequete = {
//  headers: new HttpHeaders({
//    'Access-Control-Allow-Origin': '*'
//  })
// };

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private api: ApiHelperService) {
    this.users = [];
    MOCK_USERS.forEach((user) => {
      this.users.push(new User(user));
    });
  }


<<<<<<< HEAD
  private skill0 = new ChipValue('Flater');
  private skill1 = new ChipValue('Peigner');
  private skill2 = new ChipValue('Eborgner');
  private skill3 = new ChipValue('Bomber');
  private skill4 = new ChipValue('Arriver');
  private skill5 = new ChipValue('Dégueulasser');
  private skill6 = new ChipValue('Courber');

  private qualif0 = new ChipValue('Maternelle');
  private qualif1 = new ChipValue('MaternelleSpe');
  private qualif2 = new ChipValue('650 au TOEIC');
  private qualif3 = new ChipValue('850 au COÏT');
  private qualif4 = new ChipValue('Membre J&M');
  private qualif5 = new ChipValue('BTS');
  private qualif6 = new ChipValue('M.I.T');

  private subscript0 = new ChipValue('Auto Hebdo');
  private subscript1 = new ChipValue('Chasse Pêche');
  private subscript2 = new ChipValue('Bonjour Madame');
  private subscript3 = new ChipValue('Femme Actuelle');
  private subscript4 = new ChipValue('Pif Gadget');
  private subscript5 = new ChipValue('Charlie Hebdo');
  private subscript6 = new ChipValue('Elle');
  
=======
  private skill0 = new Skill('Flater');
  private skill1 = new Skill('Peigner');
  private skill2 = new Skill('Eborgner');
  private skill3 = new Skill('Bomber');
  private skill4 = new Skill('Arriver');
  private skill5 = new Skill('Dégueulasser');
  private skill6 = new Skill('Courber');

  private qualif0 = new Qualif('Maternelle');
  private qualif1 = new Qualif('MaternelleSpe');
  private qualif2 = new Qualif('650 au TOEIC');
  private qualif3 = new Qualif('850 au COÏT');
  private qualif4 = new Qualif('Membre J&M');
  private qualif5 = new Qualif('BTS');
  private qualif6 = new Qualif('M.I.T');

  private subscript0 = new Subscript('Auto Hebdo');
  private subscript1 = new Subscript('Chasse Pêche');
  private subscript2 = new Subscript('Bonjour Madame');
  private subscript3 = new Subscript('Femme Actuelle');
  private subscript4 = new Subscript('Pif Gadget');
  private subscript5 = new Subscript('Charlie Hebdo');
  private subscript6 = new Subscript('Elle');

>>>>>>> 1a34c34f5038dff7fa9d5950e9414b6cb9d21b14

  public userLogged = new User({
    id: 2,
    firstName: 'Jacques',
    lastName: 'Uzzi',
    password: 'passwordtpo',
    birthDate: new Date('2016-01-17T08:44:29+0100'),
    email: 'email@gmail.com',
    mobileNumber: '0123456789',
    status: '',
    validated: true,
    photo: false,
    skillSet: [this.skill0, this.skill1, this.skill2, this.skill3, this.skill4, this.skill5, this.skill6],
    qualificationSet : [this.qualif0, this.qualif1, this.qualif2, this.qualif3, this.qualif4, this.qualif5, this.qualif6],
    subscriptionSet : [this.subscript0, this.subscript1, this.subscript2, this.subscript3, this.subscript4, this.subscript5, this.subscript6],
    photoProfile: 'https://cdn.profoto.com/cdn/053149e/contentassets/d39349344d004f9b8963df1551f24bf4/profoto-albert-watson-steve-jobs-pinned-image-original.jpg?width=2840&quality=75&format=jpg',
    careerGoal: 'Développeur Java Fullstack'
  });

  userLoggedSubject = new Subject<User>();

  public users: User[];
  // la suite est héritée de l'ancien service => tout du vide !

  emitUsers() {
    this.userLoggedSubject.next(this.userLogged);
  }

  updateUser(user: User) {
    this.userLogged.firstName = user.firstName;
    this.userLogged.lastName = user.lastName;
    this.userLogged.birthDate = user.birthDate;
    this.userLogged.email = user.email;
    this.userLogged.mobileNumber = user.mobileNumber;
    this.userLogged.careerGoal = user.careerGoal;
    this.userLogged.skillSet = user.skillSet;
    this.userLogged.qualificationSet = user.qualificationSet;
    this.userLogged.subscriptionSet = user.subscriptionSet;

    // envoie vers le back
    this.api.put({ endpoint: '/users', data: this.userLogged });
    this.emitUsers();
  }

  public findById(id: number): User {
    return this.users[id];
  }

  public findCurrentlyAuthenticatedUser(): Promise<User> {
    return null;
  }

  public findAll(  page: number, size: number, sortOrder: string, field: string): Promise<any> {
    const promise = new Promise((resolve, reject) => {
      this.api.get({endpoint: `/users/`, queryParams: {  page, size , sortOrder, field}})
        .then(
          res => {
            resolve(res);
          },
          msg => {
            reject(msg);
            }
        ).catch((error) => {
        });
    });
    return promise;
  }

<<<<<<< HEAD
  public findByContain(option:String ,contain: String): Promise<ChipValue>{	
    return this.api.get( {endpoint : `/${option}/candidates` , queryParams:{"contain": contain }})	
=======
  public findByContain(option: string , contain: string): Promise<Skill> {
    return this.api.get( {endpoint : `/${option}/candidates` , queryParams: {contain }});
>>>>>>> 1a34c34f5038dff7fa9d5950e9414b6cb9d21b14
  }

  public getUsersBySearch(keyword: string, page: number, size: number, sortOrder: string, field: string): Promise<any> {
    const promise = new Promise((resolve, reject) => {
      this.api.get({endpoint : `/users/search`, queryParams: {keyword, page, size, sortOrder, field} })
          .then(
              res => {
                resolve(res);
              },
              msg => {
                reject(msg);
              }
          ).catch((error) => {
      });
    });
    return promise;
  }


  public disconnect() {

  }

  public findAllByPage(page: number,size: number, sortOrder: String,fieldToSort: String): Promise<any> {
    let promise = new Promise((resolve, reject) => {
      this.api.get({
        endpoint: '/users',
        queryParams: { "page": page, "size": size, "sortOrder": sortOrder, "field": fieldToSort }
      })
        .then(
          res => {
            resolve(res);
          },
          msg => {
            reject(msg);
          }
        ).catch((error) => {
        });
    });
    return promise;
  }

}
