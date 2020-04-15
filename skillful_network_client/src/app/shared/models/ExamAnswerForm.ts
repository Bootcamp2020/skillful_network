export class ExamAnswerForm {

  private _id: number;
  private _questionId: number;
  private _answer: number;

  constructor(data: any) {
    this._id = data.id;
    this._questionId = data._questionId;
    // _userAnswerId is AnswerForm.indexAnswer attribut : userAnswerIf more readable
    this._answer = data.answer;
  }

  get id(): number { return this._id; }
  set id(value: number) { this._id = value; }
  get questionId(): number { return this._questionId; }
  set questionId(value: number) { this._questionId = value; }
  get answer(): number { return this._answer; }
  set answer(value: number) { this._answer = value; }
}
