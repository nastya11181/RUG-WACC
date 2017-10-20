import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';

// Navigation bar
import { NavComponent } from './nav/nav.component';

// Google Maps
import { GoogleMapsComponent } from './google-maps/google-maps.component';
import { AgmCoreModule } from 'angular2-google-maps/core';
const googleMapsCore = AgmCoreModule.forRoot({
   apiKey : 'AIzaSyA0XBPNoJ_DDPdygliKaVftuAYKzFjMdSw',
 });

// Login & Register
import { LoginFormComponent } from './login-form/login-form.component';
import { FormsModule } from '@angular/forms';
import { RegisterFormComponent } from './register-form/register-form.component';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    GoogleMapsComponent,
    LoginFormComponent,
    RegisterFormComponent
  ],
  imports: [
    BrowserModule,
    googleMapsCore,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
