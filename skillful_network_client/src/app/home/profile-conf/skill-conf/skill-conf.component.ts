import { Component, OnInit, Input, SimpleChanges, OnChanges } from '@angular/core';
import { ChipValue } from 'src/app/shared/models/chip-value';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { UserService } from 'src/app/shared/services/user.service';

@Component({
  selector: 'app-skill-conf',
  templateUrl: './skill-conf.component.html',
  styleUrls: ['./skill-conf.component.scss']
})

export class SkillConfComponent implements OnInit {
  @Input() chipInfoGroup : FormGroup; 
  @Input() userChipList : ChipValue[]; 

  titleAlert: string = 'This field is required';
  post: any = '';
  chips:string[];
  public listChip: ChipValue[];
  public chip: string;
  isLoading:boolean;
  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;

  constructor(private formBuilder: FormBuilder , private service : UserService) {}

  ngOnInit(): void {
    
  }

  myControl = new FormControl()

  addChip() {
    this.listChip.push(new ChipValue(this.chipInfoGroup.value['skillUnit']));
    this.chipInfoGroup.value['skillSet'] = this.listChip;
  }

  removeChip(chip : ChipValue) {
    const index = this.listChip.indexOf(chip);
    if (index >= 0) {
      this.listChip.splice(index, 1);
    }
  }
}


