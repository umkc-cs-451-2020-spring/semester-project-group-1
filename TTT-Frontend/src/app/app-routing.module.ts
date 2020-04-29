import { ViewTriggersComponent } from './view-triggers/view-triggers.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { ViewNotificationsComponent } from './view-notifications/view-notifications.component';
import { ViewTransactionsComponent } from './view-transactions/view-transactions.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  { path: '', component: UserDashboardComponent},
  { path: 'app/transactions', component: ViewTransactionsComponent},
  { path: 'app/notifications', component: ViewNotificationsComponent},
  { path: 'app/triggers', component: ViewTriggersComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
