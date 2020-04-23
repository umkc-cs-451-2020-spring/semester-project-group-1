//import { AppMetadataComponent } from './../app-metadata/app-metadata.component';
//import { BuildFlawsTableComponent } from './../build-flaws-view/build-flaws-view.component';
//import { AddAppPageComponent } from './../add-app-page/add-app-page.component';
//import { HighLevelReportWizardComponent } from './../high-level-report-wizard/high-level-report-wizard.component';
import { EditTriggersComponent } from './../edit-triggers/edit-triggers.component';
import { AddTriggersComponent } from './../add-triggers/add-triggers.component';
import { ViewTriggersComponent } from './../view-triggers/view-triggers.component';
import { ViewNotificationsComponent } from './../view-notifications/view-notifications.component';
import { ViewTransactionsComponent } from './../view-transactions/view-transactions.component';
//import { AppTableService } from './../app-table.service';
import { TriggerTableItem } from '../app-table.service';
import { AfterViewInit, Component, OnInit, ViewChild, Inject } from '@angular/core';
// import { MatPaginator, MatSort, MatTable, MatTableDataSource, MatDialog,
//   MatDialogRef, MAT_DIALOG_DATA, MatDialogConfig, MatSnackBar, MatIconRegistry, MatTooltip,
//   TooltipPosition, throwToolbarMixedModesError } from '@angular/material';
import { MatPaginator, MatSort, MatTable, MatTableDataSource, MatDialog, MatSnackBar, MatIconRegistry, TooltipPosition} from '@angular/material';
import {SelectionModel} from '@angular/cdk/collections';
import {DomSanitizer} from '@angular/platform-browser';
import { Router, ActivatedRoute } from '@angular/router';
//import { FlawTableService } from '../flaw-table.service';
import { Observable } from 'rxjs';
//import { Associate } from '../login/login-response.model';
import { map } from 'rxjs/operators';
import { AppRoutingModule } from '../app-routing.module';



@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.scss']
})
export class UserDashboardComponent implements OnInit, AfterViewInit {

  
   /**
   * Paginator for the table
   */
  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;

  /**
   * Sorter for the table
   */
  @ViewChild(MatSort, {static: false}) sort: MatSort;

  /**
   * The Application table
   *
   * Note that "application" in this context refers to a scanned application build on Veracode.
   */
  @ViewChild(MatTable, {static: false}) table: MatTable<TriggerTableItem>;

  /**
   * Build table's data source
   */
  dataSource = new MatTableDataSource<TriggerTableItem>();

  /**
   *  The context of the selected row
   */
  selection = new SelectionModel<TriggerTableItem>(true, []);

  /**
   * Tooltip position
   */
  abovePosition: TooltipPosition = 'below';

  /**
   * User session
   */
  session = window.sessionStorage;

  /**
   * Please ask Tucker Diekmann what this does (DT225453)
   */
  displayUser = 'GUEST';

  /**
   * Please ask Tucker Diekmann what this does (DT225453)
   */
  loggedIn = false;

  /**
   * The array of column names for the table to use
   */
  displayedColumns = ['Buttons', 'appName', /*'appID', 'sandboxID', 'buildID',*/ 'sandboxName', 'businessOwner', 'businessVertical',
                      'jiraProject', 'primaryLanguage', 'lastStaticScan', 'lastDynamicScan',
                      /* 'lastExcelReport','email',*/ 'cmdb_id', /*'activeStatus', */'lastModifiedBy'];

  constructor(public appTable: TriggerTableService, public dialog: MatDialog, public snackBar: MatSnackBar,
              public iconRegistry: MatIconRegistry, public sanitizer: DomSanitizer, public router: Router,
              public activatedRoute: ActivatedRoute, public flawTable: FlawTableService ) {
    iconRegistry.addSvgIcon(
      'add-app',
      sanitizer.bypassSecurityTrustResourceUrl('assets/icons/baseline-add_box-24px.svg'));
    iconRegistry.addSvgIcon(
      'edit-app',
      sanitizer.bypassSecurityTrustResourceUrl('assets/icons/baseline-edit-24px.svg'));
    iconRegistry.addSvgIcon(
      'app-schedule',
      sanitizer.bypassSecurityTrustResourceUrl('assets/icons/baseline-schedule-24px.svg'));
    iconRegistry.addSvgIcon(
      'delete-app',
      sanitizer.bypassSecurityTrustResourceUrl('assets/icons/baseline-delete-24px.svg'));
    iconRegistry.addSvgIcon(
      'app-flaws',
      sanitizer.bypassSecurityTrustResourceUrl('assets/icons/baseline-security-24px.svg'));
    iconRegistry.addSvgIcon(
      'refresh-table',
      sanitizer.bypassSecurityTrustResourceUrl('assets/icons/baseline-refresh-24px.svg'));
  }

  /**
   * Refreshes the table data
   */
  refreshTable() {
    this.snackBar.open('Attempting to refresh table...', 'Okay', {
      duration: 3000
    });
    this.appTable.getApps().subscribe(data => {
      this.dataSource.data = data;
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
      this.snackBar.open('Table successfully loaded!', 'Okay', {
        duration: 1000
      });
    }, (err) => {
      this.snackBar.open(err, 'Okay', { duration: 10000});
    });
  }

  /**
   * Refreshes all listed applications by pulling all latest promoted scans
   */
  refreshApps() {
    this.appTable.refreshApps(this.dataSource.data).subscribe(data => {
      if (data !== '200' && data !== 200) {
        this.snackBar.open('Error Code: ' + data + '. Error refreshing application data.', 'Close', {duration: 5000});
      }
    });
  }

  ngOnInit() {

    if (this.session.getItem('user') !== 'undefined' && this.session.length > 1) {
      /* User data to string (for session) */
      this.user = JSON.parse(this.session.getItem('user'));
      /* Login time to string (for session) */
      const start: Date = new Date(JSON.parse(this.session.getItem('start')));

      /* Calulate if 15 min have passed */
      const now: Date = new Date();
      const millis: number = now.getTime() - start.getTime();
      const min = millis / 60000;
      if (min > 30) {
        /* Clear session and force login */
        this.session.clear();
        this.router.navigate(['Login']);
      }
      this.loggedIn = true;
      this.refreshTable();
    } else {
      /* set user and login time as seesion variables */
   //   this.state$ = this.activatedRoute.paramMap.pipe(map(() => window.history.state));
   //   this.state$.subscribe(data => {
        //this.user = data[0];
        const auth = this.session.getItem('authHeader');
        this.session.clear();
        this.session.setItem('authHeader', auth);
      //  this.session.setItem('user', JSON.stringify(this.user));
        this.session.setItem('start', JSON.stringify(new Date()));
      });
      // this.nullCheck();
      this.loggedIn = true;
      this.refreshTable();
    }

  }

  /**
   * Redirects the user to the login page if user is undefeind
   */
  // public nullCheck() {
  //   /* If user is null, redirect to login and clear session */
  //   if (this.user === undefined || this.user.id === undefined) {
  //     this.session.clear();
  //     this.router.navigate(['Login']);
  //   }
  // }

  ngAfterViewInit() {
    if (this.loggedIn) {
      this.refreshTable();
    }

  }

  /**
   * Navigates to the [edit component]{@link EditAppPageComponent} for scan-specific metadata
   *
   * Note that "application" in this context refers to a scanned application build on Veracode.
   *
   * @param selectedRow The selected table entry
   */
  toEditApp(selectedRow: TriggerTableItem) {
      const dialogRef = this.dialog.open(EditTriggersComponent, {
        width: '20em',
        maxHeight: '90vh',
        data: selectedRow
      });

      dialogRef.afterClosed().subscribe(result => {
        console.log('dialog closed');
        this.refreshTable();
        this.selection.clear();
        });
    }

  /**
   * Navigates to the ["Add New Application"]{@link AddAppPageComponent} stepper component
   *
   * Note that "application" in this context refers to a scanned application build on Veracode.
   */
  toAddApp() {
    const dialogRef = this.dialog.open(AddTriggersComponent, {
      width: '45em',
      maxHeight: '90vh'
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('dialog closed');
      this.refreshTable();
      this.selection.clear();
    });
  }

  /**
   * Navigates to the ["High Level Report"]{@link HighLevelReportWizardComponent} stepper component
   */
  // toHighLevelWizard() {
  //   const dialogRef = this.dialog.open(HighLevelReportWizardComponent, {
  //     width: '40em',
  //     maxHeight: '90vh'
  //   });
  //   dialogRef.afterClosed().subscribe(result => {
  //     console.log('dialog closed');
  //     this.refreshTable();
  //     this.selection.clear();
  //   });
  // }

  /**
   * Navigates to the [application flaws]{@link BuildFlawsViewComponent} component.
   *
   * Note that "application" in this context refers to a scanned application build on Veracode.
   */
  toAppFlaws(selectedRow: TriggerTableItem) {
    this.router.navigateByUrl('/app/flaws',
      { state:  selectedRow } );
  }

  /**
   * Deletes the selected entry from the table. Prompts the user for confirmation before deletion.
   *
   * @param selectedRow The selected table entry
   *
   * Note that "application" in this context refers to a scanned application build on Veracode.
   */
  deleteApp(selectedRow: TriggerTableItem) {
      if (confirm('Are you sure you want to delete?')) {
      this.appTable.deleteApp(selectedRow).subscribe(result => {
        this.refreshTable();
        this.selection.clear();
      });
    }
  }

  /**
   * DEPRECATED
   * @ignore
   */
  toSchedule(selectedRow: TriggerTableItem) {
    this.router.navigateByUrl('/schedules',
      { state: selectedRow });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  /**
   * Navigates to the [applicaiton metadata]{@link AppMetadataComponent} component.
   *
   */
  toAppMeta() {
    this.router.navigateByUrl('/Admin');
  }

  /**
   * Please ask Tucker Diekmann what this does (DT225453)
   */
  whitelisted() {
    return true;
  }
}
