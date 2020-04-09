import { Component, OnInit, Input } from '@angular/core';
import { ChipValue } from 'src/app/shared/models/chip-value';
import { FormBuilder} from '@angular/forms';
import {ApiHelperService} from '../../../shared/services/api-helper.service';
import {ActivatedRoute} from '@angular/router';


@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.scss']
})
export class SkillsComponent implements OnInit {
  public listChip: ChipValue[];
  constructor(private formBuilder: FormBuilder, private api: ApiHelperService,  private route: ActivatedRoute) {

  }

  ngOnInit(): void {
     const {id} =this.route.snapshot.params;
    console.log(id);
    this.api.get({endpoint: `/users/${id}/skills`})
        .then(data => this.listChip = data)
        .catch((error) => {
          console.log('cet utilisateur n\'existe pas');
        });

  }


}
