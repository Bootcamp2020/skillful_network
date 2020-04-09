import {Injectable} from '@angular/core';
import {Training} from '../models/training';
import {MOCK_TRAINING} from '../models/mock.training';
import { ApiHelperService } from './api-helper.service';


@Injectable({
    providedIn: 'root'
})
export class TrainingService {
    public trainings: Training[];

    constructor(private api: ApiHelperService) {
    //     this.trainings = [];
    //     MOCK_TRAINING.forEach(training => {
    //         this.trainings.push(new Training(training));
    //     });
    }

    public findById(id: number): Training{
        return this.trainings[id];
    }

// Import depuis le MOCK
    // public findAllMock(): Training[] {
    //     return this.trainings;
    // }

// Import depuis le Backend
    public findAll(): Promise<any> {
        let promise = new Promise((resolve, reject) => {
            this.api.get({ endpoint: '/trainings' })
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

    public findAllByPage(page:number, 
                        size:number, 
                        sortOrder:String,
                        fieldToSort:String): Promise<any> {
        let promise = new Promise((resolve, reject) => {
            this.api.get({ endpoint: '/trainings/' ,
              queryParams: {"page":page, "size":size, "sortOrder":sortOrder, "field":fieldToSort}})
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
