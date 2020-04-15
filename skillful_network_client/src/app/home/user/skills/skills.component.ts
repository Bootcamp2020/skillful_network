import { Component, OnInit, Input } from '@angular/core';
import { ChipValue } from 'src/app/shared/models/chip-value';

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.scss']
})
export class SkillsComponent implements OnInit {
    @Input()public listSkill: ChipValue[];
  constructor() {

  }

  ngOnInit(): void {
  }
}
