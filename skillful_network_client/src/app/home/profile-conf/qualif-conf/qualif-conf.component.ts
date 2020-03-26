import { Component, OnInit, Input } from '@angular/core';
import { MOCK_QUALIF, IPost } from 'src/app/shared/models/mock.qualif';
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

  addQualifFormGroup: FormGroup;
  titleAlert: string = 'This field is required';
  post: any = '';
  qualifs:string[];
  public listQualif: Qualif[];
  @Input() public qualif: string;

  constructor(private formBuilder: FormBuilder , private service : UserService) {

  }

  ngOnInit(): void {
    this.createForm();
    this.listQualif = [];
    MOCK_QUALIF.forEach((qualifu: IPost) => {
      this.listQualif.push(new Qualif(qualifu));
    });  

    this.addQualifFormGroup.valueChanges.subscribe(data=>{
      this.qualifs = []
      if (data.newQualif.length >1){
        this.service.findByContain("qualifications",data.newQualif).then(
          datas=>{
            for(let id in datas)
            this.qualifs.push(datas[id].name)  
          }
        )
      }
    })
  }

  myControl = new FormControl()
  

  createForm() {
    this.addQualifFormGroup = this.formBuilder.group({
      'newQualif': [null, Validators.required]
    });
  }

  addQualif() {
    this.listQualif.push(new Qualif({qualif: this.addQualifFormGroup.value['newQualif']}));
  }
}



