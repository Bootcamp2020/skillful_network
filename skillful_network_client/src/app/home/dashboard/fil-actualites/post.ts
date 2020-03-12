import { IPost } from './post.mock';

export class Post {
    private _actu: string;
    private _lien: string;
    constructor(data: IPost) {
        this.actu = data.actu;
        this.lien = data.lien;
    }

    get actu(): string {
        return this._actu;
    }

    set actu(value: string) {
        this._actu = value;
    }

    get lien(): string {
        return this._lien;
    }

    set lien(value: string) {
        this._lien = value;
    }

}
