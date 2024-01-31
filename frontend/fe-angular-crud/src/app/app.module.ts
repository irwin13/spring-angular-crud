import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddPatientComponent } from './component/add-patient/add-patient.component';
import { PatientDetailComponent } from './component/patient-detail/patient-detail.component';
import { PatientListComponent } from './component/patient-list/patient-list.component';

@NgModule({
  declarations: [
    AppComponent,
    AddPatientComponent,
    PatientDetailComponent,
    PatientListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
