package com.angular.services;

import java.util.List;
import javax.ejb.Stateless;

/**
 * La clase ServicioCrud es el servicio gen\u00e9rico para realizar las
 * operaciones CRUD b\u00e1sicas
 *
 * @author Diego Tonguino
 */
@Stateless
public class ServicioCrud extends GenericCrud {

    /**
     * Inserta el registro en la base de datos.
     *
     * @param entidad la entidad a ser persistida
     */
    public void insertar(Object entidad) {
        super.insert(entidad);
    }

    /**
     * Actualiza el registro en la base de datos.
     *
     * @param <T> la entidad
     * @param entidad la entidad a ser actualizada
     * @return la entidad actualizada
     */
    public <T> T actualizar(T entidad) {
        return super.update(entidad);
    }

    /**
     * Elimina el registro de la base de datos.
     *
     * @param entidad la entidad a ser eliminada
     * @throws java.lang.Exception
     */
    public void eliminar(Object entidad) throws Exception {
        super.delete(entidad);
    }

    /**
     * Obtiene la entidad por su clave primaria.
     *
     * @param <T> la entidad
     * @param primaryKey la clave primaria de la entidad
     * @param entityClass la clase de la entidad
     * @return la entidad
     */
    public <T> T buscarPorPK(Object primaryKey, Class<T> entityClass) {
        return super.findByPK(primaryKey, entityClass);
    }

    /**
     * Obtiene la entidad por su clave primaria.
     *
     * @param <T> la entidad
     * @param entidad la entidad que contiene la clave primaria
     * @return la entidad
     * @throws java.lang.Exception
     */
    public <T> T buscarPorId(Object entidad) throws Exception {
        return super.findById((T) entidad);
    }

    /**
     * Obtiene una lista de registros del tipo de entidad enviada como
     * par\u00e1metro.
     *
     * @param <T> la entidad
     * @param entity la entidad que contiene los par\u00e1metros
     * @param order Opcional. El orden para la consulta
     * @return lista de registros
     */
    public <T> List<T> buscar(T entity, String... order) {
        return super.find(entity, order);
    }
}
