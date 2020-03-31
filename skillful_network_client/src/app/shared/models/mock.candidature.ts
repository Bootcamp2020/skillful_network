
import { MOCK_JOBOFFER, IJobOffer } from './mock.job-offers';
import { JobOffer } from './job-offer';


export interface IPost {
    id: number;
    company: String;
    job: IJobOffer;
    status: String;
    details: String;
}
export let MOCK_CANDIDATURE : IPost[] = [
    {
      id: 1,
      company: 'Softeam',
      job:MOCK_JOBOFFER[0],
      status:'validate',
      details:'https://www.softeaminstitute.fr/'
    },
    {
        id: 2,
        company: 'Pole emploi',
        job:MOCK_JOBOFFER[1],
        status:'reject',
        details:'https://www.pole-emploi.fr/accueil/'
      },
      {
        id:3,
        company: 'Third company',
        job:MOCK_JOBOFFER[2],
        status:'progress',
        details:'http://google.com'
      }
]
