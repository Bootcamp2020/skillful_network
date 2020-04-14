import { ExamExercice } from './ExamExercice';
import { ExamExerciceForm } from './ExamExerciceForm';

export class ExamForm {

  private _id: number;
  private _exerciceSet: ExamExerciceForm[];

  constructor(data: any) {
    this._id = data.id;
    this._exerciceSet = data.exerciceSet;
  }

  get id(): number { return this._id; }
  set id(value: number) { this._id = value; }
  get exerciceSet(): ExamExerciceForm[] { return this._exerciceSet; }
  set exerciceSet(value: ExamExerciceForm[]) { this._exerciceSet = value; }
}
