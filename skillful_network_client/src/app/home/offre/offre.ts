//import { IPost } from './offre.mock';
export class Trainings {

    private _description: string;
    private _keywords: string;
    private _type: string;
    private _name: string;
    private _company: string;
    private _durationHours: number;
    constructor(data: any) {
        this._company = data.company;
        this._description = data.description;
        this._keywords  = data.keywords;
        this._type = data.type;
        this._name = data.name;
        this._durationHours = data.durationHours;
    }
    get durationHours(): number {
        return this._durationHours;
    }

    set durationHours(value: number) {
        this._durationHours = value;
    }
    get description(): string {
        return this._description;
    }

    set description(value: string) {
        this._description = value;
    }

    get keywords(): string {
        return this._keywords;
    }

    set keywords(value: string) {
        this._keywords = value;
    }

    get type(): string {
        return this._type;
    }

    set type(value: string) {
        this._type = value;
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

}
export class JobDetails {

    private _description: string;
    private _keywords: string;
    private _type: string;
    private _name: string;
    private _company: string;
    constructor(data: any) {
       this._company = data.company;
       this._description = data.description;
       this._keywords  = data.keywords;
       this._type = data.type;
       this._name = data.name;
    }
    get description(): string {
        return this._description;
    }

    set description(value: string) {
        this._description = value;
    }

    get keywords(): string {
        return this.keywords;
    }

    set keywords(value: string) {
        this.keywords = value;
    }

    get type(): string {
        return this._type;
    }

    set type(value: string) {
        this._type = value;
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


}
