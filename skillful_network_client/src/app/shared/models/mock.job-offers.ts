export interface IJobOffer {
    id: number;
    name: String;
    company: String;
    description: String;
    type: String;
    dateBeg:  Date;
    dateEnd:  Date;
    dateUpload: Date;
    keywords: string[];
}

export let MOCK_JOBS : IJobOffer[] = [
    {
      id: 1,
      name:'Développeur FrontEnd',
      company:'Softeam',
      description:'Notre entreprise cherche un développeur FrontEnd pour consolider notre équipe ',
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
      }
]