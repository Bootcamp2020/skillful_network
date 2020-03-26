import { Component, OnInit, Input } from '@angular/core';
import { MOCK_SKILL, IPost } from 'src/app/shared/models/mock.skill';
import { Skill } from 'src/app/shared/models/skill';
import { FormBuilder, FormGroup } from '@angular/forms';
import {ApiHelperService} from '../../../shared/services/api-helper.service';
import {ActivatedRoute} from '@angular/router';


@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.scss']
})
export class SkillsComponent implements OnInit {
  public listSkill: Skill[];
  @Input() public skill: string;
  constructor(private formBuilder: FormBuilder, private api: ApiHelperService,  private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    // this.listSkill = [];
    // MOCK_SKILL.forEach((skillu: IPost) => {
    // this.listSkill.push(new Skill(skillu));
    // });
    const {id} = this.route.snapshot.params;
    console.log(id);
    // @ts-ignore
    this.api.get({endpoint: `/users/${id}/skills`})
        .then(data => this.listSkill = data)
        .catch((error) => {
          console.log('cet utilisateur n\'existe pas');
        });
    console.log(this.listSkill);
  }


}
