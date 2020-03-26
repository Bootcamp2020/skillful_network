
import { User } from '../models/user';
import { Subject } from 'rxjs';
import { Skill } from '../models/skill';
import { Qualif } from '../models/qualif';
import { Subscript } from '../models/subscript';
import {ApiHelperService} from './api-helper.service';
import { Injectable } from '@angular/core';
//import { HttpClient, HttpHeaders, HttpClientModule } from '@angular/common/http';
import {MOCK_USERS} from '../models/mock.users';

//const optionRequete = {
//  headers: new HttpHeaders({
//    'Access-Control-Allow-Origin': '*'
//  })
//};

@Injectable()
export class UserService {
  //private usersUrl: string;

  private skill0 = new Skill('Flater le mamout');
  private skill1 = new Skill('Peigner la girafe');
  private skill2 = new Skill('Eborgner la mouche');
  private skill3 = new Skill('Bomber le verre');
  private skill4 = new Skill('Arriver à pied par la Chine');
  private skill5 = new Skill('Dégueulasser le front-end');
  private skill6 = new Skill('Courber la banane');

  private qualif0 = new Qualif('Maternelle Supérieure');
  private qualif1 = new Qualif('Maternelle Spéciale');
  private qualif2 = new Qualif('650 au TOEIC');
  private qualif3 = new Qualif('850 au COÏT');
  private qualif4 = new Qualif('Membre honoraire de J&M');
  private qualif5 = new Qualif('BTS coiffure soudure');
  private qualif6 = new Qualif('M.I.T. de Palavas les flots');

  private subscript0 = new Subscript('Auto Hebdo');
  private subscript1 = new Subscript('Chasse Pêche et Tradition');
  private subscript2 = new Subscript('Bonjour Madame');
  private subscript3 = new Subscript('Femme Actuelle');
  private subscript4 = new Subscript('Pif Gadget');
  private subscript5 = new Subscript('Charlie Hebdo');
  private subscript6 = new Subscript('Elle');
  

  private userLogged = new User({
    id: 12,
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
    photoProfile: '',
    careerGoal: ''  
  });
    
  userLoggedSubject = new Subject<User>();

  emitUsers() {
    this.userLoggedSubject.next(this.userLogged);
  }

  updateUser(user: User){
    this.userLogged.firstName = user.firstName;
    this.userLogged.lastName = user.lastName;
    this.userLogged.birthDate = user.birthDate;
    this.userLogged.email = user.email;
    this.userLogged.mobileNumber = user.mobileNumber;
    this.userLogged.skillSet = user.skillSet;
    this.userLogged.qualificationSet = user.qualificationSet;
    this.userLogged.subscriptionSet = user.subscriptionSet;
    this.emitUsers();
  }

  // la suite est héritée de l'ancien service => tout du vide !

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
  
  public disconnect() {

  }

}
