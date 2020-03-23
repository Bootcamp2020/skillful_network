import {Component, Inject} from '@angular/core';
import {MatBottomSheet, MatBottomSheetRef} from '@angular/material/bottom-sheet';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MenuprofileComponent } from '../menuprofile/menuprofile.component';
import { User } from '../shared/models/user';
import { UserService } from '../shared/services/user.service';

export interface DialogData {
 user:User; 
}

@Component({
  selector: 'bottom-sheet-overview-example',
  templateUrl: './bottom-sheet-overview-example.html',
  styleUrls: ['bottom-sheet-overview-example.css'],
})
export class BottomSheetOverviewExample {

constructor(
  public dialogRef: MatDialogRef<BottomSheetOverviewExample >,
  @Inject(MAT_DIALOG_DATA) public data: DialogData, public userService: UserService) {

  }

 

onNoClick(): void {
  this.dialogRef.close();
}


onSelectFile(e) {
 if(e.target.files){
    var reader = new FileReader();
    reader.readAsDataURL(e.target.files[0]);
    reader.onload=(event:any)=>{
      console.log(event.target)
      this.userService.user.photoProfile=event.target.result;
      
    }
  }
}


}
