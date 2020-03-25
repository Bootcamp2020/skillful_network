import { Component, OnInit, Input } from '@angular/core';
import { Qualif } from 'src/app/shared/models/qualif';
import { FormBuilder, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-qualif-conf',
  templateUrl: './qualif-conf.component.html',
  styleUrls: ['./qualif-conf.component.scss']
})

export class QualifConfComponent implements OnInit {
  @Input() qualifInfoGroup : FormGroup; 
  @Input() userQualList : Qualif[];

  titleAlert: string = 'This field is required';
  post: any = '';

  public listQualif: Qualif[];
  public qualif: string;
  

  constructor(private formBuilder: FormBuilder) {

  }

  ngOnInit(): void {
    this.listQualif =  this.userQualList;
    this.qualifInfoGroup.value['qualificationSet'] = this.listQualif;
    console.log(this.qualifInfoGroup);  
  }

  addQualif() {
    this.listQualif.push(new Qualif(this.qualifInfoGroup.value['qualifUnit']));
    this.qualifInfoGroup.value['qualificationSet'] = this.listQualif;
  }
}