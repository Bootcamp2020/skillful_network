import { Component, OnInit, Input } from '@angular/core';
<<<<<<< HEAD
import { ChipValue } from 'src/app/shared/models/chip-value';
import { FormBuilder} from '@angular/forms';
import {ApiHelperService} from '../../../shared/services/api-helper.service';
import {ActivatedRoute} from '@angular/router';

=======
import { Skill } from 'src/app/shared/models/skill';
>>>>>>> 1a34c34f5038dff7fa9d5950e9414b6cb9d21b14

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.scss']
})
export class SkillsComponent implements OnInit {
<<<<<<< HEAD
  public listChip: ChipValue[];
  constructor(private formBuilder: FormBuilder, private api: ApiHelperService,  private route: ActivatedRoute) {
=======
    @Input()public listSkill: Skill[];
  constructor() {
>>>>>>> 1a34c34f5038dff7fa9d5950e9414b6cb9d21b14

  }

  ngOnInit(): void {
<<<<<<< HEAD
     const {id} =this.route.snapshot.params;
    console.log(id);
    this.api.get({endpoint: `/users/${id}/skills`})
        .then(data => this.listChip = data)
        .catch((error) => {
          console.log('cet utilisateur n\'existe pas');
        });

=======
>>>>>>> 1a34c34f5038dff7fa9d5950e9414b6cb9d21b14
  }
}
