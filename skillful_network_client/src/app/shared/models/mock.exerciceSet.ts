import {IExercice, MOCK_EXERCICE} from './mock.exercice';

export interface IExerciceSet {
    exerciceSet: IExercice[];
}

export const MOCK_EXERCICE_SET: IExerciceSet[] = [
    {
        exerciceSet: [
            MOCK_EXERCICE[0],
            MOCK_EXERCICE[1]
        ]
    }
];
