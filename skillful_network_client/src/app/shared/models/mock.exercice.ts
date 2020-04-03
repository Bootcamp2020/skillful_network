export interface IQuestion {
    id: number;
    question: string;
    choices: string[];
    indexAnswer: number;
    feedBack: string;
}

export interface IExercice {
    questions: IQuestion[];
    id: number;
    name: string;
    type: string;
    keywords: string[];
}

export const MOCK_EXERCICE: IExercice[] = [
    {
        questions: [
            {
                id: 1,
                question: "Angular est un framework orienté ... ",
                choices: [
                    "composants",
                    "modules",
                    "JavaScript",
                    "au Nord !"
                ],
                indexAnswer: 1,
                feedBack: "Angular est un framework orienté composants : votre application entière est un assemblage de composants !"
            },
            {
                id: 2,
                question: "Quelle entreprise est à l\u0027origine d\u0027Angular ?",
                choices: [
                    "Amazon",
                    "Facebook",
                    "Google",
                    "Apple",
                    "Microsoft"
                ],
                indexAnswer: 2,
                feedBack: "Angular a été crée par Google : Google a officiellement annoncé Angular à la conférence ng-conf en Octobre 2014."
            },
            {
                id: 3,
                question: "À quoi sert un transpileur ?",
                choices: [
                    "Convertir son code TypeScript en code JavaScript",
                    "Améliorer la qualité de son code JavaScript",
                    "Modifier le comportement de sa page web sur les mobiles",
                    "Convertir du code JavaScript du standart ECMAScript 6 vers ECMAScript 5"
                ],
                indexAnswer: 3,
                feedBack: "Le transpilateur est un outil qui permet de publier son code pour les navigateurs qui ne supportent pas encore l\u0027ES6 : le rôle du transpilateur est de traduire le code ES6 en code ES5."
            }
            ,
            {
                id: 4,
                question: "Quel est le langage recommandé pour le développement d\u0027applications Angular ?",
                choices: [
                    "Dart",
                    "TypeScript",
                    "JavaScript"
                ],
                indexAnswer: 1,
                feedBack: " 4) Quel est le langage recommandé pour le développement 1/1 d\u0027applications Angular ?Dart\nTypeScript\nJavaScript\nFeedback\nL\u0027équipe fondatrice d\u0027Angular recommande fortement d\u0027utiliser TypeScript. D\u0027ailleurs, TypeScript est le seul langage utilisé sur la documentation officielle de Angular"
            }
        ],
        id: 1,
        name: "Angular Quizz",
        type: "Questions",
        keywords: [
            "Angular",
            "Application Web",
            "Fullstack"
        ]
    },
    {
        questions: [
            {
                id: 5,
                question: "Qu’est-ce que l’injection de dépendance?",
                choices: [
                    "C’est un design pattern qui implémente le pattern Inversion de contrôle(inversion of control, IoC) pour des applications logicielles.",
                    "C’est l’un des modules de Spring.",
                    "C’est une technique pour obtenir des dépendances de n’importe quel projet.",
                    "Il est utilisé pour promouvoir un couplage étroit dans le code."
                ],
                indexAnswer: 0,
                feedBack: "Injection de dépendance est un design pattern qui implémente le pattern Inversion de contrôle pour des applications logicielles."
            },
            {
                id: 6,
                question: "Quels types d’injection de dépendance que Spring prend-il en charge?",
                choices: [
                    "Basé sur le constructeur et les setters",
                    "Basé sur le constructeur, les setters, et les getters",
                    "Basé sur les setters, les getters, et les propriétés",
                    "Basé sur le constructeur, les setters, et les propriétés"
                ],
                indexAnswer: 0,
                feedBack: "Spring prend en charge les injections basées sur le constructeur et sur les setters."
            },
            {
                id: 7,
                question: "Lequel des énoncés suivants est correct concernant le Framework Spring ?",
                choices: [
                    "Le Framework Spring est une solution lourde(heavy-weight solution).",
                    "Le Framework Spring est une solution légère(light-weight solution).",
                    "Les deux A et B sont vrais.",
                    "Aucune de ces réponses n’est vraie, même pas la réponse D."
                ],
                indexAnswer: 1,
                feedBack: "De nombreuses raisons font du spring un framework léger.Spring vous fournit différents modules et vous permet d’utiliser celui qui vous convient le mieux. Idéalement, le fichier JAR de spring ne mesure que 2 à 3 MB. Si vous comparez Spring avec EJB, vous devez écrire beaucoup moins de code et de configurations. La beauté de Spring réside dans le fait que vous pouvez vous concentrer sur la logique métier alors que dans EJB, vous devez écrire beaucoup de code en même temps que la logique métier qui le rend encombrant et étroitement couplé.Avec Spring, vous jouez avec POJO qui ne dépend pas d’un Framework et améliore la testabilité de votre code. Spring propose une intégration transparente avec les frameworks, les bibliothèques tierces, etc."
            }
            ,
            {
                id: 8,
                question: "AOP fait partie de Core Container dans le Framework Spring?",
                choices: [
                    "Vrai.",
                    "Faux"
                ],
                indexAnswer: 1,
                feedBack: "AOP (Aspect-oriented programming) ne fait pas partie de Core Container, est l’un des composants clés du Framework Spring, est une approche de programmation qui permet aux propriétés d’un programme de déterminer comment ils sont compilées dans un programme exécutable. AOP complète les régles de POO en offrant également une modularité. AOP décompose la logique du programme en parties distinctes appelées « concerns ». Cela augmente la modularité par des « cross- cutting concerns »"
            }
            ,
            {
                id: 9,
                question: "Le langage d’expressions (ou Expression Language) fait partie de Core Container dans le Framework Spring?",
                choices: [
                    "Vrai.",
                    "Faux"
                ],
                indexAnswer: 0,
                feedBack: "SpEL signifie Spring Expression Language qui fait partie de Core Container. C’est un langage d’expression puissant qui prend en charge les requêtes et la manipulation d’un graphe d’objet au moment de la création du bean ou de l’exécution. Il est similaire à d’autres langages d’expression tels que JSP EL, OGNL, MVEL et JBoss EL, etc., avec quelques fonctionnalités supplémentaires telles que l’appel de méthode et la modélisation de chaînes de base."
            }
        ],
        id: 2,
        name: "Spring Quizz",
        type: "Questions",
        keywords: [
            "Spring",
            "Application Web",
            "Fullstack",
            "Java"
        ]
    }
];
