import {Component, OnInit} from '@angular/core';
import {User} from 'src/app/shared/models/user';

@Component({
    selector: 'app-menuprofile',
    templateUrl: './menuprofile.component.html',
    styleUrls: ['./menuprofile.component.scss']
})
export class MenuprofileComponent implements OnInit {
    user: User = new User({
        id: 1,
        name: 'Jobs',
        firstName: 'Steve',
        email: 'SteveJobs@gmail.com',
        statut: 'Etudiant',
        qualification: 'Ingenieur',
        competences: ['JAVA/JEE', ' Angular', ' Management'],
        photoProfile: 'https://cdn.profoto.com/cdn/053149e/contentassets/d39349344d004f9b8963df1551f24bf4/profoto-albert-watson-steve-jobs-pinned-image-original.jpg?width=2840&quality=75&format=jpg'
    });

    constructor() {
    }

    ngOnInit(): void {
    }

}
