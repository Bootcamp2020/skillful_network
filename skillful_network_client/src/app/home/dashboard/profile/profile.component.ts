import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/shared/models/user';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
 /* @Output() */ user: User=new User({id: 1,name:'Jobs',firstName:'Steve',email:'SteveJobs@gmail.com',qualification:'Ingenieur',competences:['JAVA/JEE', ' Angular',' Management'],photoProfile:'https://profoto.com/fr/profoto-stories/albert-watson-steve-jobs'
});
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


