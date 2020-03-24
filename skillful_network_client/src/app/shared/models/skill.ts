import { IPost } from './mock.skill';

export class Skill {

    private _id: String;
    private _skill: String;

    constructor(data: IPost) {
        this.skill = data.skill;
      }
    
    public get id_1(): String {
        return this._id;
    }
    public set id_1(value: String) {
        this._id = value;
    }
    public get skill(): String {
        return this._skill;
    }
    public set skill(value: String) {
        this._skill = value;
    }
    
}
