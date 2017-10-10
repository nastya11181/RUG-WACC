import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NavComponent } from './nav/nav.component';
import { GoogleMapsComponent } from './google-maps/google-maps.component';
import { LoginModalComponent } from './login-modal/login-modal.component';

import { AgmCoreModule } from 'angular2-google-maps/core';
import { ModalModule } from 'ngx-bootstrap/modal';


const googleMapsCore = AgmCoreModule.forRoot({
  apiKey : 'AIzaSyA0XBPNoJ_DDPdygliKaVftuAYKzFjMdSw',
});

const loginModalCore = ModalModule.forRoot();

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    GoogleMapsComponent,
    LoginModalComponent
  ],
  imports: [
    BrowserModule,
    googleMapsCore,
    loginModalCore
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
