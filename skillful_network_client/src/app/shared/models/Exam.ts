import { ExamExercice } from './ExamExercice';

export class Exam {

  private _id: number;
  private _exerciceSet: ExamExercice[];

  constructor(data: any) {
    this._id = data.id;
    this._exerciceSet = data.exerciceSet;
  }

  get id(): number { return this._id; }
  set id(value: number) { this._id = value; }
  get exerciceSet(): ExamExercice[] { return this._exerciceSet; }
  set exerciceSet(value: ExamExercice[]) { this._exerciceSet = value; }
}
