export interface IReturnQuestion {
    questionId: number;
    answer: number;
}

export interface IReturnAnswerSet {
    id: number;
    answerSet: IReturnQuestion[];
}

export const MOCK_RETURN_ANSWER_SET: IReturnAnswerSet[] = [
    {
        id: 1,
        answerSet: [
            {
                questionId : 1,
                answer : 2
            },
            {
                questionId : 2,
                answer : 4
            },
            {
                questionId : 3,
                answer : 3
            }
            ,
            {
                questionId : 4,
                answer : 1
            }
        ]
    },
    {
        id: 2,
        answerSet: [
            {
                questionId : 5,
                answer : 1
            },
            {
                questionId : 6,
                answer : 3
            },
            {
                questionId : 7,
                answer : 4
            }
            ,
            {
                questionId : 8,
                answer : 2
            }
            ,
            {
                questionId : 9,
                answer : 1
            }
        ]
    }
];
