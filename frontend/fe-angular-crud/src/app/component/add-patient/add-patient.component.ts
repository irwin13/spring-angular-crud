import { Component, Input } from '@angular/core';
import { Patient } from '../../model/patient.model';
import { PatientService } from '../../service/patient.service';

@Component({
  selector: 'app-add-patient',
  templateUrl: './add-patient.component.html',
  styleUrls: ['./add-patient.component.css'],
})
export class AddPatientComponent {
  @Input() patient: Patient = {
    firstName: '',
    lastName: ''
  };
  submitted = false;

  constructor(private patientService: PatientService) {}

  savepatient(): void {
    const data = {
      firstName: this.patient.firstName,
      lastName: this.patient.lastName
    };

    this.patientService.create(data).subscribe({
      next: (res) => {
        console.log(res);
        this.submitted = true;
      },
      error: (e) => console.error(e)
    });
  }

  newpatient(): void {
    this.submitted = false;
    this.patient = {
      firstName: '',
      lastName: ''
    };
  }
}