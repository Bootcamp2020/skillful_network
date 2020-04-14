import { ExamQuestionForm } from './ExamQuestionForm';

export class ExamExerciceForm {

  private _id: number;
  private _questions: ExamQuestionForm[];

  constructor(data: any) {
    this._id = data.id;
    this._questions = data.questions;
  }

  get id(): number { return this._id; }
  set id(value: number) { this._id = value; }
  get questions(): ExamQuestionForm[] { return this._questions; }
  set questions(value: ExamQuestionForm[]) { this._questions = value; }
}
