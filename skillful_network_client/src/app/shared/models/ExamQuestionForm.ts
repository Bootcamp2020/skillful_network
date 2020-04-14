export class ExamQuestionForm {

  private _id: number;
  private _questionId: number;
  private _userAnswerId: number;

  constructor(data: any) {
    this._id = data.id;
    this._questionId = data._questionId;
    // _userAnswerId is AnswerForm.indexAnswer attribut : userAnswerIf more readable
    this._userAnswerId = data.indexAnswer;
  }

  get id(): number { return this._id; }
  set id(value: number) { this._id = value; }
  get questionId(): number { return this._questionId; }
  set questionId(value: number) { this._questionId = value; }
  get userAnswerId(): number { return this._userAnswerId; }
  set userAnswerId(value: number) { this._userAnswerId = value; }
}
