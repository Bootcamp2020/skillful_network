export class User {
  private _id: string;

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
}
