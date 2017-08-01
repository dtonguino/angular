import { Component, OnInit } from '@angular/core';

import { ButtonModule } from 'primeng/primeng';
import { ConfirmationService } from 'primeng/primeng';

import { DataTable } from 'app/common/core/data-table';
import { PaisListService } from './services/pais-list.service';
import { Pais } from './models/pais.model';

import { HttpService } from 'app/common/services/http.service';

@Component({
  selector: 'app-pais-list',
  templateUrl: './pais-list.component.html',
  styleUrls: ['./pais-list.component.css']
})
export class PaisListComponent extends DataTable<Pais> implements OnInit {

  /**
   * Cosntructor por defecto de la clase.
   * @param _paisListService instancia del servicio de paises
   */
  constructor(public _servicioCrud: HttpService, public _confirmationService: ConfirmationService, ) {
    super(_servicioCrud, _confirmationService, 'http://localhost:8084/AngularREST-1.0/webresources/WSPais', '');
  }

  /**
   * Método que inicializa las variables de la página.
   */
  ngOnInit() {
    super.getAllRecords();
  }

}
