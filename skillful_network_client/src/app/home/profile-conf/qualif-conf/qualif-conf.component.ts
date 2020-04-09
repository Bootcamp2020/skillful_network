import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { ChipValue } from 'src/app/shared/models/chip-value';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import {MatAutocompleteSelectedEvent, MatAutocomplete} from '@angular/material/autocomplete';
import { UserService } from 'src/app/shared/services/user.service';


@Component({
  selector: 'app-qualif-conf',
  templateUrl: './qualif-conf.component.html',
  styleUrls: ['./qualif-conf.component.scss']
})

export class QualifConfComponent implements OnInit {
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
  removable =true;
  addOnBlur = true;
  



 
  constructor(private formBuilder: FormBuilder , private service : UserService,) {
  }

  ngOnInit(): void {
    this.listChip =  this.userChipList;
    this.chipInfoGroup.value['qualificationSet'] = this.listChip;
    this.chipInfoGroup.valueChanges.subscribe(data=>{
      this.isLoading=false;
      this.chips = []
      if (data.qualifUnit.length >1){
        this.isLoading=true;
        this.service.findByContain("qualifications",data.qualifUnit).then(
          datas=>{
            for(let id in datas){
            this.chips.push(datas[id].name)  
            }
            this.isLoading = false;
          }
        )
      }
    })
  }

  myControl = new FormControl()
  

  addChip() {
    this.listChip.push(new ChipValue(this.chipInfoGroup.value['qualifUnit']));
    this.chipInfoGroup.value['qualificationSet'] = this.listChip;
    
  }
  
  
  removeChip(chip : ChipValue): void {
    const index = this.listChip.indexOf(chip);
    if (index >=0) {
      this.listChip.splice(index, 1);
    }
  
  }
 
}
