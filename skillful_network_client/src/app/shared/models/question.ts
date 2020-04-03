export class Question {

    private _id: number;
    private _question: string;
    private _choices: string[];
    private _indexAnswer: number;
    private _feedBack: string;

    constructor(id: number, question: string, choices: string[], indexAnswer: number, feedBack: string) {
        this._id = id;
        this._question = question;
        this._choices = choices;
        this._indexAnswer = indexAnswer;
        this._feedBack = feedBack;
    }

    get id(): number {
        return this._id;
    }

    set id(value: number) {
        this._id = value;
    }

    get question(): string {
        return this._question;
    }

    set question(value: string) {
        this._question = value;
    }

    get choices(): string[] {
        return this._choices;
    }

    set choices(value: string[]) {
        this._choices = value;
    }

    get indexAnswer(): number {
        return this._indexAnswer;
    }

    set indexAnswer(value: number) {
        this._indexAnswer = value;
    }

    get feedBack(): string {
        return this._feedBack;
    }

    set feedBack(value: string) {
        this._feedBack = value;
    }
}
