import { Observable, throwError } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';

export interface State {
  value: string;
  viewValue: string;
}

export interface StartTime {
  value: string;
  viewValue: string;
}

export interface EndTime {
  value: string;
  viewValue: string;
}

@Injectable({
  providedIn: 'root'
})
export class AddTriggerService {
  constructor(private http: HttpClient) { }

  getState(inputAppName: string): Observable<State[]> {
    return this.http.get<State[]>('/api/apps/sandbox/' + inputAppName)
      .pipe(
        catchError(this.handleError)
      );
  }

  getStartTime(): Observable<StartTime[]> {
    return this.http.get<StartTime[]>('/api/apps/list')
      .pipe(
        catchError(this.handleError)
      );
  }

  getEndTime(): Observable<EndTime[]> {
    return this.http.get<EndTime[]>('/api/apps/list')
      .pipe(
        catchError(this.handleError)
      );
  }

  addTriggers(inputAppName: string, inputSandboxName: string, inputLastModifiedBy: string) {
    return this.http.post('/api/apps/' + inputAppName + '/' + inputSandboxName, inputLastModifiedBy)
      .pipe(
        catchError(this.handleError)
      );
  }

  addPromotedBuild(inputAppName: string, inputLastModifiedBy: string) {
    return this.http.post('/api/apps/promoted/' + inputAppName, inputLastModifiedBy)
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
