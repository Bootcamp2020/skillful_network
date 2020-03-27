import { Component, OnInit, Input } from '@angular/core';
import { Qualif } from 'src/app/shared/models/qualif';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { UserService } from 'src/app/shared/services/user.service';

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
  qualifs:string[];
  public listQualif: Qualif[];
  public qualif: string;
  isLoading:boolean;
  

  constructor(private formBuilder: FormBuilder , private service : UserService) {

  }

  ngOnInit(): void {
    this.listQualif =  this.userQualList;
    this.qualifInfoGroup.value['qualificationSet'] = this.listQualif;
    this.qualifInfoGroup.valueChanges.subscribe(data=>{
      this.isLoading=false;
      this.qualifs = []
      if (data.qualifUnit.length >1){
        this.isLoading=true;
        this.service.findByContain("qualifications",data.qualifUnit).then(
          datas=>{
            for(let id in datas){
            this.qualifs.push(datas[id].name)  
            }
            this.isLoading = false;
          }
        )
      }
    })
  }

  myControl = new FormControl()
  
  addQualif() {
    this.listQualif.push(new Qualif(this.qualifInfoGroup.value['qualifUnit']));
    this.qualifInfoGroup.value['qualificationSet'] = this.listQualif;
  }
}