import { IPost } from './mock.candidature';
import { JobOffer } from './job-offer';
import { IJobOffer } from './mock.job-offers';

export class Candidature {

    private _id: number;
    private _company: String;
    private _job: IJobOffer;
    private _status: String;
    private _details: String;
  
   

    constructor(data: IPost) {
        this.id = data.id;
        this.company = data.company;
        this.job = data.job;
        this.status = data.status;
        this.details = data.details;
      }
    
    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }
    public get company(): String {
        return this._company;
    }
    public set company(value: String) {
        this._company = value;
    }
    public get job(): IJobOffer {
        return this._job;
    }
    public set job(value: IJobOffer) {
        this._job = value;
    }
    public get status(): String {
        return this._status;
    }
    public set status(value: String) {
        this._status = value;
    }
    public get details(): String {
        return this._details;
    }
    public set details(value: String) {
        this._details = value;
    }

}
