import { Component, OnInit } from '@angular/core';
import { PaisListService } from './services/pais-list.service';
import { Pais } from './models/pais.model';
import { ButtonModule } from 'primeng/primeng';
import { Message } from 'primeng/primeng';

@Component({
  selector: 'app-pais-list',
  templateUrl: './pais-list.component.html',
  styleUrls: ['./pais-list.component.css']
})
export class PaisListComponent implements OnInit {
  isLoading = true;
  paises: Array<Pais>;
  pais: Pais;
  msgs: Message[] = [];
  constructor(private _paisListService: PaisListService) {

  }

  ngOnInit() {
    this.getAllPaises();

  }

  onRowSelect(event) {
    this.msgs = [];
    this.msgs.push({ severity: 'success', summary: 'Success Message', detail: this.pais.nombre });
  }

  getAllPaises() {
    this._paisListService.getAll().subscribe(
      (data: Pais[]) => {
        this.paises = data;
        this.isLoading = false;
      },
      err => {
        console.error(err);
      },
      () => {
        console.log("Finised!");

      }
    );
  }

  onCreatePais() {
    //execute action
  }

  onDeletePais(pais: Pais) {
    this._paisListService.deletePais(pais).subscribe((data) => {
      console.log(data);
      this.getAllPaises();
    });
  }

}
