import {Component, Inject} from '@angular/core';
import {MatBottomSheet, MatBottomSheetRef} from '@angular/material/bottom-sheet';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MenuprofileComponent } from '../menuprofile/menuprofile.component';
import { User } from '../shared/models/user';
import { UserService } from '../shared/services/user.service';
import { ApiHelperService } from '../shared/services/api-helper.service';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http'; 



export interface DialogData {
 user:User; 
}

@Component({
  selector: 'bottom-sheet-overview-example',
  templateUrl: 'bottom-sheet-overview-example.html',
  styleUrls: ['bottom-sheet-overview-example.css'],
})
export class BottomSheetOverviewExample {



  public profilPicture;

constructor(
  public dialogRef: MatDialogRef<BottomSheetOverviewExample >,
  @Inject(MAT_DIALOG_DATA) public data: DialogData, public userService: UserService, private http: HttpClient) {

  }

 

onNoClick(): void {
  this.dialogRef.close();
}

onValidate() : void {
  let body = new FormData();
  body.append("image", this.dataURItoBlob(this.profilPicture), 'image.png');
  this.http.post(environment.base_url+'/users/uploadImage', body).toPromise().then((res) => {
    console.log(res);
    this.userService.userLogged.photoProfile=this.profilPicture;
  })
  this.dialogRef.close();
}

dataURItoBlob(dataURI) {
  var byteString = atob(dataURI.split(',')[1]);
  var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0]
  var ab = new ArrayBuffer(byteString.length);
  var ia = new Uint8Array(ab);
  for (var i = 0; i < byteString.length; i++) {
      ia[i] = byteString.charCodeAt(i);
  }
  var blob = new Blob([ab], {type: mimeString});
  return blob;

}



onSelectFile(event) {
 if(event.target.files){
    var reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    reader.onload=(event:any)=>{
      console.log(event);
      this.profilPicture=event.target.result;
      }
  }
}


}
