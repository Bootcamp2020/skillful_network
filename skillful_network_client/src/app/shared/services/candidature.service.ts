
import { Candidature } from './../models/candidature';
import { Injectable } from '@angular/core';
import { IPost } from '../models/mock.candidature';
import { ApiHelperService } from './api-helper.service';


@Injectable({
  providedIn: 'root'
})
export class CandidatureService {
  apiHelper: any;
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


  public getAllUserApllications(userId) :Promise<any> {
    let endPoint = '/applications/jobs/user/'+userId;
    let promise = new Promise((resolve, reject) => {
    this.apiHelper.get({"endpoint":endPoint, "queryParams":{}})
    .then(
      res => { 
        resolve(res);
      },
      msg => { 
        reject(msg);
        }
      ).catch((error) => {
      });
    });
    return promise;
  }
}
