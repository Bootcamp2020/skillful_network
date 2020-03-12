import { IPost } from './mock.candidature';

export class Candidature {

    private _id: String;
    private _company: String;
    private _job: String;
    private _status: String;
    private _details: String;
  
   

    constructor(data: IPost) {
        this.company = data.company;
        this.job = data.job;
        this.status = data.status;
        this._details = data.details;
      }
    
    public get id_1(): String {
        return this._id;
    }
    public set id_1(value: String) {
        this._id = value;
    }
    public get company(): String {
        return this._company;
    }
    public set company(value: String) {
        this._company = value;
    }
    public get job(): String {
        return this._job;
    }
    public set job(value: String) {
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
