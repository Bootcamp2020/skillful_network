import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  templateUrl: './goal-validation-modal.component.html',
  styleUrls: ['./goal-validation-modal.component.scss']
})
export class GoalValidationModalComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<GoalValidationModalComponent>,
    //@Inject(MAT_DIALOG_DATA) public data: DialogData) {
    @Inject(MAT_DIALOG_DATA) public data: any) { 
      console.log(">>> injected goal: " + this.data.goal);
    }

  ngOnInit(): void {
  }

  onValidClick(): void {
    console.log(">>> submitted goal: " + this.data.goal);
    this.dialogRef.close(this.data.goal);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
