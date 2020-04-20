import { Component, OnInit } from '@angular/core';

export interface Transactions {
  triggerNumber: number;
  triggerType: string;
  triggerSetting: string;
  editTrigger: string;
}

const TRIGGER_DATA: Transactions[] = [
  {triggerNumber: 1, triggerType: 'Cost', triggerSetting: '$100', editTrigger: 'edit'},
  {triggerNumber: 2, triggerType: 'Location', triggerSetting: 'Kansas', editTrigger: 'edit'},
  {triggerNumber: 3, triggerType: 'Timeframe', triggerSetting: '00:00-04:00',  editTrigger: 'edit'},
];

@Component({
  selector: 'app-view-triggers',
  templateUrl: './view-triggers.component.html',
  styleUrls: ['./view-triggers.component.scss']
})
export class ViewTriggersComponent implements OnInit {

  constructor() { }
  
  displayedColumns: string[] = ['triggerNumber', 'triggerType', 'triggerSetting', 'editTrigger'];
  dataSource = TRIGGER_DATA;

  ngOnInit(): void {
  }

}
