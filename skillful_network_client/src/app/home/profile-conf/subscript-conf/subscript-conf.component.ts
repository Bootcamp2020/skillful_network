import { Component, OnInit, Input } from '@angular/core';
import { MOCK_SUBSCRIPT, IPost } from 'src/app/shared/models/mock.subscript';
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

  addSubscriptFormGroup: FormGroup;
  titleAlert: string = 'This field is required';
  post: any = '';
  subscripts:string[];
  public listSubscript: Subscript[];
  @Input() public subscript: string;

  constructor(private formBuilder: FormBuilder ,private service : UserService) {

  }

  ngOnInit(): void {
    this.createForm();
    this.listSubscript = [];
    MOCK_SUBSCRIPT.forEach((subscriptu: IPost) => {
      this.listSubscript.push(new Subscript(subscriptu));
    });  
    
    this.addSubscriptFormGroup.valueChanges.subscribe(data=>{
      this.subscripts = []
      if (data.newSubscript.length >1){
        this.service.findByContain("subscriptions",data.newSubscript).then(
          datas=>{
            for(let id in datas)
            this.subscripts.push(datas[id].name)  
          }
        )
      }
    })
  }

  myControl = new FormControl()
  
  

  createForm() {
    this.addSubscriptFormGroup = this.formBuilder.group({
      'newSubscript': [null, Validators.required]
    });
  }

  addSubscript() {
    this.listSubscript.push(new Subscript({subscript: this.addSubscriptFormGroup.value['newSubscript']}));
  }
}


