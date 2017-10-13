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

  get currentCredentials() { return JSON.stringify(this.model); }

  submitForm(myForm:NgForm) {
 alert(JSON.stringify(myForm.value));
 }
}
