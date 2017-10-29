import { Component, OnInit, Injectable } from '@angular/core';


import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-google-maps',
  templateUrl: './google-maps.component.html',
  styleUrls: ['./google-maps.component.css']
})

@Injectable()
export class GoogleMapsComponent implements OnInit {

  constructor(private http: HttpClient) { }
  lat: number = 53.22;
  long: number = 6.55;
  zoom: number = 12;
  draggable: boolean = true;

  private url: string = 'api/bikes';
  bikeMarkers;
  ngOnInit():void {
    this.http.get(this.url)
      .subscribe(bikes => {

        this.bikeMarkers = bikes;
        console.log(this.bikeMarkers);
    });


  }




    clickedMarker(label: string, index: number){
     console.log('clicked the marker ' + index + ' ' + label)
   }



   markers: marker[] = [
     {
       id:{
         $oid: "2j23g4j21k3",
       },
       coords:{
          x: 43.2342,
          y: 12.3745,
       },
       status: true

     }
   ];


 }

 interface marker {
   id:{
     $oid: string;
   }
   coords:{
     x: number;
     y: number;
   }
   status: boolean;
 }
