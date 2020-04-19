import { Component, OnInit } from '@angular/core';

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
  selector: 'app-view-triggers',
  templateUrl: './view-triggers.component.html',
  styleUrls: ['./view-triggers.component.scss']
})
export class ViewTriggersComponent implements OnInit {

  constructor() { }
  
  displayedColumns: string[] = ['accountID', 'transactionTime', 'transactionType', 'amount', 'transactionID', 'state', 'category', 'transactionDescription'];
  dataSource = TRANSACTION_DATA;

  ngOnInit(): void {
  }

}
