import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions} from '@angular/http';
import { Pais } from '../models/pais.model';

import {Observable} from  'rxjs/Observable';
import 'rxjs/add/operator/map';

@Injectable()
export class PaisListService{
    paises: Array<Pais> = [];
    constructor(private _http: Http){

    }

    getAll() : Observable <Array<Pais>>{
        const url = 'http://localhost:8084/AngularREST-1.0/webresources/WSPais';
        const headers = new Headers({'Content-Type': 'application/json'});
        const options = new RequestOptions({headers: headers});
        return this._http.get(url,options).map((response) => {
            return response.json();
        });
    }
}