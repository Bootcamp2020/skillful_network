import { ExamExerciseForm } from './ExamExerciseForm';

export class ExamForm {

  private _id: number;
  private _exerciseSet: ExamExerciseForm[];

  constructor(data: any) {
    this._id = data.id;
    this._exerciseSet = data.exerciceSet;
  }

  get id(): number { return this._id; }
  set id(value: number) { this._id = value; }
  get exerciseSet(): ExamExerciseForm[] { return this._exerciseSet; }
  set exerciseSet(value: ExamExerciseForm[]) { this._exerciseSet = value; }
}
