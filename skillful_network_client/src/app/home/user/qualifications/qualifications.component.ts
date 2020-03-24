import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Qualif } from 'src/app/shared/models/qualif';
import { MOCK_QUALIF, IPost } from 'src/app/shared/models/mock.qualif';

@Component({
  selector: 'app-qualifications',
  templateUrl: './qualifications.component.html',
  styleUrls: ['./qualifications.component.scss']
})
export class QualificationsComponent implements OnInit {
  addQualifFormGroup: FormGroup;
  titleAlert: string = 'This field is required';
  post: any = '';

  public listQualif: Qualif[];
  @Input() public qualif: string;

  constructor(private formBuilder: FormBuilder) {

  }

  ngOnInit(): void {
   
    this.listQualif = [];
    MOCK_QUALIF.forEach((qualifu: IPost) => {
      this.listQualif.push(new Qualif(qualifu));
    });  
  }

  
 
}
