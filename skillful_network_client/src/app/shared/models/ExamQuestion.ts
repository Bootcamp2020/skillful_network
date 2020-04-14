import { ExamChoice } from './ExamChoice';

export class ExamQuestion {

  private _id: number;
  private _question: string;
  private _choices: ExamChoice[];
  private _indexAnswer: number;
  private _feedBack: string;
  private _userAnswerId: number;

  constructor(data: any) {
    this._id = data.id;
    this._question = data.question;
    this._choices = data.choices;
    this._indexAnswer = data.indexAnswer;
    this._feedBack = data.feedBack;
    // _userAnswerId generzated from ExamForm when received from back
  }

  get id(): number { return this._id; }
  set id(value: number) { this._id = value; }
  get question(): string { return this._question; }
  set question(value: string) { this._question = value; }
  get choices(): ExamChoice[] { return this._choices; }
  set choices(value: ExamChoice[]) { this._choices = value; }
  get indexAnswer(): number { return this._indexAnswer; }
  set indexAnswer(value: number) { this._indexAnswer = value; }
  get feedBack(): string { return this._feedBack; }
  set feedBack(value: string) { this._feedBack = value; }
  get userAnswerId(): number { return this._userAnswerId; }
  set userAnswerId(value: number) { this._userAnswerId = value; }
}
