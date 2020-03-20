import { IPost } from './mock.subscript';

export class Subscript {

    private _id: String;
    private _subscript: String;

    constructor(data: IPost) {
        this.subscript = data.subscript;
      }
    
    public get id_1(): String {
        return this._id;
    }
    public set id_1(value: String) {
        this._id = value;
    }
    public get subscript(): String {
        return this._subscript;
    }
    public set subscript(value: String) {
        this._subscript = value;
    }
    
}
