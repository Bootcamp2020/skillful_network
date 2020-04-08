import {IReturnAnswerSet, MOCK_RETURN_ANSWER_SET} from './mock.return-answerSet';

export interface IReturnExerciceSet {
    exerciceSet: IReturnAnswerSet[];
}

export const MOCK_RETURN_EXERCICE_SET: IReturnExerciceSet[] = [
    {
        exerciceSet: [
            MOCK_RETURN_ANSWER_SET[0],
            MOCK_RETURN_ANSWER_SET[1]
        ]
    }
];
