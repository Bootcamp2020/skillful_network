import { ExamQuestion } from './ExamQuestion';
import { Keyword } from './Keyword';

export class ExamExercise {

  private _id: number;
  private _name: string;
  private _type: string;
  private _keywords: Keyword[];
  private _questions: ExamQuestion[];

  constructor(data: any) {
    this._id = data.id;
    this._name = data.name;
    this._type = data.type;
    this._keywords = data.keywords;
    this._questions = data.questions;
  }

  get id(): number { return this._id; }
  set id(value: number) { this._id = value; }
  get name(): string { return this._name; }
  set name(value: string) { this._name = value; }
  get type(): string { return this._type; }
  set type(value: string) { this._type = value; }
  get keywords(): Keyword[] { return this._keywords; }
  set keywords(value: Keyword[]) { this._keywords = value; }
  get questions(): ExamQuestion[] { return this._questions; }
  set questions(value: ExamQuestion[]) { this._questions = value; }
}
