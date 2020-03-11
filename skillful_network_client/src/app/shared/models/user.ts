import { MatLine } from '@angular/material/core';

export class User {
  /* Ajouter les attribut pour creer le component User profil*/
  private _id: string;
  private _name: string;
  private _firstName: string; 
  private _email: string;
  private _qualification: string; 
  private _competences: string[];
  private _photoProfile: string;
  /*private _statut:string;*/
   /*private _interest:string;*/
  constructor(data: any) {
    this.id = data.id;
    this.name=data.name;
    this.firstName=data.firstName;
    this.email=data.email;
    this.qualification=data.qualification;
    /*this.statut=data.statut*/
    this.competences=data.competences;
   /* this.interest=data.interest;*/
    this.photoProfile=data.photoProfile;
  } 

  /* GETTERS & SETTERS */
  public get id(): string {
    return this._id;
  }
  public set id(value: string) {
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
  /*user: User=new User(1,'Albert','Einstein','Palberteinstein@gmail.com','PhD','JAVA/JEE,ANgular'); */

}
