import { Component, OnInit, Input } from '@angular/core';
import { Skill } from 'src/app/shared/models/skill';
import { FormBuilder, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-skill-conf',
  templateUrl: './skill-conf.component.html',
  styleUrls: ['./skill-conf.component.scss']
})

export class SkillConfComponent implements OnInit {
  @Input() skillInfoGroup : FormGroup; 
  @Input() userSkilList : Skill[]; 

  titleAlert: string = 'This field is required';
  post: any = '';

  public listSkill: Skill[];
  public skill: string;
  

  constructor(private formBuilder: FormBuilder) {

  }

  ngOnInit(): void {
    this.listSkill =  this.userSkilList;
    this.skillInfoGroup.value['skillSet'] = this.listSkill;
    console.log(this.skillInfoGroup);  
  }

  addSkill() {
    this.listSkill.push(new Skill(this.skillInfoGroup.value['skillUnit']));
    this.skillInfoGroup.value['skillSet'] = this.listSkill;
  }
}


