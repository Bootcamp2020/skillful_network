import { ExamAnswerForm } from './ExamAnswerForm';

export class ExamExerciseForm {

  private _id: number;
  private _answerSet: ExamAnswerForm[];

  constructor(data: any) {
    this._id = data.id;
    this._answerSet = data.answerSet;
  }

  get id(): number { return this._id; }
  set id(value: number) { this._id = value; }
  get answerSet(): ExamAnswerForm[] { return this._answerSet; }
  set answerSet(value: ExamAnswerForm[]) { this._answerSet = value; }
}
