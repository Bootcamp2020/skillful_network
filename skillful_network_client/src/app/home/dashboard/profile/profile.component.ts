import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/shared/models/user';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
 /* @Output() */ user: User=new User(1,'Steeve','Jobs','',new Date("2016-01-17T08:44:29+0100"),'SteveJobs@gmail.com','','Etudiant',true,true,[],[],[],'https://cdn.profoto.com/cdn/053149e/contentassets/d39349344d004f9b8963df1551f24bf4/profoto-albert-watson-steve-jobs-pinned-image-original.jpg?width=2840&quality=75&format=jpg'
);
  
  //constructor() { }
 /* constructor(data: any) {
    this.id = data.id;
    this.name=data.name;
    this.firstname=data.firstname;
    this.email=data.email;
    this.competences=data.competences;
  } */
  ngOnInit(): void {
  }

}


