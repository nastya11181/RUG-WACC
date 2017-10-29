import { Component, OnInit, Injectable } from '@angular/core';

import { LoginForm } from '../loginForm.model';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})

@Injectable()
export class LoginFormComponent implements OnInit {

  model = new LoginForm( '', '');

  constructor() { }

  ngOnInit() {

  }
  private url: string = 'api/bikes';

  submittedLogin = false;

  submitForm(myForm:NgForm) {
    this.submittedLogin = true;
    console.log('HttpRequest here');


  }

  get currentCredentials() { return JSON.stringify(this.model); }
}
