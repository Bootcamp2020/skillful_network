import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/shared/models/user';
@Component({
  selector: 'app-menuprofile',
  templateUrl: './menuprofile.component.html',
  styleUrls: ['./menuprofile.component.scss']
})
export class MenuprofileComponent implements OnInit {
  user: User=new User({id: 1,name:'Jobs',firstName:'Steve',email:'SteveJobs@gmail.com',qualification:'Ingenieur',competences:['JAVA/JEE', ' Angular',' Management'],photoProfile:'https://media1.ledevoir.com/images_galerie/nwd_97473_80652/steve-jobs.jpg'
});
  constructor() { }

  ngOnInit(): void {
  }

}
