import { TriggerTableItem } from './../user-dashboard/user-dashboard.service';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError  } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EditTriggerService {

  constructor(private http: HttpClient) { }

  editEntry(editedAppInfo: TriggerTableItem) {
    return this.http.post('/api/apps/update', editedAppInfo)
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