export interface IPost {
    status: string;
    titleJob: string;
    company: string;
    description: string;
    keywords: string;
    level: string;
    technology: string;
    titletraining: string;
    prerequisite: string;
    skill: string;
    duration: number;
    risk: string;
    complexity: string;



}
export let MOCK_OFFRE: IPost = 
{

    status: 'Offre d\'emploi',
    titleJob: 'Développeur Java Fullstack',
    company: 'WebAppsEnt',
    description: 'Développemnt et maintenance d\'interface web pour différents clients. Les sites web ont pour mission d\'afficher ' +
                    'simplement des informations pour les clients',
    keywords: 'HTML5, CSS, Développement, Web',
    level: 'Bac/Bac +2/3',
    technology: 'HTML5, CSS',
    titletraining: 'Formation développeur front-end',
    prerequisite: 'Minimum Bac',
    skill: 'HTML, CSS',
    duration: 24,
    risk: 'simple',
    complexity: 'modérée'

};
