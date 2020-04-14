import { LoginComponent } from 'src/app/login/login.component';
import { ChipValue } from './chip-value';
import { Subscription } from 'rxjs';

export class User {
  private _id: number;
  private _firstName: string;
  private _lastName: string;
  private _password: string;
  private _email: string;
  private _birthDate: Date;
  private _qualification: string;
  private _competences: string[];
  private _mobileNumber: string;
  private _status: string;
  private _validated: boolean;
  private _photo: boolean;
<<<<<<< HEAD
  private _skillSet: ChipValue[];
  private _qualificationSet : ChipValue[];
  private _subscriptionSet : ChipValue[];
  private _photoProfile: string;
=======
  private _skillSet: Skill[];
  private _qualificationSet : Qualif[];
  private _subscriptionSet : Subscript[];
  private _photoProfile: any;
>>>>>>> 1a34c34f5038dff7fa9d5950e9414b6cb9d21b14
  private _role: string[];

  private _careerGoal: string;
  constructor(data: any) {
    this._id = data.id;
    this._firstName = data.firstName;
    this._email = data.email;
    this._status = data.status;
    this._qualification = data.qualification;
    this._lastName = data.lastName;
    this._password = data.password;
    this._birthDate = data.birthDate;
    this._email = data.email;
    this._mobileNumber = data.mobileNumber;
    this._status = data.status;
    this._validated = data.validated;
    this._photo = data.photo;
    this._skillSet = data.skillSet;
    this._qualificationSet = data.qualificationSet;
    this._subscriptionSet = data.subscriptionSet;
    this._photoProfile = data.photoProfile;
    this._careerGoal = data.careerGoal;
    this._role = ['user'];
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
  get birthDate(): Date {
    return this._birthDate;
  }
  set birthDate(value: Date) {
    this._birthDate = value;
  }
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
  public get skillSet(): ChipValue[] {
    return this._skillSet;
  }
  public set skillSet(value: ChipValue[]) {
    this._skillSet = value;
  }
  public get qualificationSet(): ChipValue[] {
    return this._qualificationSet;
  }
  public set qualificationSet(value: ChipValue[]) {
    this._qualificationSet = value;
  }
  public get subscriptionSet(): ChipValue[] {
    return this._subscriptionSet;
  }
  public set subscriptionSet(value: ChipValue[]) {
    this._subscriptionSet = value;
  }
  public get photoProfile():any {
    return this._photoProfile;
  }
  public set photoProfile(value: any) {
    this._photoProfile = value;
  }
  public get careerGoal(): string {
    return this._careerGoal;
  }
  public set careerGoal(value: string) {
    this._careerGoal = value;
  }
}
