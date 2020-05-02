import { LoginScreenComponent } from './../login-screen/login-screen.component';
import { ViewTransactionsComponent } from './../view-transactions/view-transactions.component';
import { EditTriggerService } from './../edit-triggers/edit-trigger.service';
import { TriggerTableItem } from './../transactions.service';
import { TriggerTableService } from './../transactions.service';
import { AfterViewInit, Component, OnInit, ViewChild, Inject } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { TooltipPosition } from '@angular/material/tooltip';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatIconRegistry } from '@angular/material/icon';
import {SelectionModel} from '@angular/cdk/collections';
import {DomSanitizer} from '@angular/platform-browser';
import { Router, ActivatedRoute} from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { trigger } from '@angular/animations';




export interface Transactions {
  triggerNumber: number;
  triggerType: string;
  triggerSetting: string;
  editTrigger: string;
}

const TRIGGER_DATA: Transactions[] = [
  {triggerNumber: 1, triggerType: 'Cost', triggerSetting: '$100', editTrigger: 'edit'},
  {triggerNumber: 2, triggerType: 'Location', triggerSetting: 'Kansas', editTrigger: 'edit'},
  {triggerNumber: 3, triggerType: 'Timeframe', triggerSetting: '00:00-04:00',  editTrigger: 'edit'},
];

/**
 * The Veracode Builds Component.
 *
 * Displays a table of all Veracode scans pulled into the application.
 * Empty sandbox names indicate a promoted build.
 */
@Component({
  selector: 'app-view-triggers',
  templateUrl: './view-triggers.component.html',
  styleUrls: ['./view-triggers.component.scss']
})


export class ViewTriggersComponent implements OnInit {


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
   * Associate state passed in from the [login]{@link LoginComponent} component
   */
  //state$: Observable<Associate>;

  /**
   * The currently logged in user.
   */
  //user: Associate;

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
  displayedColumns: string[] = [/*'triggerNumber',*/ 'triggerType', 'triggerSetting', 'editTrigger'];


  constructor(public appTable: TriggerTableService, public dialog: MatDialog, public snackBar: MatSnackBar,
              public iconRegistry: MatIconRegistry, public sanitizer: DomSanitizer, public router: Router,
              public activatedRoute: ActivatedRoute) {
    iconRegistry.addSvgIcon(
      'view-trigger',
      sanitizer.bypassSecurityTrustResourceUrl('assets/icons/baseline-add_box-24px.svg'));
  }

  /**
   * Refreshes the table data
   */
  refreshTable() {
    this.snackBar.open('Attempting to refresh table...', 'Okay', {
      duration: 3000
    });
    this.appTable.getTransaction().subscribe(data => {
      this.dataSource.data = TRIGGER_DATA;
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
   *//*
  refreshApps() {
    this.appTable.refreshTable(this.dataSource.data).subscribe(data => {
      if (data !== '200' && data !== 200) {
        this.snackBar.open('Error Code: ' + data + '. Error refreshing application data.', 'Close', {duration: 5000});
      }
    });
  }
*/
  ngOnInit() {

    if (this.session.getItem('user') !== 'undefined' && this.session.length > 1) {
      /* User data to string (for session) */

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
        const auth = this.session.getItem('authHeader');
        this.session.clear();
        this.session.setItem('authHeader', auth);

        this.session.setItem('start', JSON.stringify(new Date()));
      };

      this.loggedIn = true;
      this.refreshTable();
    }

    toDashboard(){
      this.router.navigateByUrl('/app/dashboard');
    }

  }
