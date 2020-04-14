import { ExamQuestion } from './ExamQuestion';

export class ExamExercice {

  private _id: number;
  private _name: string;
  private _questions: ExamQuestion[];

  constructor(data: any) {
    this._id = data.id;
    this._name = data.name;
    this._questions = data.questions;
  }

  get id(): number { return this._id; }
  set id(value: number) { this._id = value; }
  get name(): string { return this._name; }
  set name(value: string) { this._name = value; }
  get questions(): ExamQuestion[] { return this._questions; }
  set questions(value: ExamQuestion[]) { this._questions = value; }
}
