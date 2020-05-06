import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { HttpHeaders, HttpParams } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type' : 'application/json'
  })
};


export interface TriggerTableItem {
  appName: string;
  appID: number;
  sandboxID: number;
  buildID: number;
  sandboxName: string;
  businessOwner: string;
  businessVertical: string;
  jiraProject: string;
  primaryLanguage: string;
  lastStaticScan: string;
  lastDynamicScan: string;
  lastExcelReport: string;
  email: string;
  cmdb_id: number;
  activeStatus: boolean;
  lastModifiedBy: string;
}

@Injectable({providedIn: 'root'})

export class ViewTriggersService {

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

  private handleError(error: HttpErrorResponse) {

    // clientside error
    if (error.error instanceof ErrorEvent) {
      console.error('An error occured:', error.error.message);
    } else {
      // Backend returned response code
      console.error(`Server returned error code: ${error.status}, ` +
      `body was: ${error.error}`);
    }
    return throwError(
      'Something went wrong. Please try again or check console');
  }
  
}
