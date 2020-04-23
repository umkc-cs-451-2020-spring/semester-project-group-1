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
    return this.http.delete('/api/apps/' + inputApp.appID + '/' + inputApp.buildID)
    .pipe(
      catchError(this.handleError)
    );
  }

  refreshApps(apps: TriggerTableItem[]): Observable<any> {
    return this.http.post('/api/apps/refresh', apps)
    .pipe(
      catchError(this.handleError)
    );
}
