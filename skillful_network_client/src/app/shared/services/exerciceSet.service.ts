import {Injectable} from '@angular/core';
import {ApiHelperService} from './api-helper.service';
import {ExerciceSet} from '../models/exerciceSet';
import {MOCK_EXERCICE_SET} from '../models/mock.exerciceSet';
import {ReturnExerciceSet} from '../models/return-exerciceSet';

@Injectable({
    providedIn: 'root'
})
export class ExerciceSetService {
    public exercicesSet: ExerciceSet[];
    public returnExercicesSet: ReturnExerciceSet;

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
            this.api.get({ endpoint: '/simulation' })
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
    public export(returnExercicesSet): Promise<any> {
        let promise = new Promise((resolve, reject) => {
            this.api.post({ endpoint: '/simulations/:id/answer'})
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
