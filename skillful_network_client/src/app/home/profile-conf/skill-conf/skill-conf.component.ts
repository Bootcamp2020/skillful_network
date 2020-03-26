import { Component, OnInit, Input, SimpleChanges, OnChanges } from '@angular/core';
import { Skill } from 'src/app/shared/models/skill';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { UserService } from 'src/app/shared/services/user.service';

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
  skills:string[];
  public listSkill: Skill[];
  public skill: string;
  

  constructor(private formBuilder: FormBuilder , private service : UserService) {}

  ngOnInit(): void {
    this.listSkill =  this.userSkilList;
    this.skillInfoGroup.value['skillSet'] = this.listSkill;
    console.log(this.skillInfoGroup);  
   

    this.skillInfoGroup.valueChanges.subscribe(data=>{
      this.skills = []
      if (data.skillUnit.length >1){
        this.service.findByContain("skills",data.skillUnit).then(
            datas=>{
              for(let id in datas)
              this.skills.push(datas[id].name)  
            }
        )
      }
    })
  }

  myControl = new FormControl()

  addSkill() {
    this.listSkill.push(new Skill(this.skillInfoGroup.value['skillUnit']));
    this.skillInfoGroup.value['skillSet'] = this.listSkill;
  }
}


