import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/shared/models/user';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  // public user: User = new User({id: 1, name: 'Jobs' , firstName: 'Steve', email: 'SteveJobs@gmail.com', statut: 'Etudiant', qualification: 'Ingenieur', competences: ['JAVA/JEE', ' Angular', ' Management'], photoProfile: 'https://cdn.profoto.com/cdn/053149e/contentassets/d39349344d004f9b8963df1551f24bf4/profoto-albert-watson-steve-jobs-pinned-image-original.jpg?width=2840&quality=75&format=jpg'
    user: User=new User({
    id: 1,
    name:'Jobs',
    firstName:'Steve',
    email:'SteveJobs@gmail.com',
    statut:'Etudiant',
    qualification:'Ingenieur',
    competences:['JAVA/JEE', ' Angular',' Management'],
        // tslint:disable-next-line:max-line-length
    photoProfile: 'https://cdn.profoto.com/cdn/053149e/contentassets/d39349344d004f9b8963df1551f24bf4/profoto-albert-watson-steve-jobs-pinned-image-original.jpg?width=2840&quality=75&format=jpg',
    careerGoal: '"Développeur full-stack orienté project management, je cherche un poste de Tech Lead dans le secteur de la finance."'
     // public user: User = new User({id: 1, name: 'Jobs' , firstName: 'Steve', email: 'SteveJobs@gmail.com', status: 'Etudiant', qualification: 'Ingenieur', competences: ['JAVA/JEE', ' Angular', ' Management'], photoProfile: 'https://cdn.profoto.com/cdn/053149e/contentassets/d39349344d004f9b8963df1551f24bf4/profoto-albert-watson-steve-jobs-pinned-image-original.jpg?width=2840&quality=75&format=jpg'
});
  constructor() { }
 /* constructor(data: any) {
    this.id = data.id;
    this.name=data.name;
    this.firstname=data.firstname;
    this.email=data.email;
    this.competences=data.competences;
    this.careerGoal=data.careerGoal;
  } */
  ngOnInit(): void {

    console.log(this.user);
  }

}


