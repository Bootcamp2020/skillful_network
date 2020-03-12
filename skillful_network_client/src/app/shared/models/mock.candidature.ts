export interface IPost {
    company: String;
    job: String;
    status: String;
    details: String;
}
export let MOCK_CANDIDATURE : IPost[] = [
    {
      company: 'Softeam',
      job:'Developpeur',
      status:'validate',
      details:'https://www.softeaminstitute.fr/'
    },
    {
        company: 'Pole emploi',
        job:'Developpeur JAVA/EE',
        status:'reject',
        details:'https://www.pole-emploi.fr/accueil/'
      },
      {
        company: 'Third company',
        job:'Developpeur Angular',
        status:'progress',
        details:'http://google.com'
      }
]