import { EstadoBotones } from 'app/common/core/estado-botones';
import { HttpService } from 'app/common/services/http.service';
import { Message, ConfirmDialogModule, ConfirmationService } from 'primeng/primeng';

export class DataTable<T> extends EstadoBotones {

    isLoading = true;
    lista: Array<T>;
    actual: T;
    respaldo: T;
    msgs: Message[] = [];

    constructor(public _servicioCrud: HttpService, public _confirmationService: ConfirmationService, public _url: String, public _token: String) {
        super();
    }

    onRowSelect(event) {
        super.alEstadoSeleccionado();
    }

    getAllRecords() {
        this._servicioCrud.get(this._url, this._token).subscribe(
            (data: T[]) => {
                this.lista = data;
                this.isLoading = false;
            },
            err => {
                console.error('Se ha producido el siguiente error: ' + err);
            },
            () => {
                //Finalizado con exito
            }
        );
    }

    onCreate() {
        this.actual = {} as T;
        super.alEstadoNuevo();
    }

    onUpdate() {
        this.respaldo = Object.assign({}, this.actual);
        super.alEstadoModificar();
    }

    onDelete(_id: any) {
        this._confirmationService.confirm({
            header: 'Eliminar',
            message: '¿Está seguro que desea eliminar el registro seleccionado?',
            icon: 'fa fa-question-circle',
            accept: () => {
                this._servicioCrud.delete(this._url + '/' + _id, this._token).subscribe((data) => {
                    this.getAllRecords();
                });
                this.msgs = [];
                this.msgs.push({ severity: 'success', summary: 'Confirmación', detail: 'Se ha eliminado el registro correctamente.' });
                super.alEstadoInicial();
            }
        });
    }

    onDetail() {
        super.alEstadoSoloLectura();
    }

    onSave() {
        this.getAllRecords();
        super.alEstadoEdicion();
    }

    onCancel() {
        this.actual = Object.assign({}, this.respaldo);
        //Investigar como actualizar el modelo luego de asignar el respaldo
        //Temporal getAllRecords()
        this.getAllRecords();
        super.alEstadoEdicion();
    }

    getMsgRecords() {
        return 'En total existen ' + this.lista.length + ' registros';
    }
}