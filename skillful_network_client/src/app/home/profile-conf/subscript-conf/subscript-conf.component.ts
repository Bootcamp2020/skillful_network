import { Component, OnInit, Input } from '@angular/core';
import { Subscript } from 'src/app/shared/models/subscript';
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
  @Input() subscriptInfoGroup : FormGroup; 
  @Input() userSubscriptList : Subscript[]; 

  titleAlert: string = 'This field is required';
  post: any = '';
  subscripts:string[];
  public listSubscript: Subscript[];
  public subscript: string;
  isLoading:boolean;
  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;


  constructor(private formBuilder: FormBuilder ,private service : UserService) {

  }

  ngOnInit(): void {
    this.listSubscript =  this.userSubscriptList;
    this.subscriptInfoGroup.value['subscriptionSet'] = this.listSubscript; 
    this.subscriptInfoGroup.valueChanges.subscribe(data=>{
      this.isLoading=false;
      this.subscripts = []
      if (data.subscriptUnit.length >1){
        this.isLoading=true;
        this.service.findByContain("subscriptions",data.subscriptUnit).then(
          datas=>{
            for(let id in datas){
            this.subscripts.push(datas[id].name)  
            }
            this.isLoading = false;
          }
        )
      }
    })
  }

  myControl = new FormControl()

  addSubscript() {
    this.listSubscript.push(new Subscript(this.subscriptInfoGroup.value['subscriptUnit']));
    this.subscriptInfoGroup.value['subscriptionSet'] = this.listSubscript;
  }


  removeSubscript(subscript : Subscript): void {
    const index = this.listSubscript.indexOf(subscript);
    if (index >= 0) {
      this.listSubscript.splice(index, 1);
    }
  }
}