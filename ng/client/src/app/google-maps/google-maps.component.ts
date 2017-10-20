import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-google-maps',
  templateUrl: './google-maps.component.html',
  styleUrls: ['./google-maps.component.css']
})
export class GoogleMapsComponent implements OnInit {

  constructor() { }
  lat: number = 47.0105;
  long: number = 28.8638;
  zoom: number = 10;
  draggable: boolean = true;
  ngOnInit() {
  }
    clickedMarker(label: string, index: number){
     console.log('clicked the marker ${label||index}')
   }



   markers: marker[] = [
     {
       lat: 47.0345,
       lng: 29,
       label: 'A',
       draggable: true
     },
     {
       lat: 46.982,
       lng: 28.84476,
       label: 'B',
       draggable: false
     }
   ];
 }

 interface marker {
   lat: number;
   lng: number;
   label: string;
   draggable: boolean;
 }
