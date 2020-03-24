import { Component, OnInit, Input } from '@angular/core';
import { MOCK_SUBSCRIPT, IPost } from 'src/app/shared/models/mock.subscript';
import { Subscript } from 'src/app/shared/models/subscript';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-subscript',
  templateUrl: './subscript.component.html',
  styleUrls: ['./subscript.component.scss']
})
export class SubscriptComponent implements OnInit {

  addSubscriptFormGroup: FormGroup;
  titleAlert: string = 'This field is required';
  post: any = '';

  public listSubscript: Subscript[];
  @Input() public subscript: string;

  constructor(private formBuilder: FormBuilder) {

  }

  ngOnInit(): void {
    
    this.listSubscript = [];
    MOCK_SUBSCRIPT.forEach((subscriptu: IPost) => {
      this.listSubscript.push(new Subscript(subscriptu));
    });  
  }
  

  

}
