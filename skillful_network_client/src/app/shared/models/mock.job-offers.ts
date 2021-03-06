export interface IJobOffer {
    id: number;
    name: string;
    company: string;
    description: string;
    type: string;
    dateBeg: Date;
    dateEnd: Date;
    dateUpload: Date;
    keywords: string[];
}

export let MOCK_JOBOFFER: IJobOffer[] = [
    {
      id: 1,
      name: 'Développeur FrontEnd',
      company: 'Softeam',
      description: 'Notre entreprise cherche un développeur FrontEnd pour consolider notre équipe ',
      type: 'CDI',
      dateBeg: new Date("04/11/2020"),
      dateEnd: new Date ("08/21/2021"),
      dateUpload: new Date ("08/21/2021"),
      keywords:["développeur", "Front-End", "Back-End", "Java", "Angular"]
    },
    {
        id: 2,
        name:'Développeur FullStack',
        company:'SopraSteria',
        description:'Notre entreprise cherche un développeur FullStack pour consolider notre équipe ',
        type: 'CDD',
        dateBeg: new Date ("05/11/2020"),
        dateEnd: new Date ("02/11/2020"),
        dateUpload: new Date ("03/25/2020"),
        keywords:["développeur", "Front-End", "Back-End", "Java", "Angular"]
      },
      {
        id: 3,
        name:'Développeur PHP',
        company:'Alten',
        description:'Notre entreprise cherche un développeur PHP expérimenté pour une mission client',
        type: 'CDI',
        dateBeg: new Date ("04/04/2020"),
        dateEnd: new Date ("01/08/2021"),
        dateUpload: new Date ("02/18/2020"),
        keywords:["développeur", "Front-End", "Back-End", "Java", "Angular", "PHP"]
      },
      {
        id: 4,
        name:'Développeur Java/J2E',
        company:'Astek',
        description:'Notre entreprise cherche un développeur Java pour un client',
        type: 'CDI',
        dateBeg: new Date ("01/05/2020"),
        dateEnd: new Date ("01/08/2022"),
        dateUpload: new Date ("01/03/2020"),
        keywords:["développeur", "Front-End", "Back-End", "Java", "Angular", "J2E"]
      },
      {
        id: 5,
        name:'Développeur Angular',
        company:'Amadeus',
        description:'Notre entreprise cherche un développeur Angular pour renforcer notre équipe ',
        type: 'CDI',
        dateBeg: new Date ("05/05/2020"),
        dateEnd: new Date ("08/31/2022"),
        dateUpload: new Date ("03/25/2020"),
        keywords:["développeur", "Front-End", "Back-End", "Java", "Angular"]
      },
    {
        id: 6,
        name:'Developpeur',
        company:'Company6',
        description:'linkjoboffer6',
        type: 'Stage',
        dateBeg: new Date(2020,4,21),
        dateEnd: new Date(2020,6,22),
        dateUpload: new Date(2019,5,23),
        keywords: ['JAVA'],
    },
    {
        id: 7,
        name: 'Codeur',
        company: 'Company7',
        description: 'linkjoboffer7',
        type: 'CDI',
        dateBeg: new Date(2020,2,21),
        dateEnd: new Date(2020,4,20),
        dateUpload: new Date(2019,3,15),
        keywords: ['PYTHON', 'ANGULAR'],
    },
    {
        id: 8,
        name:'Integrateur',
        company:'Company8',
        description:'linkjoboffer8',
        type: 'CDI',
        dateBeg: new Date(2020,11,3),
        dateEnd: new Date(2020,12,30),
        dateUpload: new Date(2019,6,25),
        keywords: ['PYTHON'],
    },
    {
        id: 9,
        name:'Codeur',
        company:'Company9',
        description:'linkjoboffer9',
        type: 'CDD',
        dateBeg: new Date(2020,3,20),
        dateEnd: new Date(2020,6,19),
        dateUpload: new Date(2019,4,13),
        keywords: ['JAVA', 'PYTHON', 'ANGULAR'],
    },
    {
        id: 10,
        name:'Developpeur',
        company:'Company3',
        description:'linkjoboffer10',
        type: 'CDI',
        dateBeg: new Date(2020,4,17),
        dateEnd: new Date(2020,6,10),
        dateUpload: new Date(2019,4,28),
        keywords: ['ANGULAR'],
    },
    {
        id: 11,
        name:'Developpeur',
        company:'Company1',
        description:'linkjoboffer11',
        type: 'CDD',
        dateBeg: new Date(2020,1,23),
        dateEnd: new Date(2020,4,30),
        dateUpload: new Date(2019,8,23),
        keywords: ['PYTHON', 'ANGULAR'],
    },
    {
        id: 12,
        name:'Scrumaster',
        company:'Company2',
        description:'linkjoboffer12',
        type: 'CDD',
        dateBeg: new Date(2020,1,3),
        dateEnd: new Date(2020,4,30),
        dateUpload: new Date(2019,6,25),
        keywords: ['PYTHON'],
    },
    {
        id: 13,
        name:'Codeur',
        company:'Company3',
        description:'linkjoboffer13',
        type: 'CDI',
        dateBeg: new Date(2020,3,23),
        dateEnd: new Date(2020,6,23),
        dateUpload: new Date(2019,6,23),
        keywords: ['JAVA', 'PYTHON', 'ANGULAR'],
    },
    {
        id: 14,
        name:'Developpeur',
        company:'Company4',
        description:'linkjoboffer14',
        type: 'Stage',
        dateBeg: new Date(2020,2,21),
        dateEnd: new Date(2020,6,22),
        dateUpload: new Date(2019,5,23),
        keywords: ['JAVA'],
    },
    {
        id: 15,
        name:'Codeur',
        company:'Company8',
        description:'linkjoboffer15',
        type: 'CDI',
        dateBeg: new Date(2020,7,21),
        dateEnd: new Date(2020,12,20),
        dateUpload: new Date(2020,3,15),
        keywords: ['PYTHON', 'ANGULAR'],
    },
    {
        id: 16,
        name:'Integrateur',
        company:'Company6',
        description:'linkjoboffer16',
        type: 'CDD',
        dateBeg: new Date(2020,11,3),
        dateEnd: new Date(2020,12,30),
        dateUpload: new Date(2019,6,25),
        keywords: ['PYTHON'],
    },
    {
        id: 17,
        name:'Developpeur',
        company:'Company7',
        description:'linkjoboffer17',
        type: 'CDD',
        dateBeg: new Date(2020,2,23),
        dateEnd: new Date(2020,4,23),
        dateUpload: new Date(2019,4,23),
        keywords: ['ANGULAR'],
    },
    {
        id: 18,
        name:'Scrumaster',
        company:'Company8',
        description:'linkjoboffer18',
        type: 'CDI',
        dateBeg: new Date(2020,1,3),
        dateEnd: new Date(2020,4,30),
        dateUpload: new Date(2019,6,25),
        keywords: ['PYTHON'],
    },
    {
        id: 19,
        name:'Developpeur',
        company:'Company5',
        description:'linkjoboffer19',
        type: 'CDI',
        dateBeg: new Date(2020,3,23),
        dateEnd: new Date(2020,6,23),
        dateUpload: new Date(2019,6,23),
        keywords: ['JAVA'],
    },
    {
        id: 20,
        name:'Developpeur',
        company:'Company6',
        description:'linkjoboffer20',
        type: 'Stage',
        dateBeg: new Date(2020,4,21),
        dateEnd: new Date(2020,6,22),
        dateUpload: new Date(2019,5,23),
        keywords: ['JAVA'],
    }
]
