
import { Candidature } from './../models/candidature';
import { Injectable } from '@angular/core';
import { IPost } from '../models/mock.candidature';
import { ApiHelperService } from './api-helper.service';


@Injectable({
  providedIn: 'root'
})
export class CandidatureService {
  constructor(private api: ApiHelperService) { }

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


  async getAllUserApllications(userId) : Promise<Candidature[]>{
    let endPoint = '/applications/jobs/user/'+userId;
    try{
      let candidatures = await this.api.get({"endpoint":endPoint});
      return candidatures;
    }catch(ex){
      return null;
    }
    
    
  }
}
