import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
// import { MatSelectChange, MatDialog, MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AddTriggerService } from './add-trigger.service';
// import { Associate } from '../login/login-response.model';



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
  selector: 'app-add-triggers',
  templateUrl: './add-triggers.component.html',
  styleUrls: ['./add-triggers.component.scss']
})




export class AddTriggersComponent implements OnInit {

startTimes: StartTime[] = [
  {value: '00:00-0', viewValue: '00:00'},
  {value: '01:00-1', viewValue: '01:00'},
  {value: '02:00-2', viewValue: '02:00'},
  {value: '03:00-3', viewValue: '03:00'},
  {value: '04:00-4', viewValue: '04:00'},
  {value: '05:00-5', viewValue: '05:00'},
  {value: '06:00-6', viewValue: '06:00'},
  {value: '07:00-7', viewValue: '07:00'},
  {value: '08:00-8', viewValue: '08:00'},
  {value: '09:00-9', viewValue: '09:00'},
  {value: '10:00-10', viewValue: '10:00'},
  {value: '11:00-11', viewValue: '11:00'},
  {value: '12:00-12', viewValue: '12:00'},
  {value: '13:00-13', viewValue: '13:00'},
  {value: '14:00-14', viewValue: '14:00'},
  {value: '15:00-15', viewValue: '15:00'},
  {value: '16:00-16', viewValue: '16:00'},
  {value: '17:00-17', viewValue: '17:00'},
  {value: '18:00-18', viewValue: '18:00'},
  {value: '19:00-19', viewValue: '19:00'},
  {value: '20:00-20', viewValue: '20:00'},
  {value: '21:00-21', viewValue: '21:00'},
  {value: '22:00-22', viewValue: '22:00'},
  {value: '23:00-23', viewValue: '23:00'}
];

endTimes: EndTime[] = [{value: '00:00-0', viewValue: '00:00'},
{value: '01:00-1', viewValue: '01:00'},
{value: '02:00-2', viewValue: '02:00'},
{value: '03:00-3', viewValue: '03:00'},
{value: '04:00-4', viewValue: '04:00'},
{value: '05:00-5', viewValue: '05:00'},
{value: '06:00-6', viewValue: '06:00'},
{value: '07:00-7', viewValue: '07:00'},
{value: '08:00-8', viewValue: '08:00'},
{value: '09:00-9', viewValue: '09:00'},
{value: '10:00-10', viewValue: '10:00'},
{value: '11:00-11', viewValue: '11:00'},
{value: '12:00-12', viewValue: '12:00'},
{value: '13:00-13', viewValue: '13:00'},
{value: '14:00-14', viewValue: '14:00'},
{value: '15:00-15', viewValue: '15:00'},
{value: '16:00-16', viewValue: '16:00'},
{value: '17:00-17', viewValue: '17:00'},
{value: '18:00-18', viewValue: '18:00'},
{value: '19:00-19', viewValue: '19:00'},
{value: '20:00-20', viewValue: '20:00'},
{value: '21:00-21', viewValue: '21:00'},
{value: '22:00-22', viewValue: '22:00'},
{value: '23:00-23', viewValue: '23:00'}];



  states: State[] = [{value: 'Alabama-0', viewValue: 'Alabama'},
    {value: 'Alaska-1', viewValue: 'Alaska'},
    {value: 'Arizona-2', viewValue: 'Arizona'},
    {value: 'Arkansas-3', viewValue: 'Arkansas'},
    {value: 'California-4', viewValue: 'California'},
    {value: 'Colorado-5', viewValue: 'Colorado'},
    {value: 'Connecticuit-6', viewValue: 'Connecticuit'},
    {value: 'Delaware-7', viewValue: 'Delaware'},
    {value: 'Florida-8', viewValue: 'Florida'},
    {value: 'Georgia-9', viewValue: 'Georgia'},
    {value: 'Hawaii-10', viewValue: 'Hawaii'},
    {value: 'Idaho-11', viewValue: 'Idaho'},
    {value: 'Illinois-12', viewValue: 'Illinois'},
    {value: 'Indiana-13', viewValue: 'Indiana'},
    {value: 'Iowa-14', viewValue: 'Iowa'},
    {value: 'Kansas-15', viewValue: 'Kansas'},
    {value: 'Kentucky-16', viewValue: 'Kentucky'},
    {value: 'Louisiana-17', viewValue: 'Louisiana'},
    {value: 'Maine-18', viewValue: 'Maine'},
    {value: 'Maryland-19', viewValue: 'Maryland'},
    {value: 'Massachusetts-20', viewValue: 'Massachusetts'},
    {value: 'Michigan-21', viewValue: 'Michigan'},
    {value: 'Minnesota-22', viewValue: 'Minnesota'},
    {value: 'Mississippi-23', viewValue: 'Mississippi'},
    {value: 'Missouri-24', viewValue: 'Missouri'},
    {value: 'Montana-25', viewValue: 'Montana'},
    {value: 'Nebraska-26', viewValue: 'Nebraska'},
    {value: 'Nevada-27', viewValue: 'Nevada'},
    {value: 'New Hampshire-28', viewValue: 'New Hampshire'},
    {value: 'New Jersey-29', viewValue: 'New Jersey'},
    {value: 'New Mexico-30', viewValue: ' New Mexico'},
    {value: 'New York-31', viewValue: 'New York'},
    {value: 'North Carolina-32', viewValue: 'North Carolina'},
    {value: 'North Dakota-33', viewValue: 'North Dakota'},
    {value: 'Ohio-34', viewValue: 'Ohio'},
    {value: 'Oklahoma-35', viewValue: 'Oklahoma'},
    {value: 'Oregon-36', viewValue: 'Oregon'},
    {value: 'Pennsylvania-37', viewValue: 'Pennsylvania'},
    {value: 'Rhode Island-38', viewValue: 'Rhode Island'},
    {value: 'South Carolina-39', viewValue: 'South Carolina'},
    {value: 'South Dakota-40', viewValue: 'South Dakota'},
    {value: 'Tennessee-41', viewValue: 'Tennessee'},
    {value: 'Texas-42', viewValue: 'Texas'},
    {value: 'Utah-43', viewValue: 'Utah'},
    {value: 'Vermont-44', viewValue: 'Vermont'},
    {value: 'Virginia-45', viewValue: 'Virginia'},
    {value: 'Washington-46', viewValue: 'Washington'},
    {value: 'West Virginia-47', viewValue: 'West Virginia'},
    {value: 'Wisconsin-48', viewValue: 'Wisconsin'},
    {value: 'Wyoming-49', viewValue: 'Wyoming'}
  ];


  // sandboxes: Sandbox[] = [];
  // public veracodeAppList: VeracodeAppEntry[] = [];

  /**
   * Filtered variables array for the mat sort.
  //  */
  // public filteredVariables = this.veracodeAppList.slice();
  session = window.sessionStorage;

  /**
   * Form control for the app name
   */
  triggerType = new FormControl(''); //appName

    /**
   * Form control for the sandbox name
   */
  triggerState = new FormControl(''); //sandboxName

  triggerStartTime = new FormControl('');

  triggerEndTime = new FormControl('');

      // TODO: Fix this screwy logic or at least make it cleaner. Maybe create a method that just disables/enables the whole component?
  // appNotLoaded = true;
  // sandboxNotSelected = true;
  // sandboxesLoading = false;
  // sandboxBuildAdding = false;
  triggerAdding = false;
  triggerAdded = false;

  // new form control booleans
  // sandboxBuild = false;
  // promotedBuild = false;


  // user: Associate = JSON.parse(this.session.getItem("user"));
  // lastModifiedByInput = new FormControl('', [Validators.required, Validators.pattern('^(D|d)(T|t)[0-9]{5,6}$')]);

  constructor(public appService: AddTriggerService,
              public dialogRef: MatDialogRef<AddTriggersComponent>, public snackBar: MatSnackBar) { }

  ngOnInit() {
    // this.snackBar.open('Attemping to retrieve list of triggers');
    // // this.appName.disable();
    // this.appService.getAppList().subscribe(data => {
    // this.veracodeAppList = data;

    // console.log(data);
    // this.filteredVariables = this.veracodeAppList.slice();
    // this.snackBar.open('App list from Veracode successfully retrieved', 'Okay', {
    //   duration: 3000
    // });
    // this.appName.enable();

      // }, (err) => {
      //   this.snackBar.open(err, 'Okay', {
      //     duration: 3000
      //   });
      // });
  }


  /**
   * Retrieves a list of sandboxes for an application and pipes it int the sandbox FormControl.
   */
  // searchApps() {
  //   this.snackBar.open('Attemping to load sandboxes...');

  //   this.sandboxesLoading = true;
  //   this.promotedAdded = false;
  //   this.appNotLoaded = true;
  //   this.appName.disable();

  //   this.appService.getSandboxNames(this.appName.value.appID).subscribe(data => {
  //     // this.appService.handleError();
  //     this.sandboxes = data;

  //     // console.log(data);
  //     // console.log(this.sandboxes);

  //     this.appNotLoaded = false;

  //     this.snackBar.open('Sandboxes for ' + this.appName.value.appName + ' loaded', 'Okay', {
  //       duration: 3000
  //     });

  //     this.sandboxesLoading = false;
  //     this.appName.enable();
  //     this.sandboxName.enable();

  //   }, (err) => {
  //     this.snackBar.open(err, 'Okay', {
  //       duration: 3000
  //     });

  //     this.sandboxesLoading = false;
  //   });

  //   // console.log(this.sandboxes); - List of Sandbox Objects
  // }

  /**
   * Adds a sandbox build to the database given inputted values for application name and sandbox name
   */
  // addApp() {

  //   this.snackBar.open('Adding new trigger');

  //   this.sandboxBuildAdding = true;
  //   this.sandboxNotSelected = true;
  //   this.appName.disable();
  //   this.sandboxName.disable();

  //   this.appService.addSandboxBuild(this.appName.value.appName, this.sandboxName.value.sandboxID,
  //     this.user.id).subscribe(data => {
  //     this.sandboxBuildAdding = false;
  //     this.snackBar.open('Latest build from ' + this.sandboxName.value.sandboxName + ' added to the database', 'Okay', {
  //       duration: 3000
  //     });
  //     this.appName.enable();
  //     this.sandboxName.enable();
  //   }, (err) => {
  //     this.snackBar.open(err, 'Okay', {
  //       duration: 3000
  //     });
  //   });
  // }

  // /**
  //  * Adds a latest promoted build to the database given inputted value for application name.
  //  */
  addTriggers() {
    this.snackBar.open('Adding new trigger');
    // this.promotedBuildAdding = true;
    this.triggerState.disable();
    this.triggerStartTime.disable();
    this.triggerEndTime.disable();
    this.appService.addTriggers(this.states[0].value, this.startTimes[0].value, this.endTimes[0].value).subscribe(data => {
      // this.AddTrigg = false;
      this.snackBar.open('Latest promoted build added', 'Okay', {
        duration: 3000
      });
      this.triggerState.enable();
      this.triggerStartTime.enable();
      this.triggerEndTime.enable();
    }, (err) => {
      this.snackBar.open(err, 'Okay', {
        duration: 3000
      });
    });

  }


}
