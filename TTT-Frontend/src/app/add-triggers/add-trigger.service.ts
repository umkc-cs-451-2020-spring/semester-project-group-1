import { Observable, throwError } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';


/*
@Injectable({
  providedIn: 'root'
})
export class AddTriggerService {
  constructor(private http: HttpClient) { }

  getState(inputStateName: string): Observable<State[]> {
    return this.http.get<State[]>('/api/apps/sandbox/' + inputStateName)
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

  addTriggers(inputStateName: string, inputStartTime: string, inputEndTime: string) {
    return this.http.post('/api/apps/' + inputStateName + '/' + inputStartTime, inputEndTime)
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
*/
