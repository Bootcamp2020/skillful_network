import {Injectable} from '@angular/core';
import {Training} from '../models/training';
import {MOCK_TRAINING} from '../models/mock.training';


@Injectable({
    providedIn: 'root'
})
export class TrainingService {
    public trainings: Training[];

    constructor() {
        this.trainings = [];
        MOCK_TRAINING.forEach(training => {
            this.trainings.push(new Training(training));
        });
    }

    public findById(id: number): Training{
        return this.trainings[id];
    }

// Import depuis le MOCK
    public findAllMock(): Training[] {
        return this.trainings;
    }

// Import depuis le Backend
    public findAll(): Training[] {
        return this.trainings;
    }

}
