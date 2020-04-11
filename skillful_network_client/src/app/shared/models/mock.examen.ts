export interface IExam {
    id: number;
    exerciceSet: IExercice[];
}

export interface IExercice {
  id: number;
  name: string;
  questions: IQuestion[];
}

export interface IQuestion {
  id: number;
  question: string;
  choices: IChoice[];
  indexAnswer: number;
  feedBack: string;
  userAnswerId: number;
}

export interface IChoice {
  id: number;
  choice: string;
}

// Formulaire de réponse de l'utilisateur

export interface IExamForm {
  id: number;
  exerciceSet: IExerciceForm[];
}

export interface IExerciceForm {
  id: number;
  questions: IQuestionForm[];
}

export interface IQuestionForm {
  id: number;
  questionId: number;
  userAnswerId: number;
}

export let MOCK_EXAM : IExam = {
  id: 44,
  exerciceSet: [
    {
      id: 1,
      name: "Spring Quizz",
      questions: [
        {
          id: 9,
          question: "Le langage d'expressions (ou Expression Language) fait partie de Core Container dans le Framework Spring?",
          choices: [
            {
                id: 31,
                choice: "Faux"
            },
            {
                id: 30,
                choice: "Vrai"
            }
        ],
          indexAnswer: 30,
          feedBack: "SpEL signifie Spring Expression Language qui fait partie de Core Container. C'est un langage d'expression puissant qui prend en charge les requêtes et la manipulation d'un graphe d'objet au moment de la création du bean ou de l'exécution. Il est similaire à d'autres langages d'expression tels que JSP EL, OGNL, MVEL et JBoss EL, etc., avec quelques fonctionnalités supplémentaires telles que l'appel de méthode et la modélisation de chaînes de base.",
          "userAnswerId": null,
        },
        {
          "id": 8,
          "question": "AOP fait partie de Core Container dans le Framework Spring?",
          "choices": [
              {
                  "id": 28,
                  "choice": "Vrai"
              },
              {
                  "id": 29,
                  "choice": "Faux"
              }
          ],
          "indexAnswer": 28,
          "feedBack": "AOP (Aspect-oriented programming) ne fait pas partie de Core Container, est l'un des composants clés du Framework Spring, est une approche de programmation qui permet aux propriétés d'un programme de déterminer comment ils sont compilées dans un programme exécutable. AOP complète les régles de POO en offrant également une modularité. AOP décompose la logique du programme en parties distinctes appelées « concerns ». Cela augmente la modularité par des « cross- cutting concerns »",
          "userAnswerId": null,
        },
      ]
    },
    {
      id: 2,
      name: "Angular Quizz",
      questions: [
        {
          id: 3,
          question: "A quoi sert un transpileur ?",
          choices:  [
            {
                id: 13,
                choice: "Convertir du code JavaScript du standart ECMAScript 6 vers ECMAScript 5"
            },
            {
                id: 12,
                choice: "Modifier le comportement de sa page web sur les mobiles"
            },
            {
                id: 10,
                choice: "Convertir son code TypeScript en code JavaScript"
            },
            {
                id: 11,
                choice: "Améliorer la qualité de son code JavaScript"
            }
        ],
          indexAnswer: 10,
          feedBack: "SpEL signifie Spring Expression Language qui fait partie de Core Container. C'est un langage d'expression puissant qui prend en charge les requêtes et la manipulation d'un graphe d'objet au moment de la création du bean ou de l'exécution. Il est similaire à d'autres langages d'expression tels que JSP EL, OGNL, MVEL et JBoss EL, etc., avec quelques fonctionnalités supplémentaires telles que l'appel de méthode et la modélisation de chaînes de base.",
          "userAnswerId": null,
        },
        {
          "id": 1,
          "question": "Angular est un framework orienté ... ",
          "choices": [
              {
                  "id": 3,
                  "choice": "JavaScript"
              },
              {
                  "id": 2,
                  "choice": "modules"
              },
              {
                  "id": 4,
                  "choice": "au Nord !"
              },
              {
                  "id": 1,
                  "choice": "composants"
              }
          ],
          "indexAnswer": 1,
          "feedBack": "Angular est un framework orienté composants : votre application entière est un assemblage de composants !",
          "userAnswerId": null,
        },
      ]
    }
  ]
};

export let MOCK_EXAM_FORM : IExamForm = {
  "id": 3,
  "exerciceSet": [
    {
      "id": 1,
      "questions": [
        {
          "id": 1,
          "questionId": 9,
          "userAnswerId": 31
        },
        {
          "id": 2,
          "questionId": 8,
          "userAnswerId": 28
        }
      ]
    },
    {
      "id": 2,
      "questions": [
        {
          "id": 1,
          "questionId": 3,
          "userAnswerId": 10
        },
        {
          "id": 2,
          "questionId": 1,
          "userAnswerId": 3
        }
      ]
    },
  ]
};