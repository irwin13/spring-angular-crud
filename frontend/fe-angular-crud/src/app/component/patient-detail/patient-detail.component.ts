import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Patient, PatientPage } from '../../model/patient.model';
import { PatientService } from '../../service/patient.service';

@Component({
  selector: 'app-patient-detail',
  templateUrl: './patient-detail.component.html',
  styleUrls: ['./patient-detail.component.css'],
})

export class PatientDetailComponent implements OnInit {
  @Input() viewMode = false;

  @Input() currentPatient: Patient = {
    pid: -1,
    firstName: '',
    lastName: ''
  };

  patientPage: PatientPage = {

  };

  message = '';

  constructor(
    private patientService: PatientService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getPatient(this.route.snapshot.params['pid']);
    }
  }

  getPatient(pid: number): void {
    this.patientService.search(pid, '').subscribe({
      next: (data) => {
        this.patientPage = data;
        console.log(data);
      },
      error: (e) => console.error(e)
    });
  }

  updatePatient(): void {
    this.message = '';

    this.patientService
      .update(this.currentPatient.pid, this.currentPatient)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message
            ? res.message
            : 'This patient was updated successfully!';
        },
        error: (e) => console.error(e)
      });
  }

  deletePatient(): void {
    this.patientService.delete(this.currentPatient.pid).subscribe({
      next: (res) => {
        console.log(res);
        this.router.navigate(['/patient']);
      },
      error: (e) => console.error(e)
    });
  }
}