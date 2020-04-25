import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';
import { Router, ActivatedRoute } from '@angular/router';

export interface Transactions {
  transactionID: number;
  transactionTime: string;
  transactionType: string;
  state: string;
  category: string;
  transactionDescription: string;
  amount: string;
  accountID: number;
}

const TRANSACTION_DATA: Transactions[] = [
  {transactionID: 123, state: 'Kansas', category: 'purchase', transactionDescription: "drink", accountID: 1, transactionTime: '13:00', transactionType: 'food', amount: '12.00'},
  {transactionID: 123, state: 'Kansas', category: 'purchase', transactionDescription: "drink", accountID: 1, transactionTime: '13:00', transactionType: 'food', amount: '12.00'},
  {transactionID: 123, state: 'Kansas', category: 'purchase', transactionDescription: "drink", accountID: 1, transactionTime: '13:00', transactionType: 'food', amount: '12.00'},
  {transactionID: 123, state: 'Kansas', category: 'purchase', transactionDescription: "drink", accountID: 1, transactionTime: '13:00', transactionType: 'food', amount: '12.00'},
  {transactionID: 123, state: 'Kansas', category: 'purchase', transactionDescription: "drink", accountID: 1, transactionTime: '13:00', transactionType: 'food', amount: '12.00'},
  {transactionID: 123, state: 'Kansas', category: 'purchase', transactionDescription: "drink", accountID: 1, transactionTime: '13:00', transactionType: 'food', amount: '12.00'},
  {transactionID: 123, state: 'Kansas', category: 'purchase', transactionDescription: "drink", accountID: 1, transactionTime: '13:00', transactionType: 'food', amount: '12.00'},
  {transactionID: 123, state: 'Kansas', category: 'purchase', transactionDescription: "drink", accountID: 1, transactionTime: '13:00', transactionType: 'food', amount: '12.00'},

];

@Component({
  selector: 'app-view-transactions',
  templateUrl: './view-transactions.component.html',
  styleUrls: ['./view-transactions.component.scss']
})

export class ViewTransactionsComponent implements OnInit {

  constructor(/*public transactionTable: TransactionTableService,*/ public dialog: MatDialog, public snackBar: MatSnackBar,
    public iconRegistry: MatIconRegistry, public sanitizer: DomSanitizer, public router: Router,
    public activatedRoute: ActivatedRoute ) { }

    displayedColumns: string[] = [/*'accountID',*/ 'transactionTime', 'transactionType', 'amount', /*'transactionID',*/ 'state', 'category', 'transactionDescription'];
    dataSource = TRANSACTION_DATA;

  ngOnInit(): void {
  }

  /*
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

  toEditApp(selectedRow: AppTableItem) {
    const dialogRef = this.dialog.open(EditAppPageComponent, {
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

  toAddApp() {
    const dialogRef = this.dialog.open(AddAppPageComponent, {
      width: '45em',
      maxHeight: '90vh'
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('dialog closed');
      this.refreshTable();
      this.selection.clear();
    });
  }

  toHighLevelWizard() {
    const dialogRef = this.dialog.open(HighLevelReportWizardComponent, {
      width: '40em',
      maxHeight: '90vh'
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('dialog closed');
      this.refreshTable();
      this.selection.clear();
    });

  }

  toAppFlaws(selectedRow: AppTableItem) {
    this.router.navigateByUrl('/app/flaws',
      { state:  selectedRow } );
  }
  */

}

