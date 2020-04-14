import {Component, Input, OnInit} from '@angular/core';
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
<<<<<<< HEAD
  public listChip: ChipValue[];
=======
  @Input() public listQualif: Qualif[];
>>>>>>> 1a34c34f5038dff7fa9d5950e9414b6cb9d21b14
  constructor(private formBuilder: FormBuilder,  private api: ApiHelperService,  private route: ActivatedRoute) {

  }

  ngOnInit(): void {

   /* const {id} = this.route.snapshot.params;
    console.log(id);
    // @ts-ignore
    this.api.get({endpoint: `/users/${id}/Qualifications`})
        .then(data => this.listChip = data)
        .catch((error) => {
          console.log('cet utilisateur n\'existe pas');
        });*/

  }

}
