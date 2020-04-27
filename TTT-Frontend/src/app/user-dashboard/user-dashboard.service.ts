import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { HttpHeaders, HttpParams } from '@angular/common/http';



const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type' : 'application/json'
  })
};


export interface TriggerTableItem {
  triggerID: number;
  functionality: string;
  currentSettings: string;
}

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
export class UserDashboardService {


  constructor(private http: HttpClient) { }

  // AppTableMainUrl: string = 'api/apps/all';
  // AppTableLimit = '?_limit=15';

  // TODO: Error handle the table

  getApps(): Observable<TriggerTableItem[]> {
    return this.http.get<TriggerTableItem[]>('/api/apps/all')
    .pipe(
      catchError(this.handleError)
    );
  }

  deleteApp(inputApp: TriggerTableItem): Observable<any> {
    return this.http.delete('/api/apps/')
    .pipe(
      catchError(this.handleError)
    );
  }
  public handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
}

}
