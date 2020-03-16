export interface IPost {
    actu: string;
    lien: string;

}
export let MOCK_POSTS: IPost[] = [
    {

        actu: 'Les femmes demandeuses d\'emploi inscrites à Pôle emploi en 2019',
        lien: 'http://www.pole-emploi.org/statistiques-analyses/demandeurs-demploi/trajectoires-et-retour-a-lemploi/les-femmes-demandeuses-demploi-inscrites-a-pole-emploi-en-2019.html?type=article',

    },
    {

        actu: '« Les « Mad skills » permettent de mieux adresser la complexité » ',
        lien: 'http://www.emploiparlonsnet.pole-emploi.org/articles/les-mad-skills-permettent-de-mieux-adresser-la-complexite/',


    },
    {

        actu: 'Les entrées en formation des demandeurs d\'emploi au 3e trimestre 2019 ',
        lien: 'https://statistiques.pole-emploi.org/formation/formpub/202177',

    },
    {

        actu: 'L\'accès à l\'emploi des demandeurs d\'emploi inscrits à Pôle emploi en mars 2019',
        // tslint:disable-next-line:max-line-length
        lien: 'http://www.pole-emploi.org/statistiques-analyses/demandeurs-demploi/trajectoires-et-retour-a-lemploi/lacces-a-lemploi-des-demandeurs-demploi-inscrits-a-pole-emploi-en-mars-2019.html?type=article',

    },
    {

        actu: 'Pôle emploi, premier soutien au recrutement des entreprises',
        // tslint:disable-next-line:max-line-length
        lien: 'http://www.pole-emploi.org/accueil/actualites/2018/pole-emploi-premier-soutien-au-recrutement-des-entreprises.html?type=article',

    },
    {

        actu: 'Pôle emploi, 1er soutien au recrutement des entreprises',
        lien: 'http://www.pole-emploi.org/accueil/actualites/infographies/pole-emploi-1er-soutien-au-recrutement-des-entreprises.html?type=article',

    }
];
