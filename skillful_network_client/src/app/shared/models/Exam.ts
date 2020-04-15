import { ExamExercise } from './ExamExercise';

export class Exam {

  private _id: number;
  private _exerciseSet: ExamExercise[];

  constructor(data: any) {
    this._id = data.id;
    this._exerciseSet = data.exerciseSet;
  }

  get id(): number { return this._id; }
  set id(value: number) { this._id = value; }
  get exerciseSet(): ExamExercise[] { return this._exerciseSet; }
  set exerciseSet(value: ExamExercise[]) { this._exerciseSet = value; }
}
