import {Injectable} from '@angular/core';
import {ApiHelperService} from './api-helper.service';
import {ExerciceSet} from '../models/exerciceSet';
import {MOCK_EXERCICE_SET} from '../models/mock.exerciceSet';

@Injectable({
    providedIn: 'root'
})
export class ExerciceSetService {
    public exercicesSet: ExerciceSet[];
    public returnExercicesSet: any;
    public userId:number;

    constructor(private api: ApiHelperService) {
        this.exercicesSet = [];
        MOCK_EXERCICE_SET.forEach(exo => {
            this.exercicesSet.push(new ExerciceSet(exo));
        });
    }

    public findById(id: number): ExerciceSet {
        return this.exercicesSet[id];
    }

// Import depuis le MOCK
    public findAllMock(): ExerciceSet[] {
        return this.exercicesSet;
    }

// Import depuis le Backend
    public findAll(): Promise<any> {
        let promise = new Promise((resolve, reject) => {
            this.api.get({ endpoint: '/simulations/user' })
                .then(
                    res => {
                        resolve(res);
                    },
                    msg => {
                        reject(msg);
                    }
                ).catch((error) => {
            });
        });
        return promise;
    }

// Envoi au Backend
    public export(idexam, returnExercicesSet): Promise<any> {
        let promise = new Promise((resolve, reject) => {
            this.api.post({ endpoint: '/simulations/' + idexam + '/answer', data: returnExercicesSet})
                .then(
                    res => {
                        resolve(res);
                    },
                    msg => {
                        reject(msg);
                    }
                ).catch((error) => {
            });
        });
        return promise;
    }
}
