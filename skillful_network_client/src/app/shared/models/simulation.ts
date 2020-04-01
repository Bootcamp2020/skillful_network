import { IPost } from './mock.simulation';


export class Simulation {

    private _nbexercice: number;
    private _date: Date;
    private _careerGoal: String;
    private _details: String;
    private _reloadSim: String;

  
   

    constructor(data: IPost) {
        this.nbexercice = data.nbexercice;
        this.date = data.date;
        this.careerGoal = data.careerGoal;
        this.details = data.details;
        this.reloadSim = data.reloadSim;
      }
    
    
    public get nbexercice(): number {
        return this._nbexercice;
    }
    public set nbexercice(value: number) {
        this._nbexercice = value;
    }
    public get date(): Date {
        return this._date;
    }
    public set date(value: Date) {
        this._date = value;
    }
    public get careerGoal(): String {
        return this._careerGoal;
    }
    public set careerGoal(value: String) {
        this._careerGoal = value;
    }
    public get details(): String {
        return this._details;
    }
    public set details(value: String) {
        this._details = value;
    }
    public get reloadSim(): String {
        return this._reloadSim;
    }
    public set reloadSim(value: String) {
        this._reloadSim = value;
    }

}
