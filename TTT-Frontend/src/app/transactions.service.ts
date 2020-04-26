import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, pipe } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';



const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};


export interface TriggerTableItem {

}


@Injectable({
  providedIn: 'root'
})
export class TriggerTableService {

  constructor(private http: HttpClient) { }

  // AppTableMainUrl: string = 'api/apps/all';
  // AppTableLimit = '?_limit=15';

  // TODO: Error handle the table

  getTransaction(): Observable<TriggerTableItem[]> {
    return this.http.get<TriggerTableItem[]>('/api/apps/all')
      .pipe(
        catchError(this.handleError)
      );
  }

  deleteTransaction(inputApp: TriggerTableItem): Observable<any> {
    return this.http.delete('/api/apps/')
      .pipe(
        catchError(this.handleError)
      );
  }

  editTrigger(inputApp: TriggerTableItem): Observable<any> {
    return this.http.get('/api/apps/')
      .pipe(
        catchError(this.handleError)
      );
  }


  public handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);

  }


}
