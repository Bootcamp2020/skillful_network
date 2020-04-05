import { IPost } from './mock.simulation';
import { User } from './user';


export class Simulation {

    private _id: number;
    private _userId: number;
    private _jobGoal: String;
    private _creationDate: Date ;
    private _synthesis: String ;
    private _jobOffersSuggested: boolean;
    private _trainingsSuggested: boolean;
    private _exam: boolean;

    constructor(data: any) {
        if (data == null) {
            this._id = -1;
            // this._userId = 0;
            // this._jobGoal ="";
            // this._creationDate = new Date;
            // this._synthesis = "";
        } else {
            this._id = data.id;
            this._userId = data.user.id;
            this._jobGoal = data.jobGoal;
            this._creationDate = data.creationDate;
            this._synthesis = data.synthesis;
        }
    }
    
    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }
    public get userId(): number {
        return this._userId;
    }
    public set userId(value: number) {
        this._userId = value;
    }
    public get jobGoal(): String {
        return this._jobGoal;
    }
    public set jobGoal(value: String) {
        this._jobGoal = value;
    }
    public get creationDate(): Date {
        return this._creationDate;
    }
    public set creationDate(value: Date) {
        this._creationDate = value;
    }
    public get synthesis(): String {
        return this._synthesis;
    }
    public set synthesis(value: String) {
        this._synthesis = value;
    }
    public get jobOffersSuggested(): boolean {
        return this._jobOffersSuggested;
    }
    public set jobOffersSuggested(value: boolean) {
        this._jobOffersSuggested = value;
    }
    public get trainingsSuggested(): boolean {
        return this._trainingsSuggested;
    }
    public set synthtrainingsSuggestedesis(value: boolean) {
        this._trainingsSuggested = value;
    }
    public get exam(): boolean {
        return this._exam;
    }
    public set exam(value: boolean) {
        this._exam = value;
    }

}
