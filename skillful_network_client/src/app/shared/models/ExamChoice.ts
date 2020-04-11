export class ExamChoice {

  private _id: number;
  private _choice: string;

  constructor(data: any) {
    this._id = data.id;
    this._choice = data.choices;
  }

  get id(): number { return this._id; }
  set id(value: number) { this._id = value; }
  get choice(): string { return this._choice; }
  set choice(value: string) { this._choice = value; }
}
