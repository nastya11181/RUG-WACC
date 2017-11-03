import { Component, OnInit, Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { GeoService } from './GeoService';

import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'app-google-maps',
  templateUrl: './google-maps.component.html',
  styleUrls: ['./google-maps.component.css'],
  viewProviders : [GeoService]

})

@Injectable()
export class GoogleMapsComponent implements OnInit {

  constructor(private http: HttpClient, private geoService: GeoService) {

   }
  lat: number = 53.22;
  long: number = 6.55;
  zoom: number = 12;
  draggable: boolean = true;

  result;



  private url: string = 'api/bikes';
  private bikeMarkers: marker[];
  private bikeys = [];
  private bikey: id_ad_co;
  private counter : number;
  private bikeAddress : id_ad_co[];
  private numbers = [];
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
            this.getAddress(i.coords.x,i.coords.y).subscribe(val =>
            {
              //console.log(this.bikey);
              this.bikey = {
                id: i.id.$oid,
                address: val,
                coords: {
                  x: i.coords.x,
                  y: i.coords.y
                }
              }

              this.bikeys.push(this.bikey);
              //console.log("Log all bikes");
              //console.log(this.bikeys);
            });
          }
        }
    });

    /*
    this.getAddress(53.22,6.55).subscribe(val => {
      console.log(val);
    });

    let btn = document.getElementsByClassName("coolbutton");
    btn.addEventListener("click",(e:Event)=>{
      console.log("Pressed some button");
    })
    */
    }
    selectBike(mickey: id_ad_co):void{
      var body = {id : mickey.address,coords: mickey.coords};
      console.log("Set bike with " + mickey.address + " id availability to 'false'");
      this.http.post(this.url+"/"+mickey.id, body).subscribe();
      //http call to update but status from free to reserved;
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

    getAddress(lat:number,lng:number): Observable<string> {
      return this.geoService.getLocation(lat,lng)
        .map((response) => {
          return this.result = response.results[0].formatted_address;
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
 };

 interface id_ad_co {
   id : string;
   address: string;
   coords: {
     x: number;
     y: number;
   }
 }
