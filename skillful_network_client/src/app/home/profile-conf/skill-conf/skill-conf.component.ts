import { Component, OnInit, Input, SimpleChanges, OnChanges } from '@angular/core';
import { MOCK_SKILL, IPost } from 'src/app/shared/models/mock.skill';
import { Skill } from 'src/app/shared/models/skill';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { UserService } from 'src/app/shared/services/user.service';

@Component({
  selector: 'app-skill-conf',
  templateUrl: './skill-conf.component.html',
  styleUrls: ['./skill-conf.component.scss']
})

export class SkillConfComponent implements OnInit  {

  addSkillFormGroup: FormGroup;
  titleAlert: string = 'This field is required';
  post: any = '';
  skills:string[];
  public listSkill: Skill[];
  @Input() public skill: string;

  constructor(private formBuilder: FormBuilder , private service : UserService) {}

  ngOnInit(): void {
    this.createForm();
    this.listSkill = [];
    MOCK_SKILL.forEach((skillu: IPost) => {
      this.listSkill.push(new Skill(skillu));
    });  

    this.addSkillFormGroup.valueChanges.subscribe(data=>{
      this.skills = []
      if (data.newSkill.length >1){
        this.service.findByContain("skills",data.newSkill).then(
            datas=>{
              for(let id in datas)
              this.skills.push(datas[id].name)  
            }
        )
      }
    })
  }

  myControl = new FormControl()
 
  
  createForm() {
    this.addSkillFormGroup = this.formBuilder.group({
      'newSkill': [null, Validators.required]
    });
  }

  addSkill() {
    this.listSkill.push(new Skill({skill: this.addSkillFormGroup.value['newSkill']}));
  }
}


