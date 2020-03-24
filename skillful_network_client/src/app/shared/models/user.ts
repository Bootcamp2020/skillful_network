import { MatLine } from '@angular/material/core';

export class User {
  /* Ajouter les attribut pour creer le component User profil*/
  private _id: number;
  private _name: string;
  private _firstName: string; 
  private _email: string;
  private _statut:string;
  private _qualification: string; 
  private _competences: string[];
  private _photoProfile: string;
  private _careerGoal: string;
  /*private _statut:string;*/
   /*private _interest:string;*/
  constructor(data: any) {
    this.id = data.id;
    this.name=data.name;
    this.firstName=data.firstName;
    this.email=data.email;
    this.statut=data.statut;
    this.qualification=data.qualification;
    /*this.statut=data.statut*/
    this.competences=data.competences;
   /* this.interest=data.interest;*/
    this.photoProfile=data.photoProfile;
    this.careerGoal=data.careerGoal;
  } 

  /* GETTERS & SETTERS */
  public get id(): number {
    return this._id;
  }
  public set id(value: number) {
    this._id = value;
  }
  public get name(): string {
    return this._name;
  }
  public set name(value: string) {
    this._name = value;
  }
  public get firstName(): string {
    return this._firstName;
  }
  public set firstName(value: string) {
    this._firstName = value;
  }
  public get email(): string {
    return this._email;
  }
  public set email(value: string) {
    this._email = value;
  }

  public get statut(): string {
    return this._statut;
  }
  public set statut(value: string) {
    this._statut = value;
  }

  public get qualification(): string {
    return this._qualification;
  }
  public set qualification(value: string) {
    this._qualification = value;
  }
  public get competences(): string[] {
    return this._competences;
  }
  public set competences(value: string[]) {
    this._competences = value;
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
  /*user: User=new User(1,'Albert','Einstein','Palberteinstein@gmail.com','PhD','JAVA/JEE,ANgular'); */

}
