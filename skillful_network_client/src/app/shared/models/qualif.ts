import { IPost } from './mock.qualif';

export class Qualif {

    private _id: String;
    private _qualif: String;

    constructor(data: IPost) {
        this.qualif = data.qualif;
      }
    
    public get id_1(): String {
        return this._id;
    }
    public set id_1(value: String) {
        this._id = value;
    }
    public get qualif(): String {
        return this._qualif;
    }
    public set qualif(value: String) {
        this._qualif = value;
    }
    
}
