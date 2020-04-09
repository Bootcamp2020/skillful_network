import { Component, OnInit} from '@angular/core';
import { FormBuilder} from '@angular/forms';
import { ChipValue } from 'src/app/shared/models/chip-value';
import {ApiHelperService} from '../../../shared/services/api-helper.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-qualifications',
  templateUrl: './qualifications.component.html',
  styleUrls: ['./qualifications.component.scss']
})
export class QualificationsComponent implements OnInit {
  public listChip: ChipValue[];
  constructor(private formBuilder: FormBuilder,  private api: ApiHelperService,  private route: ActivatedRoute) {

  }

  ngOnInit(): void {

    const {id} = this.route.snapshot.params;
    console.log(id);
    // @ts-ignore
    this.api.get({endpoint: `/users/${id}/Qualifications`})
        .then(data => this.listChip = data)
        .catch((error) => {
          console.log('cet utilisateur n\'existe pas');
        });

  }

}
