import {ReturnQuestion} from './return-question';

export class ReturnAnswerSet {

  private _id: number;
  private _answerSet: ReturnQuestion[];

  constructor(id: number, answerSet: ReturnQuestion[]) {
    this._id = id;
    this._answerSet = answerSet;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get answerSet(): ReturnQuestion[] {
    return this._answerSet;
  }

  set answerSet(value: ReturnQuestion[]) {
    this._answerSet = value;
  }
}
