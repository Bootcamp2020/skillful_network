import {Component, OnInit} from '@angular/core';
import {User} from 'src/app/shared/models/user';

@Component({
    selector: 'app-menuprofile',
    templateUrl: './menuprofile.component.html',
    styleUrls: ['./menuprofile.component.scss']
})
export class MenuprofileComponent implements OnInit {
    
    user: User=new User({
    id: 1,
    firstName:'Steeve',
    lastName: 'Jobs',
    email: 'SteveJobs@gmail.com',
    mobileNumber: '0123456789',
    status: 'Etudiant',
    validated: true,
    photo: true,
    photoProfile: 'https://cdn.profoto.com/cdn/053149e/contentassets/d39349344d004f9b8963df1551f24bf4/profoto-albert-watson-steve-jobs-pinned-image-original.jpg?width=2840&quality=75&format=jpg',
  });


    constructor() {
    }

    ngOnInit(): void {
    }

}
