import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { RegisterForm } from '../registerForm.model';

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
