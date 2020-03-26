
import { Candidature } from './../models/candidature';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class CandidatureService {
  constructor() { }

  verifierCandidature(idJobOffer, listCandidature:Candidature[] ): boolean{
    for(let i in listCandidature){
      if(listCandidature[i].job.id  === idJobOffer){
        return true;
      } 
    }
    return false;
  }

  getCandidatureByJobOfferId(idJobOffer, listCandidature:Candidature[]): Candidature{
    for(let i in listCandidature){
      if(listCandidature[i].job.id  === idJobOffer){
        return listCandidature[i];
      } 
    }
    return null;
  }
}
