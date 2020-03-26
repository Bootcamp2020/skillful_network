import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Qualif } from 'src/app/shared/models/qualif';
import { MOCK_QUALIF, IPost } from 'src/app/shared/models/mock.qualif';
import {ApiHelperService} from "../../../shared/services/api-helper.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-qualifications',
  templateUrl: './qualifications.component.html',
  styleUrls: ['./qualifications.component.scss']
})
export class QualificationsComponent implements OnInit {
  addQualifFormGroup: FormGroup;
  titleAlert: string = 'This field is required';
  post: any = '';

  public listQualif: Qualif[];
  @Input() public qualif: string;

  constructor(private formBuilder: FormBuilder,  private api: ApiHelperService,  private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    const {id} = this.route.snapshot.params;
    console.log(id);
    // @ts-ignore
    this.api.get({endpoint: `/users/${id}/Qualifications`})
        .then(data => this.listQualif = data)
        .catch((error) => {
          console.log('cet utilisateur n\'existe pas');
        });
    console.log(this.listQualif);
  }

}
