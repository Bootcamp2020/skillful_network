import { Component, OnInit, Input } from '@angular/core';
import { ChipValue } from 'src/app/shared/models/chip-value';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';
import { UserService } from 'src/app/shared/services/user.service';

@Component({
  selector: 'app-chip-conf',
  templateUrl: './chip-conf.component.html',
  styleUrls: ['./chip-conf.component.scss']
})
export class ChipConfComponent implements OnInit {
  @Input() chipInfoGroup : FormGroup; 
  @Input() userChipList : ChipValue[]; 
  @Input() title : string;
  @Input() label : string;
  @Input() detail : string;

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
    this.listChip =  this.userChipList;
    this.chipInfoGroup.value['chipSet'] = this.listChip;
    this.chipInfoGroup.valueChanges.subscribe(data=>{
      this.isLoading=false;
      this.chips = []
      if (data.any.length >1){
        this.isLoading=true;
        this.service.findByContain("",data.any).then(
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
    this.listChip.push(new ChipValue(this.chipInfoGroup.value['chipUnit']));
    this.chipInfoGroup.value['chipSet'] = this.listChip;
    
  }
  
  
  removeChip(chip : ChipValue): void {
    const index = this.listChip.indexOf(chip);
    if (index >=0) {
      this.listChip.splice(index, 1);
    }
  
  }
 
}