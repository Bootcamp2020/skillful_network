import { Component, OnInit, Input } from '@angular/core';
import { MOCK_SKILL, IPost } from 'src/app/shared/models/mock.skill';
import { Skill } from 'src/app/shared/models/skill';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-skill-conf',
  templateUrl: './skill-conf.component.html',
  styleUrls: ['./skill-conf.component.scss']
})

export class SkillConfComponent implements OnInit {

  addSkillFormGroup: FormGroup;
  titleAlert: string = 'This field is required';
  post: any = '';

  public listSkill: Skill[];
  @Input() public skill: string;

  constructor(private formBuilder: FormBuilder) {

  }

  ngOnInit(): void {
    this.createForm();
    this.listSkill = [];
    MOCK_SKILL.forEach((skillu: IPost) => {
      this.listSkill.push(new Skill(skillu));
    });  
  }

  createForm() {
    this.addSkillFormGroup = this.formBuilder.group({
      'newSkill': [null, Validators.required]
    });
  }

  addSkill() {
    this.listSkill.push(new Skill({skill: this.addSkillFormGroup.value['newSkill']}));
  }
}


