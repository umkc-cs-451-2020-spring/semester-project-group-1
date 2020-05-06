import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, pipe } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { TriggerTableItem } from './triggers.service';



const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};


export interface TransactionTableItem {
  transactionID: number;
  transactionType: string;
  transactionTime: string;
  state: string;
  category: string;
  transactionDescription: string;
  amount: string;
  accountID: number;
}

@Injectable({
  providedIn: 'root'
})
export class TransactionTableService {

  constructor(private http: HttpClient) { }

  // AppTableMainUrl: string = 'api/apps/all';
  // AppTableLimit = '?_limit=15';

  // TODO: Error handle the table

  getTransaction(): Observable<TransactionTableItem[]> {
    return this.http.get<TransactionTableItem[]>('/api/transaction/all')
      .pipe(
        catchError(this.handleError)
      );
  }

  public handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);

  }


}
