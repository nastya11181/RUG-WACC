import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NavComponent } from './nav/nav.component';
import { GoogleMapsComponent } from './google-maps/google-maps.component';

import { AgmCoreModule } from 'angular2-google-maps/core';

const googleMapsCore = AgmCoreModule.forRoot({
  apiKey : 'AIzaSyA0XBPNoJ_DDPdygliKaVftuAYKzFjMdSw',
});

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    GoogleMapsComponent
  ],
  imports: [
    BrowserModule,
    googleMapsCore
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
