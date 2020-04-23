import { Injectable } from '@angular/core';
import { TriggerTableItem } from './app-table.service';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { HttpHeaders, HttpParams } from '@angular/common/http';
import { catchError } from 'rxjs/operators';

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