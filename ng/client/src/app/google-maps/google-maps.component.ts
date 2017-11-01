import { Component, OnInit, Injectable } from '@angular/core';


import { HttpClient } from '@angular/common/http';

import { GeoService } from './GeoService';


@Component({
  selector: 'app-google-maps',
  templateUrl: './google-maps.component.html',
  styleUrls: ['./google-maps.component.css'],
  viewProviders : [GeoService]

})

@Injectable()
export class GoogleMapsComponent implements OnInit {

  constructor(private http: HttpClient, private geoService: GeoService) { }
  lat: number = 53.22;
  long: number = 6.55;
  zoom: number = 12;
  draggable: boolean = true;

  private location = "Cuza-Voda 24, Chisinau, Moldova";
  result;



  private url: string = 'api/bikes';
  private bikeMarkers: marker[];
  ngOnInit():void {
    this.http.get<marker[]>(this.url)
      .subscribe(bikes => {
        this.bikeMarkers = bikes;
        console.log(this.bikeMarkers);
        console.log(this.bikeMarkers.length);

        for(let i of this.bikeMarkers)
        {
          if(i.status)
          {
            console.log(this.getAddress(i.coords.x,i.coords.y));
          }
        }

    });


    this.getAddress(53.22,6.55);
    }
    /*
    createTable():void{
      for(var i=0; i<this.bikeMarkers.length; i++)
      {
        console.log(i);
        //console.log(this.bikeMarkers[i].coords.x,this.bikeMarkers[i].coords.y);
      }
    }
    */

    getAddress(lat:number,lng:number): void {
        this.geoService.getLocation(lat,lng)
            .subscribe((response) => {
              this.result = response.results[0];

              console.log(this.result.formatted_address);
            })
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
