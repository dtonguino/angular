import { EnumEstadoRegistro } from '../enumeraciones/estado-registro.enum';

/**
 * Clase que maneja los estados de los registros.
 */
export class EstadoBotonesUtil {
    /**
     * El estado actual del registro.
     */
    estado: EnumEstadoRegistro;
    
    /**
     * Constructor por defecto de la clase.
     */
    constructor() {
        this.alEstadoInicial();
    }

    /**
     * Cambia el estado al estado INICIAL.
     */
    protected alEstadoInicial() {
        this.estado = EnumEstadoRegistro.INICIAL;
    }

    /**
     * Cambia el estado al estado NUEVO.
     */
    protected alEstadoNuevo() {
        this.estado = EnumEstadoRegistro.NUEVO;
    }

    /**
     * Cambia el estado al estado MODIFICAR.
     */
    protected alEstadoModificar() {
        this.estado = EnumEstadoRegistro.MODIFICAR;
    }

    /**
     * Cambia el estado al estado SELECCIONADO.
     */
    protected alEstadoSeleccionado() {
        this.estado = EnumEstadoRegistro.SELECCIONADO;
    }

    /**
     * Cambia el estado al estado SOLO_LECTURA.
     */
    protected alEstadoSoloLectura() {
        this.estado = EnumEstadoRegistro.SOLO_LECTURA;
    }

    /**
     * Indica si el registro est\u00e1 en estado seleccionado o no.
     * @return TRUE: si est\u00e1, FALSE: caso contrario
     */
    public estadoSeleccionado() {
        return EnumEstadoRegistro.SELECCIONADO == this.estado;
    }

    /**
     * Indica si el registro est\u00e1 en estado nuevo o no.
     * @return TRUE: si est\u00e1, FALSE: caso contrario
     */
    public estadoNuevo() {
        return EnumEstadoRegistro.NUEVO == this.estado;
    }

    /**
     * Indica si el registro est\u00e1 en estado de modificaci\u00f3n o no.
     * @return TRUE: si est\u00e1, FALSE: caso contrario
     */
    public estadoModificar() {
        return EnumEstadoRegistro.MODIFICAR == this.estado;
    }

    /**
     * Indica si el registro est\u00e1 en estado de solo lectura o no.
     * @return TRUE: si est\u00e1, FALSE: caso contrario
     */
    public estadoSoloLectura() {
        return EnumEstadoRegistro.SOLO_LECTURA == this.estado;
    }
}