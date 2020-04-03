export class ReturnQuestion {

    private _questionId: number;
    private _answer: number;

    constructor(questionId: number, answer: number) {
        this._questionId = questionId;
        this._answer = answer;
    }

    get questionId(): number {
        return this._questionId;
    }

    set questionId(value: number) {
        this._questionId = value;
    }

    get answer(): number {
        return this._answer;
    }

    set answer(value: number) {
        this._answer = value;
    }
}
