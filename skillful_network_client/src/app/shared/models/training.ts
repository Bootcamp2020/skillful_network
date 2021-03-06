import{ITraining} from './mock.training'
export class Training {
    // tslint:disable-next-line:variable-name
    private _id: number;
    // tslint:disable-next-line:variable-name
    private _name: string;
    private _organisation: string;
    private _description: string;
    private _financer: string;
    private _dateBeg: Date;
    private _dateEnd: Date;
    private _durationHours: number;
    private _dateUpload: Date;
    private _prerequisites: string[];
    private _keywords: string[];

    constructor(data: ITraining) {
        this.id = data.id;
        this.name = data.name;
        this.organisation = data.organisation;
        this.description = data.description;
        this.financer = data.financer;
        this.dateBeg = data.dateBeg;
        this.dateEnd = data.dateEnd;
        this.durationHours = data.durationHours;
        this.dateUpload = data.dateUpload;
        this.prerequisites = data.prerequisites;
        this.keywords = data.keywords;
    }

    /* GETTERS & SETTERS */
    get id(): number {
        return this._id;
    }

    set id(value: number) {
        this._id = value;
    }

    get organisation(): string {
        return this._organisation;
    }

    set organisation(value: string) {
        this._organisation = value;
    }
    get description(): string {
        return this._description;
    }

    set description(value: string) {
        this._description = value;
    }

    get keywords(): string[] {
        return this._keywords;
    }

    set keywords(value: string[]) {
        this._keywords = value;
    }

    get prerequisites(): string[] {
        return this._prerequisites;
    }

    set prerequisites(value: string[]) {
        this._prerequisites = value;
    }

    get dateUpload(): Date {
        return this._dateUpload;
    }

    set dateUpload(value: Date) {
        this._dateUpload = value;
    }

    get durationHours(): number {
        return this._durationHours;
    }

    set durationHours(value: number) {
        this._durationHours = value;
    }

    get dateEnd(): Date {
        return this._dateEnd;
    }

    set dateEnd(value: Date) {
        this._dateEnd = value;
    }

    get name(): string {
        return this._name;
    }

    set name(value: string) {
        this._name = value;
    }

    get dateBeg(): Date {
        return this._dateBeg;
    }

    set dateBeg(value: Date) {
        this._dateBeg = value;
    }

    get financer(): string {
        return this._financer;
    }

    set financer(value: string) {
        this._financer = value;
    }
}




