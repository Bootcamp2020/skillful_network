import { Component, OnInit, Input } from '@angular/core';
import { Subscript } from 'src/app/shared/models/subscript';
import { FormBuilder, FormGroup} from '@angular/forms';

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

  public listSubscript: Subscript[];
  public subscript: string;
  

  constructor(private formBuilder: FormBuilder) {

  }

  ngOnInit(): void {
    this.listSubscript =  this.userSubscriptList;
    this.subscriptInfoGroup.value['subscriptionSet'] = this.listSubscript;
    console.log(this.subscriptInfoGroup);  
  }

  addSubscript() {
    this.listSubscript.push(new Subscript(this.subscriptInfoGroup.value['subscriptUnit']));
    this.subscriptInfoGroup.value['subscriptionSet'] = this.listSubscript;
  }
}