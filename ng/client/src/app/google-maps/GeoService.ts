import { Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class GeoService {
  constructor(private http: HttpClient){}
  getLocation(lat: number, lng: number):any {
    return this.http.get('https://maps.googleapis.com/maps/api/geocode/json?latlng='+lat+','+lng);
}

}
