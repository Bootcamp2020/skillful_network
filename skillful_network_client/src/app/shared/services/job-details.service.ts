import {Injectable} from '@angular/core';
import {ApiHelperService} from './api-helper.service';
import {JobOffer} from '../models/job-offer';


@Injectable({
  providedIn: 'root'
})
export class JobDetailsService {
  public jobDetails: JobOffer[];
  constructor(private api: ApiHelperService) {
    this.jobDetails = [];
  }

  public findById(id: number): JobOffer {
    return this.jobDetails[id];
  }



// Import depuis le Backend
  public findAll(): Promise<any> {
    return new Promise((resolve, reject) => {

    });
  }
  }

