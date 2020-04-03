import { IPost } from './offre.mock';

export class Post {
    private _status: string;
    private _titleJob: string;
    private _company: string;
    private _description: string;
    private _keywords: string;
    private _level: string;
    private _technology: string;
    private _titletraining: string;
    private _prerequisite: string;
    private _skill: string;
    private _duration: number;
    private _risk: string;
    private _complexity: string;
    constructor(data: IPost) {
        this._status = data.status;
        this._titleJob = data.titleJob;
        this._company = data.company;
        this._description = data.description;
        this._keywords = data.keywords;
        this._level = data.level;
        this._technology = data.technology;
        this._titletraining = data.titletraining;
        this._prerequisite = data.prerequisite;
        this._skill = data.skill;
        this._duration = data.duration;
        this._risk = data.risk;
        this._complexity = data.complexity;

    }

    get status(): string {
        return this._status;
    }

    set status(value: string) {
        this._status = value;
    }

    get titleJob(): string {
        return this._titleJob;
    }

    set titleJob(value: string) {
        this._titleJob = value;
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
    get keywords(): string {
        return this._keywords;
    }

    set keywords(value: string) {
        this._keywords = value;
    }
    get level(): string {
        return this._level;
    }
    set level(value: string) {
        this._level = value;
    }
    get technology(): string {
        return this._technology;
    }

    set technology(value: string) {
        this._technology = value;
    }
    get titletraining(): string {
        return this._titletraining;
    }

    set titletraining(value: string) {
        this._titletraining = value;
    }
    get prerequisite(): string {
        return this._prerequisite;
    }

    set prerequisite(value: string) {
        this._prerequisite = value;
    }
    get skill(): string {
        return this._skill;
    }

    set skill(value: string) {
        this._skill = value;
    }
    get duration(): number {
        return this._duration;
    }

    set duration(value: number) {
        this._duration = value;
    }
    get risk(): string {
        return this._risk;
    }
    set risk(value: string) {
        this._risk = value;
    }
    get complexity(): string {
        return this._complexity;
    }
    set complexity(value: string) {
        this._complexity = value;
    }


}
