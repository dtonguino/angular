import { Component, OnInit } from '@angular/core';
import { PaisListService } from './services/pais-list.service';
import { Pais } from './models/pais.model';
@Component({
  selector: 'app-pais-list',
  templateUrl: './pais-list.component.html',
  styleUrls: ['./pais-list.component.css']
})
export class PaisListComponent implements OnInit {
  isLoading = true;
  paises: Array<Pais>;
  constructor(private _paisListService:PaisListService) {

  }

  ngOnInit() {
    this._paisListService.getAll().subscribe(
      (data: Pais[]) => {
        this.paises=data;
        this.isLoading=false;
      },
      err => {
        console.error(err);
      },
      () => {
        console.log("Finised!");
        
      }
    );
  }

}
