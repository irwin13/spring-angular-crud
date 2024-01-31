import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddPatientComponent } from './component/add-patient/add-patient.component';
import { PatientDetailComponent } from './component/patient-detail/patient-detail.component';
import { PatientListComponent } from './component/patient-list/patient-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'patient', pathMatch: 'full' },
  { path: 'patient', component: PatientListComponent },
  { path: 'patient/:id', component: PatientDetailComponent },
  { path: 'add', component: AddPatientComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }