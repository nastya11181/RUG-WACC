import { Component, OnInit } from '@angular/core';

import { RegisterForm } from '../registerForm.model';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {

  model = new RegisterForm( '','','','','','');

  constructor() { }

  ngOnInit() {
  }
  submittedRegister = false;

  get currentCredentials() { return JSON.stringify(this.model); }

  submitForm(myForm:NgForm) {
    this.submittedRegister = true;
 alert(JSON.stringify(myForm.value));
 }
}
