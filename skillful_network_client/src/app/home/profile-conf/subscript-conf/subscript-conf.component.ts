import { Component, OnInit, Input } from '@angular/core';
import { ChipValue } from 'src/app/shared/models/chip-value';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { UserService } from 'src/app/shared/services/user.service';

@Component({
  selector: 'app-subscript-conf',
  templateUrl: './subscript-conf.component.html',
  styleUrls: ['./subscript-conf.component.scss']
})

export class SubscriptConfComponent implements OnInit {
  @Input() ChipInfoGroup : FormGroup; 
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


  constructor(private formBuilder: FormBuilder ,private service : UserService) {

  }

  ngOnInit(): void {
    this.listChip =  this.userChipList;
    this.ChipInfoGroup.value['subscriptionSet'] = this.listChip; 
    this.ChipInfoGroup.valueChanges.subscribe(data=>{
      this.isLoading=false;
      this.chips = []
      if (data.subscriptUnit.length >1){
        this.isLoading=true;
        this.service.findByContain("subscriptions",data.subscriptUnit).then(
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
    this.listChip.push(new ChipValue(this.ChipInfoGroup.value['subscriptUnit']));
    this.ChipInfoGroup.value['subscriptionSet'] = this.listChip;
  }


  removeChip(chip : ChipValue): void {
    const index = this.listChip.indexOf(chip);
    if (index >= 0) {
      this.listChip.splice(index, 1);
    }
  }
}