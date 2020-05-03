import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';


export interface TriggerTableItem {
  userID: number;
  triggerID: number;
  rule: string;
}

@Injectable({
  providedIn: 'root'
})
export class TriggersService {

  constructor(private http: HttpClient) { }

  addTrigger(inputRule: String) {
    return this.http.post('/api/StateTrigger/add/' + inputRule, {})
      .pipe(
        catchError(this.handleError)
      );
  }

  getTriggers(): Observable<TriggerTableItem[]> {
    return this.http.get<TriggerTableItem[]>('/api/StateTrigger/all')
        .pipe(
          catchError(this.handleError)
        );
  }

  editTrigger(inputTrigger: TriggerTableItem){
    return this.http.put('/api/StateTrigger/update', inputTrigger)
      .pipe(
        catchError(this.handleError)
      );
  }

  deleteTrigger(inputTriggerID: number){
    return this.http.delete('/api/StateTrigger/delete/' + inputTriggerID)
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
