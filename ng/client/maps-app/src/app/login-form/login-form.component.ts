import { Component, OnInit } from '@angular/core';

import { LoginForm } from '../loginForm.model';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  model = new LoginForm( '', '');

  constructor() { }
  ngOnInit() {
  }

  submittedLogin = false;

  submitForm(myForm:NgForm) {
    this.submittedLogin = true;
  }
  get currentCredentials() { return JSON.stringify(this.model); }


}
