import { Component, OnInit } from '@angular/core';


export interface Notifications {
  notificationID: number; //transaction ID
  typeOfNotification: string; // transaction time
  date: string; //transactionType
  description: string; //state
}

const NOTIFICATION_DATA: Notifications[] = [
  {notificationID: 1, description: 'Transaction over $100',  typeOfNotification: 'Cost', date: '19/04/2020', },
  {notificationID: 2, description: 'Transaction at 03:00',  typeOfNotification: 'Timeframe', date: '19/04/2020', },
  {notificationID: 3, description: 'Transaction in Texas',  typeOfNotification: 'Location', date: '19/04/2020', },
  {notificationID: 4, description: 'Transaction over $100',  typeOfNotification: 'Cost', date: '19/04/2020', },
  {notificationID: 5, description: 'Transaction at 03:00',  typeOfNotification: 'Timeframe', date: '19/04/2020', },
  {notificationID: 6, description: 'Transaction in Texas',  typeOfNotification: 'Location', date: '19/04/2020', },
   
];


@Component({
  selector: 'app-view-notifications',
  templateUrl: './view-notifications.component.html',
  styleUrls: ['./view-notifications.component.scss']
})
export class ViewNotificationsComponent implements OnInit {

  constructor() { }

  displayedColumns: string[] = ['typeOfNotification', 'date', 'notificationID', 'description'];
  dataSource = NOTIFICATION_DATA;


  ngOnInit(): void {
  }

}
