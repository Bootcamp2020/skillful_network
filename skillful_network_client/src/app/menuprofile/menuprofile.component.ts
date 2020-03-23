import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/shared/models/user';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { BottomSheetOverviewExample } from '../bottom-sheet-overview-example/bottom-sheet-overview-example';
import { UserService } from '../shared/services/user.service';


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

    constructor(public dialog: MatDialog, public userService: UserService) {
    }

    ngOnInit(): void {
    }

  onSelectFile(e) {
    if (e.target.files) {
      var reader = new FileReader();
      reader.readAsDataURL(e.target.files[0]);
      reader.onload = (event: any) => {
        console.log(event.target)
        this.userService.user.photoProfile = event.target.result;
      }
    }
  }

  openModalProfile() {
    const dialogRef = this.dialog.open(BottomSheetOverviewExample, {
      width: '25%',
      height: '70%',
      data: { user: this.userService.user.photoProfile }
    });

   
  }


}
