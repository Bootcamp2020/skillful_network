import { IPost } from './simulation-synthesis.mock';

export class Post {
    private _status: string;
    private _titreSimulation: string;
    private _entreprise: string;
    private _description: string;
    private _motsCles: string;
    private _niveau: string;
    private _environement: string;
    private _nomFormation: string;
    private _presRequis: string;
    private _competence: string;
    private _duree: number;
    constructor(data: IPost) {
        this._status = data.status;
        this._titreSimulation = data.titreSimulation;
        this._entreprise = data.entreprise;
        this._description = data.description;
        this._motsCles = data.motsCles;
        this._niveau = data.niveau;
        this._environement = data.environement;
        this._nomFormation = data.nomFormation;
        this._presRequis = data.presRequis;
        this._competence = data.competence;
        this._duree = data.duree;

    }

    get status(): string {
        return this._status;
    }

    set status(value: string) {
        this._status = value;
    }

    get titreOffre(): string {
        return this._titreSimulation;
    }

    set titreOffre(value: string) {
        this._titreSimulation = value;
    }
    get entreprise(): string {
        return this._entreprise;
    }

    set entreprise(value: string) {
        this._entreprise = value;
    }
    get description(): string {
        return this._description;
    }

    set description(value: string) {
        this._description = value;
    }
    get motsCles(): string {
        return this._motsCles;
    }

    set motsCles(value: string) {
        this._motsCles = value;
    }
    get niveau(): string {
        return this._niveau;
    }

    set niveau(value: string) {
        this._niveau = value;
    }
    get environement(): string {
        return this._environement;
    }

    set environement(value: string) {
        this._environement = value;
    }
    get nomFormation(): string {
        return this._nomFormation;
    }

    set nomFormation(value: string) {
        this._nomFormation = value;
    }
    get presRequis(): string {
        return this._presRequis;
    }

    set presRequis(value: string) {
        this._presRequis = value;
    }
    get competence(): string {
        return this._competence;
    }

    set competence(value: string) {
        this._competence = value;
    }
    get duree(): number {
        return this._duree;
    }

    set duree(value: number) {
        this._duree = value;
    }

}