
import { Candidature } from './../models/candidature';
import { Injectable } from '@angular/core';
import { IPost } from '../models/mock.candidature';


@Injectable({
  providedIn: 'root'
})
export class CandidatureService {
  constructor() { }

  verifierCandidature(idJobOffer, listCandidature:IPost[] ): boolean{
    
    for(let i in listCandidature){
      if(listCandidature[i].job.id  === idJobOffer){
        return true;
      } 
    }
    return false;
  }

  recupererCandidature(idJobOffer, listCandidature:IPost[]): IPost{
    for(let i in listCandidature){
      if(listCandidature[i].job.id  === idJobOffer){
        return listCandidature[i];
      } 
    }
    return null;
  }
}
