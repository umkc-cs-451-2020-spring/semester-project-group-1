import { ViewTransactionsComponent } from './../view-transactions/view-transactions.component';
import { EditTriggerService } from './../edit-triggers/edit-trigger.service';
import { TriggerTableItem, TriggerTableService } from './../transactions.service';
import { EditTriggersComponent } from './../edit-triggers/edit-triggers.component';
import { AddTriggersComponent } from './../add-triggers/add-triggers.component';
import { AfterViewInit, Component, OnInit, ViewChild, Inject } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { TooltipPosition } from '@angular/material/tooltip';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatIconRegistry } from '@angular/material/icon';
import { SelectionModel } from '@angular/cdk/collections';
import { DomSanitizer } from '@angular/platform-browser';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AppRoutingModule } from '../app-routing.module';
import { trigger } from '@angular/animations';


@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.scss']
})
export class UserDashboardComponent implements OnInit, AfterViewInit {

  /**
  * Paginator for the table
  */
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;

  /**
   * Sorter for the table
   */
  @ViewChild(MatSort, { static: false }) sort: MatSort;

  /**
   * The Application table
   *
   * Note that "application" in this context refers to a scanned application build on Veracode.
   */
  @ViewChild(MatTable, { static: false }) table: MatTable<TriggerTableItem>;

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
  displayedColumns = ['Buttons', 'appName', /*'appID', 'sandboxID', 'buildID',*/ 'Transaction_ID', 'Transaction_type', 'Transaction_time',
    'State', 'Category', 'Transaction_description', 'Amount',
                      /* 'lastExcelReport','email',*/ 'Account_id', /*'activeStatus', */];

  constructor(public appTable: TriggerTableService, public dialog: MatDialog, public snackBar: MatSnackBar,
    public iconRegistry: MatIconRegistry, public sanitizer: DomSanitizer, public router: Router) {
    iconRegistry.addSvgIcon(
      'add-trigger',
      sanitizer.bypassSecurityTrustResourceUrl('assets/icons/baseline-add_box-24px.svg'));
    iconRegistry.addSvgIcon(
      'edit-trigger',
      sanitizer.bypassSecurityTrustResourceUrl('assets/icons/baseline-edit-24px.svg'));
    iconRegistry.addSvgIcon(
      'delete-trigger',
      sanitizer.bypassSecurityTrustResourceUrl('assets/icons/baseline-delete-24px.svg'));
  }
  ngAfterViewInit(): void {
    throw new Error("Method not implemented.");
  }

  /**
   * Refreshes the table data
   */
  refreshTable() {
    this.snackBar.open('Attempting to refresh table...', 'Okay', {
      duration: 3000
    });
    this.appTable.getTransaction().subscribe(data => {
      this.dataSource.data = data;
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
      this.snackBar.open('Table successfully loaded!', 'Okay', {
        duration: 1000
      });
    }, (err) => {
      this.snackBar.open(err, 'Okay', { duration: 10000 });
    });
  }

  /**
   * Refreshes all listed applications by pulling all latest promoted scans
   */
  // refreshApps() {
  //   this.appTable.refreshApps(this.dataSource.data).subscribe(data => {
  //     if (data !== '200' && data !== 200) {
  //       this.snackBar.open('Error Code: ' + data + '. Error refreshing application data.', 'Close', {duration: 5000});
  //     }
  //   });
  // }

  ngOnInit() {

    if (this.session.getItem('user') !== 'undefined' && this.session.length > 1) {
      /* User data to string (for session) */
      // this.user = JSON.parse(this.session.getItem('user'));
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
      // this.state$ = this.activatedRoute.paramMap.pipe(map(() => window.history.state));
      // this.state$.subscribe(data => {
      //   this.user = data[0];
      const auth = this.session.getItem('authHeader');
      this.session.clear();
      this.session.setItem('authHeader', auth);
      // this.session.setItem('user', JSON.stringify(this.user));
      this.session.setItem('start', JSON.stringify(new Date()));
    };
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

// ngAfterViewInit() {
//   if (this.loggedIn){
//     this.refreshTable();
//   }

// }

/**
 * Navigates to the [edit component]{@link EditTriggersComponent} for scan-specific metadata
 *
 * Note that "application" in this context refers to a scanned application build on Veracode.
 *
 * @param selectedRow The selected table entry
 */
openDialog(selectedRow: EditTriggersComponent) , {
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
 * Navigates to the ["Add New Application"]{@link AddTriggersComponent} stepper component
 *
 * Note that "application" in this context refers to a scanned application build on Veracode.
 */
toAddTrigger() {
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
 * Deletes the selected entry from the table. Prompts the user for confirmation before deletion.
 *
 * @param selectedRow The selected table entry
 *
 * Note that "application" in this context refers to a scanned application build on Veracode.
 */
viewTransactions(selectedRow: ViewTransactionsComponent) {
  if (confirm('Are you sure you want to delete?')) {
    this.appTable.deleteApp(selectedRow).subscribe(result => {
      this.refreshTable();
      this.selection.clear();
    });
  }
}

