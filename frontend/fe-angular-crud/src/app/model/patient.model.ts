export class Patient {
    pid?: number;
    firstName?: string;
    lastName?: string;
    dateOfBirth?: Date;
    gender?: string;
    phoneNo?: string;
}

export class PatientPage {
    content?: Patient[];
    totalRecord?: number;
    next?: boolean;
    previous?: boolean;
}