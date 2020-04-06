import {ReturnAnswerSet} from './return-answerSet';

export class ReturnExerciceSet {

  private _exerciceSet: ReturnAnswerSet[];

  constructor(exerciceSet: ReturnAnswerSet[]) {
    this._exerciceSet = exerciceSet;
  }

  get exerciceSet(): ReturnAnswerSet[] {
    return this._exerciceSet;
  }

  set exerciceSet(value: ReturnAnswerSet[]) {
    this._exerciceSet = value;
  }
}
