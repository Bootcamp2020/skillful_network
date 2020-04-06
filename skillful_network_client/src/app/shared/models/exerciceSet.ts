import {Exercice} from './exercice';

export class ExerciceSet {

  private _exerciceSet: Exercice[];

  constructor(data: any) {
    this._exerciceSet = data.exerciceSet;
  }

  get exerciceSet(): Exercice[] {
    return this._exerciceSet;
  }

  set exerciceSet(value: Exercice[]) {
    this._exerciceSet = value;
  }
}
