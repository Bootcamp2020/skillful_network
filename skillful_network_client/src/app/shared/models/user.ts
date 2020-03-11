export class User {
  private _id: string;
  private _email:string;
  constructor(data: any) {
    this.id = data.id;
  }
  /* GETTERS & SETTERS */
  get id(): string {
    return this._id;
  }
  set id(value: string) {
    this._id = value;
  }
  get email(): string {
    return this._email;
  }
  set email(value: string) {
    this._email = value;
  }
}
