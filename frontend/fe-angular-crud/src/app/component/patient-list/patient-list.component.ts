import { Component, OnInit } from '@angular/core';
import { Patient, PatientPage } from '../../model/patient.model';
import { PatientService } from '../../service/patient.service';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css'],
})
export class PatientListComponent implements OnInit {
  patientPage?: PatientPage;
  currentPatient: Patient = {};
  currentIndex = -1;
  pid = -1;
  name = '';

  constructor(private PatientService: PatientService) {}

  ngOnInit(): void {
    this.retrievePatients();
  }

  retrievePatients(): void {
    this.PatientService.search(this.pid, this.name).subscribe({
      next: (data) => {
        this.patientPage = data;
        console.log(data);
      },
      error: (e) => console.error(e)
    });
  }

  refreshList(): void {
    this.retrievePatients();
    this.currentPatient = {};
    this.currentIndex = -1;
  }

}