import { Component, OnInit } from '@angular/core';

import { ButtonModule } from 'primeng/primeng';
import { Message } from 'primeng/primeng';

import { EstadoBotonesUtil } from 'app/util/estado-botones-util';
import { PaisListService } from './services/pais-list.service';
import { Pais } from './models/pais.model';

@Component({
  selector: 'app-pais-list',
  templateUrl: './pais-list.component.html',
  styleUrls: ['./pais-list.component.css']
})
export class PaisListComponent extends EstadoBotonesUtil implements OnInit {
  isLoading = true;
  paises: Array<Pais>;
  pais: Pais;
  msgs: Message[] = [];

  /**
   * Cosntructor por defecto de la clase.
   * @param _paisListService instancia del servicio de paises
   */
  constructor(private _paisListService: PaisListService) {
    super();
  }

  /**
   * Método que inicializa las variables de la página.
   */
  ngOnInit() {
    this.getAllPaises();
  }

  onRowSelect(event) {
    this.msgs = [];
    this.msgs.push({ severity: 'success', summary: 'Success Message', detail: this.pais.nombre });
    super.alEstadoSeleccionado();
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
    super.alEstadoNuevo();
  }

  onUpdatePais() {
    super.alEstadoNuevo();
  }

  onDeletePais(pais: Pais) {
    this._paisListService.deletePais(pais).subscribe((data) => {
      console.log(data);
      this.getAllPaises();
    });
    super.alEstadoInicial();
  }

}
