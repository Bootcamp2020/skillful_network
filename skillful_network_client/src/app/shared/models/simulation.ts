import { User } from './user';
import { JobOffer } from './job-offer';
import { Training } from './training';


export class Simulation {

    private _id: number;
    private _userId: number;
    private _jobGoal: String;
    private _creationDate: Date ;
    private _synthesis: String ;
    private _jobOffersSuggested: JobOffer;
    private _jobAccess: boolean;
    private _trainingsSuggested: Training;

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
            this._jobOffersSuggested = data.jobOffersSuggested;
            this._jobAccess = data.jobAccess;
            this._trainingsSuggested = data.trainingsSuggested;
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
    public get jobOffersSuggested(): JobOffer {
        return this._jobOffersSuggested;
    }
    public set jobOffersSuggested(value: JobOffer) {
        this._jobOffersSuggested = value;
    }
    public get trainingsSuggested(): Training {
        return this._trainingsSuggested;
    }
    public set synthtrainingsSuggestedesis(value: Training) {
        this._trainingsSuggested = value;
    }
    public get jobAccess(): boolean {
        return this._jobAccess;
    }
    public set jobAccess(value: boolean) {
        this._jobAccess = value;
    }

}
