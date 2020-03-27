import { Component, OnInit, Input } from '@angular/core';
import { MOCK_SKILL, IPost } from 'src/app/shared/models/mock.skill';
import { Skill } from 'src/app/shared/models/skill';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.scss']
})
export class SkillsComponent implements OnInit {
  addSkillFormGroup: FormGroup;
  titleAlert: string = 'This field is required';
  post: any = '';

  public listSkill: Skill[];
  @Input() public skill: string;

  constructor(private formBuilder: FormBuilder) {

  }

  ngOnInit(): void {
    
    this.listSkill = [];
    MOCK_SKILL.forEach((skillu: IPost) => {
      this.listSkill.push(new Skill(skillu.toString()));
    });  
  }


}
