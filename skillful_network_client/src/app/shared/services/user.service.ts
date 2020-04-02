
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { ApiHelperService } from './api-helper.service';

import { Subject } from 'rxjs';
import { Skill } from '../models/skill';
import { Qualif } from '../models/qualif';
import { Subscript } from '../models/subscript';
import {MOCK_USERS} from '../models/mock.users';


//const optionRequete = {
//  headers: new HttpHeaders({
//    'Access-Control-Allow-Origin': '*'
//  })
//};

@Injectable({
  providedIn: 'root'
})
export class UserService {
  

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
  

  public userLogged = new User({
    id: 2,
    firstName:'Jacques',
    lastName: 'Uzzi',
    password: 'passwordtpo',
    birthDate: new Date("2016-01-17T08:44:29+0100"),
    email: 'email@gmail.com',
    mobileNumber: '0123456789',
    status: '',
    validated: true,
    photo: false,
    skillSet: [this.skill0,this.skill1,this.skill2,this.skill3,this.skill4,this.skill5,this.skill6],
    qualificationSet : [this.qualif0,this.qualif1,this.qualif2,this.qualif3,this.qualif4,this.qualif5,this.qualif6],
    subscriptionSet : [this.subscript0,this.subscript1,this.subscript2,this.subscript3,this.subscript4,this.subscript5,this.subscript6],
    photoProfile: 'https://cdn.profoto.com/cdn/053149e/contentassets/d39349344d004f9b8963df1551f24bf4/profoto-albert-watson-steve-jobs-pinned-image-original.jpg?width=2840&quality=75&format=jpg',
    careerGoal: 'Développeur Java Fullstack'  
  });

  userLoggedSubject = new Subject<User>();
  // la suite est héritée de l'ancien service => tout du vide !

  emitUsers() {
    this.userLoggedSubject.next(this.userLogged);
  }

  public users: User[];
  constructor(private api: ApiHelperService) {
    this.users = [];
    MOCK_USERS.forEach((user) => {
      this.users.push(new User(user));
    });
  }

  updateUser(user: User){
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
    this.api.put({ endpoint: '/users/' + this.userLogged.id, data: this.userLogged })
    this.emitUsers();
  }

  public findById(id: number): User {
    return this.users[id];
  }

  public findCurrentlyAuthenticatedUser(): Promise<User> {
    return null;
  }

  public findAll(): Promise<any> {
    let promise = new Promise((resolve, reject) => {
      this.api.get({ endpoint: '/users' })
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

  public findByContain(option:String ,contain: String): Promise<Skill>{	
    return this.api.get( {endpoint : `/${option}/candidates` , queryParams:{"contain": contain }})	
  }
  
  public getUsersBySearch(keyword:string, page: number, size: number):Promise<any>{
    return this.api.get({endpoint : `/users/search`, queryParams: {keyword, page: page, size: size} })
  }
  

  public disconnect() {

  }

}
