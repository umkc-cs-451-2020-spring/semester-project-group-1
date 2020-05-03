import { TriggerTableItem, TriggersService } from './../triggers.service';
import { MatSnackBar } from '@angular/material/snack-bar';
//import { EditAppService } from './../edit-app.service';
import { FormControl, Validators, FormGroup, FormBuilder, FormsModule } from '@angular/forms';
import { Component, OnInit, OnChanges, AfterViewInit, NgModule } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialog, } from '@angular/material/dialog';
import { Inject } from '@angular/core';
//import { Associate } from '../login/login-response.model';

interface State {
  value: string;
  viewValue: string;
}

interface StartTime {
  value: string;
  viewValue: string;
}

interface EndTime {
  value: string;
  viewValue: string;
}



@Component({
  selector: 'app-edit-triggers',
  templateUrl: './edit-triggers.component.html',
  styleUrls: ['./edit-triggers.component.scss']
})
export class EditTriggersComponent implements OnInit {

  // startTimes: StartTime[] = [
  //   { value: '00:00-0', viewValue: '00:00' },
  //   { value: '01:00-1', viewValue: '01:00' },
  //   { value: '02:00-2', viewValue: '02:00' },
  //   { value: '03:00-3', viewValue: '03:00' },
  //   { value: '04:00-4', viewValue: '04:00' },
  //   { value: '05:00-5', viewValue: '05:00' },
  //   { value: '06:00-6', viewValue: '06:00' },
  //   { value: '07:00-7', viewValue: '07:00' },
  //   { value: '08:00-8', viewValue: '08:00' },
  //   { value: '09:00-9', viewValue: '09:00' },
  //   { value: '10:00-10', viewValue: '10:00' },
  //   { value: '11:00-11', viewValue: '11:00' },
  //   { value: '12:00-12', viewValue: '12:00' },
  //   { value: '13:00-13', viewValue: '13:00' },
  //   { value: '14:00-14', viewValue: '14:00' },
  //   { value: '15:00-15', viewValue: '15:00' },
  //   { value: '16:00-16', viewValue: '16:00' },
  //   { value: '17:00-17', viewValue: '17:00' },
  //   { value: '18:00-18', viewValue: '18:00' },
  //   { value: '19:00-19', viewValue: '19:00' },
  //   { value: '20:00-20', viewValue: '20:00' },
  //   { value: '21:00-21', viewValue: '21:00' },
  //   { value: '22:00-22', viewValue: '22:00' },
  //   { value: '23:00-23', viewValue: '23:00' }
  // ];

  // endTimes: EndTime[] = [{ value: '00:00-0', viewValue: '00:00' },
  // { value: '01:00-1', viewValue: '01:00' },
  // { value: '02:00-2', viewValue: '02:00' },
  // { value: '03:00-3', viewValue: '03:00' },
  // { value: '04:00-4', viewValue: '04:00' },
  // { value: '05:00-5', viewValue: '05:00' },
  // { value: '06:00-6', viewValue: '06:00' },
  // { value: '07:00-7', viewValue: '07:00' },
  // { value: '08:00-8', viewValue: '08:00' },
  // { value: '09:00-9', viewValue: '09:00' },
  // { value: '10:00-10', viewValue: '10:00' },
  // { value: '11:00-11', viewValue: '11:00' },
  // { value: '12:00-12', viewValue: '12:00' },
  // { value: '13:00-13', viewValue: '13:00' },
  // { value: '14:00-14', viewValue: '14:00' },
  // { value: '15:00-15', viewValue: '15:00' },
  // { value: '16:00-16', viewValue: '16:00' },
  // { value: '17:00-17', viewValue: '17:00' },
  // { value: '18:00-18', viewValue: '18:00' },
  // { value: '19:00-19', viewValue: '19:00' },
  // { value: '20:00-20', viewValue: '20:00' },
  // { value: '21:00-21', viewValue: '21:00' },
  // { value: '22:00-22', viewValue: '22:00' },
  // { value: '23:00-23', viewValue: '23:00' }];



  // states: State[] = [
  //   { value: 'Alabama-0', viewValue: 'Alabama' },
  //   { value: 'Alaska-1', viewValue: 'Alaska' },
  //   { value: 'Arizona-2', viewValue: 'Arizona' },
  //   { value: 'Arkansas-3', viewValue: 'Arkansas' },
  //   { value: 'California-4', viewValue: 'California' },
  //   { value: 'Colorado-5', viewValue: 'Colorado' },
  //   { value: 'Connecticuit-6', viewValue: 'Connecticuit' },
  //   { value: 'Delaware-7', viewValue: 'Delaware' },
  //   { value: 'Florida-8', viewValue: 'Florida' },
  //   { value: 'Georgia-9', viewValue: 'Georgia' },
  //   { value: 'Hawaii-10', viewValue: 'Hawaii' },
  //   { value: 'Idaho-11', viewValue: 'Idaho' },
  //   { value: 'Illinois-12', viewValue: 'Illinois' },
  //   { value: 'Indiana-13', viewValue: 'Indiana' },
  //   { value: 'Iowa-14', viewValue: 'Iowa' },
  //   { value: 'Kansas-15', viewValue: 'Kansas' },
  //   { value: 'Kentucky-16', viewValue: 'Kentucky' },
  //   { value: 'Louisiana-17', viewValue: 'Louisiana' },
  //   { value: 'Maine-18', viewValue: 'Maine' },
  //   { value: 'Maryland-19', viewValue: 'Maryland' },
  //   { value: 'Massachusetts-20', viewValue: 'Massachusetts' },
  //   { value: 'Michigan-21', viewValue: 'Michigan' },
  //   { value: 'Minnesota-22', viewValue: 'Minnesota' },
  //   { value: 'Mississippi-23', viewValue: 'Mississippi' },
  //   { value: 'Missouri-24', viewValue: 'Missouri' },
  //   { value: 'Montana-25', viewValue: 'Montana' },
  //   { value: 'Nebraska-26', viewValue: 'Nebraska' },
  //   { value: 'Nevada-27', viewValue: 'Nevada' },
  //   { value: 'New Hampshire-28', viewValue: 'New Hampshire' },
  //   { value: 'New Jersey-29', viewValue: 'New Jersey' },
  //   { value: 'New Mexico-30', viewValue: ' New Mexico' },
  //   { value: 'New York-31', viewValue: 'New York' },
  //   { value: 'North Carolina-32', viewValue: 'North Carolina' },
  //   { value: 'North Dakota-33', viewValue: 'North Dakota' },
  //   { value: 'Ohio-34', viewValue: 'Ohio' },
  //   { value: 'Oklahoma-35', viewValue: 'Oklahoma' },
  //   { value: 'Oregon-36', viewValue: 'Oregon' },
  //   { value: 'Pennsylvania-37', viewValue: 'Pennsylvania' },
  //   { value: 'Rhode Island-38', viewValue: 'Rhode Island' },
  //   { value: 'South Carolina-39', viewValue: 'South Carolina' },
  //   { value: 'South Dakota-40', viewValue: 'South Dakota' },
  //   { value: 'Tennessee-41', viewValue: 'Tennessee' },
  //   { value: 'Texas-42', viewValue: 'Texas' },
  //   { value: 'Utah-43', viewValue: 'Utah' },
  //   { value: 'Vermont-44', viewValue: 'Vermont' },
  //   { value: 'Virginia-45', viewValue: 'Virginia' },
  //   { value: 'Washington-46', viewValue: 'Washington' },
  //   { value: 'West Virginia-47', viewValue: 'West Virginia' },
  //   { value: 'Wisconsin-48', viewValue: 'Wisconsin' },
  //   { value: 'Wyoming-49', viewValue: 'Wyoming' }
  // ];


  // constructor(public dialogRef: MatDialogRef<EditTriggersComponent>,
  //   @Inject(MAT_DIALOG_DATA) public data: TriggerTableItem,
  //   public editTriggers: TriggersService,
  //   public snackBar: MatSnackBar) { }

  // session = window.sessionStorage;
  // // user: Associate = JSON.parse(this.session.getItem("user"));
  // entryToEdit = this.data;

  // functionality = new FormControl(this.entryToEdit.functionality, [Validators.maxLength(100)]);
  // triggerID = new FormControl(this.entryToEdit.triggerID, [Validators.maxLength(100)]);
  // currentSettings = new FormControl(this.entryToEdit.currentSettings, [Validators.maxLength(100)]);
  // changeDate = false;

  // // entryAppID = this.entryToEdit.appID;
  // // entryBuildID = this.entryToEdit.buildID;
  // // entrySandboxID = this.entryToEdit.sandboxID;
  // // entrySandboxName = this.entryToEdit.sandboxName;

  // // entryTriggerID = this.entryToEdit.triggerID;
  // // entryFunctionality = this.entryToEdit.functionality;
  // // entryCurrentSettings = this.entryToEdit.currentSettings;


  ngOnInit(): void {
  }


  // // enteredValue() {
  // //   if (this.primaryLanguageInput.value !== undefined && this.primaryLanguageInput.value !== null && this.primaryLanguageInput.value !== "" && this.primaryLanguageInput.valid) {
  // //     // console.log(this.primaryLanguageInput.value);
  // //     return true;
  // //   }
  // //   else if (this.projectNameInput.value !== undefined && this.projectNameInput.value !== null && this.projectNameInput.value !== "" && this.projectNameInput.valid) {
  // //     // console.log(this.projectNameInput.value);
  // //     return true;
  // //   }
  // //   else {
  // //     return false;
  // //   }
  // // }

  // /**
  //  * Updates build metadata with inputted FormControl values
  //  */
  // updateTriggerEntry() {
  //   // console.log("Last Static Scan: " + this.entryToEdit.lastStaticScan);
  //   this.entryToEdit.functionality = this.functionality.value;
  //   this.entryToEdit.triggerID = this.triggerID.value;
  //   this.entryToEdit.currentSettings = this.currentSettings.value;
  //   this.snackBar.open('Attempting to edit entry...');
  //   this.editTriggers.editEntry(this.entryToEdit).subscribe(
  //     data => {
  //       this.snackBar.open('Edit successful!', 'Okay', {
  //         duration: 3000
  //       }

  //     );
  //     }, (err) => {
  //       this.snackBar.open(err, 'Okay', {
  //         duration: 3000
  //       });
  //     }
  //   );


  //   this.dialogRef.close();
  // }
  // editEntry(entryToEdit: TriggerTableItem) {
  //   throw new Error("Edit not completed.");
  // }
}


