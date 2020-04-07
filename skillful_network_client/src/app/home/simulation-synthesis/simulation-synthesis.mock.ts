export interface IPost {
    status: string;
    titreSimulation: string;
    entreprise: string;
    description: string;
    motsCles: string;
    niveau: string;
    environement: string;
    nomFormation: string;
    presRequis: string;
    competence: string;
    duree: number;



}
export let MOCK_SIMULATION_SYNTHESIS: IPost = 
{

    status: 'SIMULATION',
    titreSimulation: 'Développeur Java Fullstack',
    entreprise: 'WebAppsEnt',
    description: 'Développemnt et maintenance d\'interface web pour différents clients. Les sites web ont pour mission d\'afficher ' +
                    'simplement des informations pour les clients',
    motsCles: 'HTML5, CSS, Développement, Web',
    niveau: 'Bac/Bac +2/3',
    environement: 'HTML5, CSS',
    nomFormation: 'Formation développeur front-end',
    presRequis: 'Minimum Bac',
    competence: 'HTML, CSS',
    duree: 24,

};