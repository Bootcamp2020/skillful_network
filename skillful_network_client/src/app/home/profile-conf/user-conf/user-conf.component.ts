import { Component, Input } from '@angular/core';
import {  FormGroup } from '@angular/forms';

@Component({
  selector: 'app-user-conf',
  templateUrl: './user-conf.component.html',
  styleUrls: ['./user-conf.component.scss']
})

export class UserConfComponent {

  @Input() userInfoGroup : FormGroup; 
  
  ngOnInit() {
    console.log(this.userInfoGroup);
  }
  
}