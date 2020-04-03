import {Question} from './question';

export class Exercice {

  private _questions: Question[];
  private _id: number;
  private _name: string;
  private _type: string;
  private _keywords: string[];

  constructor(questions: Question[], id: number, name: string, type: string, keywords: string[]) {
    this._questions = questions;
    this._id = id;
    this._name = name;
    this._type = type;
    this._keywords = keywords;
  }

  get questions(): Question[] {
    return this._questions;
  }

  set questions(value: Question[]) {
    this._questions = value;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get type(): string {
    return this._type;
  }

  set type(value: string) {
    this._type = value;
  }

  get keywords(): string[] {
    return this._keywords;
  }

  set keywords(value: string[]) {
    this._keywords = value;
  }
}
