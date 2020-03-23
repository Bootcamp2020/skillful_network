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

  constructor(public dialog: MatDialog, public userService: UserService) { }

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
