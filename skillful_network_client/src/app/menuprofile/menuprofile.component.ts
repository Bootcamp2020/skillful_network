import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/shared/models/user';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { BottomSheetOverviewExample } from '../bottom-sheet-overview-example/bottom-sheet-overview-example';
import { UserService } from '../shared/services/user.service';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {DomSanitizer} from "@angular/platform-browser";


@Component({
    selector: 'app-menuprofile',
    templateUrl: './menuprofile.component.html',
    styleUrls: ['./menuprofile.component.scss']
})
export class MenuprofileComponent implements OnInit {
    public photoProfile: any;
    user: User=new User({
    id: 1,
    firstName:'Steeve',
    lastName: 'Jobs',
    email: 'SteveJobs@gmail.com',
    mobileNumber: '0123456789',
    status: 'Etudiant',
    validated: true,
    photo: true,
    //photoProfile: 'https://cdn.profoto.com/cdn/053149e/contentassets/d39349344d004f9b8963df1551f24bf4/profoto-albert-watson-steve-jobs-pinned-image-original.jpg?width=2840&quality=75&format=jpg',
  });


    constructor(public dialog: MatDialog, public userService: UserService,  private http: HttpClient, private sanitizer: DomSanitizer) {
    }

    ngOnInit(): void {
        console.log(this.user.photo);
        console.log(this.user.id)
        if (this.user.photo) {
            this.http.get(environment.base_url + `/image/${this.user.id}`, {responseType: 'blob'})
                .subscribe(dataBlob => {
                    const objectURL = URL.createObjectURL(dataBlob);

                    this.userService.userLogged.photoProfile = this.sanitizer.bypassSecurityTrustUrl(objectURL);
                });
        } else {
            this.userService.userLogged.photoProfile = '../../../../assets/pictures/profile_defaut.jpg';
        }
    }

  onSelectFile(e) {
    if (e.target.files) {
      var reader = new FileReader();
      reader.readAsDataURL(e.target.files[0]);
      reader.onload = (event: any) => {
        console.log(event.target)
        this.userService.userLogged.photoProfile = event.target.result;
      }
    }
  }

  openModalProfile() {
    const dialogRef = this.dialog.open(BottomSheetOverviewExample, {
      width: '25%',
      height: '70%',
      data: { user: this.userService.userLogged.photoProfile }
    });

   
  }


}
