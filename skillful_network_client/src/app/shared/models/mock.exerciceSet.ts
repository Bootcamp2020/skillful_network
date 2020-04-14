import {IExercice, MOCK_EXERCICE} from './mock.exercice';

export interface IExerciceSet {
    exerciseSet: IExercice[];
}

export const MOCK_EXERCICE_SET: IExerciceSet[] = [
    {
        exerciseSet: [
            MOCK_EXERCICE[0],
            MOCK_EXERCICE[1]
        ]
    }
];
