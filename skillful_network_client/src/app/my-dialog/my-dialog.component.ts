import { Component, OnInit , Inject} from '@angular/core';
import {MatDialogRef, MatDialog} from '@angular/material/dialog';
import {MAT_DIALOG_DATA} from '@angular/material/dialog'
import { inject } from '@angular/core/testing';


@Component({
  selector: 'app-my-dialog',
  templateUrl: './my-dialog.component.html',
  styleUrls: ['./my-dialog.component.scss']
})
export class MyDialogComponent implements OnInit {

  constructor(public thisDialogRef:MatDialogRef<MyDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: string) { }

  ngOnInit(): void {
  }

  onCloseConfirm() {
this.thisDialogRef.close('fermeture')
  }

}
