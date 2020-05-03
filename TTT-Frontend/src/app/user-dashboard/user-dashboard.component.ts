import { ViewTransactionsComponent } from './../view-transactions/view-transactions.component';
import { TransactionTableItem, TransactionTableService } from './../transactions.service';
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
// import { trigger } from '@angular/animations';



@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.scss']
})
export class UserDashboardComponent implements OnInit {

  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: false }) sort: MatSort;
  @ViewChild(MatTable, { static: false }) table: MatTable<TransactionTableItem>;

  /**
   * Build table's data source
   */
  dataSource = new MatTableDataSource<TransactionTableItem>();

  /**
   * Tooltip position
   */
  abovePosition: TooltipPosition = 'below';

  /**
   * The array of column names for the table to use
   */
  displayedColumns: string[] = [/*'accountID',*/ 'transactionTime', 'transactionType', 'amount', /*'transactionID',*/
  'state', 'category', 'transactionDescription'];

  constructor(public transactionService: TransactionTableService, public dialog: MatDialog, public snackBar: MatSnackBar,
              public iconRegistry: MatIconRegistry, public sanitizer: DomSanitizer, public router: Router) {
  }

  /**
   * Refreshes the table data
   */
  refreshTable() {
    this.snackBar.open('Attempting to refresh table...', 'Okay', {
      duration: 3000
    });
    this.transactionService.getTransaction().subscribe(data => {
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
  ngOnInit() {
      this.refreshTable();
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


toEditTriggers(selectedRow: EditTriggersComponent) {
  const dialogRef = this.dialog.open(EditTriggersComponent, {
    width: '20em',
    maxHeight: '90vh',
    data: selectedRow
  });

  dialogRef.afterClosed().subscribe(result => {
    console.log('dialog closed');
    this.refreshTable();
  });
}

toAddTrigger() {
  const dialogRef = this.dialog.open(AddTriggersComponent, {
    width: '45em',
    maxHeight: '90vh'
  });
  dialogRef.afterClosed().subscribe(result => {
    console.log('dialog closed');
    this.refreshTable();
  });
}

toViewTransactions() {
  this.router.navigateByUrl('app/transactions');
}


toViewNotifications() {
  this.router.navigateByUrl('app/notifications');
}

toViewTriggers(){
  this.router.navigateByUrl('app/triggers');
}

/*
viewTransactions(selectedRow: ViewTransactionsComponent) {
  if (confirm('Are you sure you want to delete?')) {
    this.appTable.deleteApp(selectedRow).subscribe(result => {
      this.refreshTable();
      this.selection.clear();
    });
  }
}*/
}
