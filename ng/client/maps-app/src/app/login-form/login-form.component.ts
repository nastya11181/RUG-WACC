import { Component, OnInit } from '@angular/core';

import { LoginForm } from '../loginForm.model';

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

  get currentCredentials() { return JSON.stringify(this.model); }


}
