import { LoginComponent } from 'src/app/login/login.component';
import { Skill } from './skill';
import { Qualif } from './qualif';
import { Subscript } from './subscript';

export class User {
  
  constructor(
    public id : number,
    public firstName : string = 'tbd',
    public lastName : string  = 'tbd',
    public password : string  = 'tbd',
    public birthDate : Date = new Date("1900-01-01T08:12:29+0100"),
    public email : string  = 'tbd@tbd.com',
    public mobileNumber : string = '0000000000',
    public status : string  = 'tbd',
    public validated : boolean = false,
    public photo : boolean = false,
    public skillSet : Skill[] = [],
    public qualificationSet : Qualif[] = [],
    public subscriptionSet : Subscript[] = [],
    public photoProfile: string = 'tbd'
  ){}
}
