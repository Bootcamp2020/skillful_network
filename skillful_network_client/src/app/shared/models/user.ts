import { LoginComponent } from 'src/app/login/login.component';
import { Skill } from './skill';
import { Qualif } from './qualif';
import { Subscript } from './subscript';
import { Subscription } from 'rxjs';

export class User {
  private _id: number;
  private _firstName: string;
  private _lastName: string;
  private _password: string;
  private _email: string;
  private _birthDate: string;
  private _qualification: string;
  private _competences: string[];
  private _mobileNumber: string;
  private _status: string;
  private _validated: boolean;
  private _photo: boolean;
  private _skillSet: Skill[];
  private _qualificationSet : Qualif[];
  private _subscriptionSet : Subscript[];
  private _photoProfile: string;

  private _careerGoal: string;
  constructor(data: any) {
    this.id = data.id;
    this.firstName = data.firstName;

    this.email = data.email;
    this.status = data.status;
    this._qualification = data.qualification;
    this.lastName = data.lastName;
    this.password = data.password;
    this._birthDate = data.birthDate;
    this.email = data.email;
    this._mobileNumber = data.mobileNumber;
    this.status = data.status;
    this.validated = data.validated;
    this.photo = data.photo;
    this.skillSet = data.skillSet;
    this.qualificationSet = data.qualificationSet;
    this.subscriptionSet = data.subscriptionSet;
    this.photoProfile = data.photoProfile;
    this.careerGoal = data.careerGoal;
  }
  /* GETTERS & SETTERS */
  public get id(): number {
    return this._id;
  }
  public set id(value: number) {
    this._id = value;
  }
  public get firstName(): string {
    return this._firstName;
  }
  public set firstName(value: string) {
    this._firstName = value;
  }
  public get lastName(): string {
    return this._lastName;
  }
  public set lastName(value: string) {
    this._lastName = value;
  }
  public get password(): string {
    return this._password;
  }
  public set password(value: string) {
    this._password = value;
  }
 /* get birthDate(): Date {
    return this._birthDate;
  }
  set birthDate(value: Date) {
    this._birthDate = value;
  }*/
  public get email(): string {
    return this._email;
  }
  public set email(value: string) {
    this._email = value;
  }
  public get status(): string {
    return this._status;
  }
  public set status(value: string) {
    this._status = value;
  }

    public get mobileNumber(): string {
    return this._mobileNumber;
  }
  public set mobileNumber(value: string) {
    this._mobileNumber = value;

  }
  public get validated(): boolean {
    return this._validated;
  }
  public set validated(value: boolean) {
    this._validated = value;
  }
  public get photo(): boolean {
    return this._photo;
  }
  public set photo(value: boolean) {
    this._photo = value;
  }
  public get skillSet(): Skill[] {
    return this._skillSet;
  }
  public set skillSet(value: Skill[]) {
    this._skillSet = value;
  }

  public get qualificationSet(): Qualif[] {
    return this._qualificationSet;
  }
  public set qualificationSet(value: Qualif[]) {
    this._qualificationSet = value;
  }
  public get subscriptionSet(): Subscript[] {
    return this._subscriptionSet;
  }
  public set subscriptionSet(value: Subscript[]) {
    this._subscriptionSet = value;
  }
  public get photoProfile(): string {
    return this._photoProfile;
  }
  public set photoProfile(value: string) {
    this._photoProfile = value;
  }
  public get careerGoal(): string {
    return this._careerGoal;
  }
  public set careerGoal(value: string) {
    this._careerGoal = value;
  }
}
