export class JobOffer {

    private _id: number;
    private _name: string;
    private _company: string;
    private _description: string;
    private _type: string;
    private _dateBeg: Date;
    private _dateEnd: Date;
    private _dateUpload: Date;
    private _keywords: string[];

    constructor(data: any) {
        this._id = data._id;
        this._name = data._name;
        this._company = data._company;
        this._description = data._description;
        this._type = data._type;
        this._dateBeg = data._dateBeg;
        this._dateEnd = data._dateEnd;
        this._dateUpload = data._dateUpload;
        this._keywords = data._keywords;
    }

    /* GETTERS & SETTERS */
    get id(): number {
        return this._id;
    }

    set id(value: number) {
        this._id = value;
    }

    get name(): string {
        return this._name;
    }

    set name(value: string) {
        this._name = value;
    }

    get company(): string {
        return this._company;
    }

    set company(value: string) {
        this._company = value;
    }

    get description(): string {
        return this._description;
    }

    set description(value: string) {
        this._description = value;
    }

    get type(): string {
        return this._type;
    }

    set type(value: string) {
        this._type = value;
    }

    get dateBeg(): Date {
        return this._dateBeg;
    }

    set dateBeg(value: Date) {
        this._dateBeg = value;
    }

    get dateEnd(): Date {
        return this._dateEnd;
    }

    set dateEnd(value: Date) {
        this._dateEnd = value;
    }

    get dateUpload(): Date {
        return this._dateUpload;
    }

    set dateUpload(value: Date) {
        this._dateUpload = value;
    }

    get keywords(): string[] {
        return this._keywords;
    }

    set keywords(value: string[]) {
        this._keywords = value;
    }
}
