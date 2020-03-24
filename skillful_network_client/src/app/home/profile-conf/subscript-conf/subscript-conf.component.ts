import { Component, OnInit, Input } from '@angular/core';
import { MOCK_SUBSCRIPT, IPost } from 'src/app/shared/models/mock.subscript';
import { Subscript } from 'src/app/shared/models/subscript';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-subscript-conf',
  templateUrl: './subscript-conf.component.html',
  styleUrls: ['./subscript-conf.component.scss']
})
export class SubscriptConfComponent implements OnInit {

  addSubscriptFormGroup: FormGroup;
  titleAlert: string = 'This field is required';
  post: any = '';

  public listSubscript: Subscript[];
  @Input() public subscript: string;

  constructor(private formBuilder: FormBuilder) {

  }

  ngOnInit(): void {
    this.createForm();
    this.listSubscript = [];
    MOCK_SUBSCRIPT.forEach((subscriptu: IPost) => {
      this.listSubscript.push(new Subscript(subscriptu));
    });  
  }

  createForm() {
    this.addSubscriptFormGroup = this.formBuilder.group({
      'newSubscript': [null, Validators.required]
    });
  }

  addSubscript() {
    this.listSubscript.push(new Subscript({subscript: this.addSubscriptFormGroup.value['newSubscript']}));
  }
}


