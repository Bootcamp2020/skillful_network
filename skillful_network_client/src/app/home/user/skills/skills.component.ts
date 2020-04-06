import { Component, OnInit, Input } from '@angular/core';
import { Skill } from 'src/app/shared/models/skill';

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.scss']
})
export class SkillsComponent implements OnInit {
    @Input()public listSkill: Skill[];
  constructor() {

  }

  ngOnInit(): void {
  }
}
