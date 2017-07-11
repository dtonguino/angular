/*
 * Copyright (C) 2014 Diego Tonguino
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.angular.webservices.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;

/**
 * La clase GenericCrud es utilizado para la administraci\u00f3n de las
 * operaciones de persistencia definidas por JPA.
 *
 * @author Diego Tonguino
 */
public abstract class GenericCrud {

    /**
     * El manejador de persistencia de entidades.
     */
    @PersistenceContext(unitName = "punit")
    private EntityManager em;

    /**
     * Inserta el registro en la base de datos.
     *
     * @param entity la entidad a ser persistida
     */
    protected void insert(Object entity) {
        this.em.persist(entity);
    }

    /**
     * Actualiza el registro en la base de datos.
     *
     * @param <T> la entidad
     * @param entity la entidad a ser actualizada
     * @return la entidad actualizada
     */
    protected <T> T update(T entity) {
        return this.em.merge(entity);
    }

    /**
     * Elimina el registro de la base de datos.
     *
     * @param entity la entidad a ser eliminada
     * @throws java.lang.Exception
     */
    protected void delete(Object entity) throws Exception {
        try {
            Class<?> clase = Class.forName(entity.getClass().getName());
            Field[] atributos = clase.getDeclaredFields();
            for (Field field : atributos) {
                if (field.getAnnotation(Id.class) != null) {
                    String codigo = field.getName();
                    String getCampo = "get" + codigo.substring(0, 1).toUpperCase() + codigo.substring(1);
                    Method metodoGet = clase.getMethod(getCampo);
                    Object valor = metodoGet.invoke(entity);
                    Object obj = this.findByPK(valor, clase);
                    this.em.remove(obj);
                    break;
                } else if (field.getAnnotation(EmbeddedId.class) != null) {
                    Method metodoPk = clase.getMethod("getPk");
                    Object valorPk = metodoPk.invoke(entity);
                    Object obj = this.findByPK(valorPk, clase);
                    this.em.remove(obj);
                    break;
                }
            }
        } catch (ClassNotFoundException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
            throw new Exception(e);
        }
    }

    /**
     * Obtiene la entidad por su clave primaria.
     *
     * @param <T> la entidad
     * @param primaryKey la clave primaria de la entidad
     * @param entityClass la clase de la entidad
     * @return la entidad
     */
    protected <T> T findByPK(Object primaryKey, Class<T> entityClass) {
        return this.em.find(entityClass, primaryKey);
    }

    /**
     * Obtiene la entidad por su clave primaria.
     *
     * @param <T> la entidad
     * @param entity la entidad que contiene la clave primaria
     * @return la entidad
     * @throws java.lang.Exception
     */
    protected <T> T findById(T entity) throws Exception {
        try {
            Class<?> clase = Class.forName(entity.getClass().getName());
            Field[] atributos = clase.getDeclaredFields();
            for (Field field : atributos) {
                if (field.getAnnotation(Id.class) == null) {
                    continue;
                }
                String codigo = field.getName();
                String getCampo = "get" + codigo.substring(0, 1).toUpperCase() + codigo.substring(1);
                Method metodoGet = clase.getMethod(getCampo);
                Object valor = metodoGet.invoke(entity);
                return this.findByPK(valor, (Class<T>) clase);
            }
        } catch (ClassNotFoundException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
            throw new Exception(e);
        }
        return null;
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
    protected <T> List<T> find(T entity, String... order) {
        StringBuilder sql = new StringBuilder();
        try {
            Class<?> clase = Class.forName(entity.getClass().getName());
            Field[] atributos = clase.getDeclaredFields();
            List<Object> params = new ArrayList<>();
            StringBuilder codigoOrder = new StringBuilder();
            sql.append("select t from ");
            sql.append(entity.getClass().getName());
            sql.append(" t");
            sql.append(" where 1 = 1");
            for (Field field : atributos) {
                try {
                    if (field.getAnnotation(Id.class) != null) {
                        codigoOrder.append("t.").append(field.getName()).append(", ");
                    } else if (field.getAnnotation(EmbeddedId.class) != null) {
                        Method metodoPk = clase.getMethod("getPk");
                        Class<?> clasePk = metodoPk.getReturnType();
                        Object entityPK = metodoPk.invoke(entity);
                        Field[] atributosPk = clasePk.getDeclaredFields();
                        for (Field fieldPk : atributosPk) {
                            try {
                                if (fieldPk.getAnnotation(Column.class) == null) {
                                    continue;
                                }
                                codigoOrder.append("t.pk.").append(fieldPk.getName()).append(", ");
                                String campoPk = fieldPk.getName();
                                String getCampoPk = "get" + campoPk.substring(0, 1).toUpperCase() + campoPk.substring(1);
                                Method metodoGet = clasePk.getMethod(getCampoPk);
                                Object valorPk = metodoGet.invoke(entityPK);
                                if (valorPk == null) {
                                    continue;
                                }
                                sql.append(" and t.pk.");
                                sql.append(campoPk);
                                if (valorPk instanceof String && StringUtils.contains(valorPk.toString(), "%")) {
                                    sql.append(" like ?");
                                } else {
                                    sql.append(" = ?");
                                }
                                sql.append(params.size() + 1);
                                params.add(valorPk);
                            } catch (NoSuchMethodException e) {
                                System.out.println("Se ha producido el siguiente error: " + e.getMessage());
                            }
                        }
                    }
                    if (field.getAnnotation(Column.class) == null) {
                        continue;
                    }
                    String campo = field.getName();
                    String getCampo = "get" + campo.substring(0, 1).toUpperCase() + campo.substring(1);
                    Method metodoGet = clase.getMethod(getCampo);
                    Object valor = metodoGet.invoke(entity);
                    if (valor == null) {
                        continue;
                    }
                    sql.append(" and t.");
                    sql.append(campo);
                    if (valor instanceof String && StringUtils.contains(valor.toString(), "%")) {
                        sql.append(" like ?");
                    } else {
                        sql.append(" = ?");
                    }
                    sql.append(params.size() + 1);
                    params.add(valor);
                } catch (NoSuchMethodException e) {
                    System.out.println("Se ha producido el siguiente error: " + e.getMessage());
                }
            }
            if (order != null && order.length > 0) {
                StringBuilder camposOrder = new StringBuilder();
                for (int i = 0; i < order.length; i++) {
                    if (StringUtils.isNotBlank(order[i])) {
                        camposOrder.append("t.").append(order[i]).append(", ");
                    }
                }
                if (StringUtils.isNotBlank(camposOrder)) {
                    sql.append(" order by ").append(camposOrder.substring(0, camposOrder.length() - 2));
                }
            } else if (StringUtils.isNotBlank(codigoOrder)) {
                sql.append(" order by ");
                sql.append(codigoOrder.substring(0, codigoOrder.length() - 2));
            }
            return this.getResultList(sql, params, (Class<T>) entity.getClass());
        } catch (ClassNotFoundException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            System.out.println("Se ha producido el siguiente error: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Crea una instancia de Query.
     *
     * @param sql la sentencia sql
     * @return una instancia de Query
     */
    protected Query createQuery(StringBuilder sql) {
        return this.em.createQuery(sql.toString());
    }

    /**
     * Crea una instancia de TypedQuery.
     *
     * @param <T> la entidad
     * @param sql la sentencia sql
     * @param resultClass la clase de la entidad resultado
     * @return una instancia de TypedQuery
     */
    protected <T> TypedQuery<T> createQuery(StringBuilder sql, Class<T> resultClass) {
        return this.em.createQuery(sql.toString(), resultClass);
    }

    /**
     * Obtiene una lista de registros del tipo de entidad enviada como
     * par\u00e1metro.
     *
     * @param <T> la entidad
     * @param sql la sentencia sql
     * @param resultClass la clase de la entidad resultado
     * @return lista de registros
     */
    protected <T> List<T> getResultList(StringBuilder sql, Class<T> resultClass) {
        TypedQuery<T> query = this.createQuery(sql, resultClass);
        return query.getResultList();
    }

    /**
     * Obtiene una lista de registros del tipo de entidad enviada como
     * par\u00e1metro.
     *
     * @param <T> la entidad
     * @param sql la sentencia sql
     * @param arg el par\u00e1metro de la sentencia sql
     * @param resultClass la clase de la entidad resultado
     * @return lista de registros
     */
    protected <T> List<T> getResultList(StringBuilder sql, Object arg, Class<T> resultClass) {
        TypedQuery<T> query = this.createQuery(sql, resultClass);
        query.setParameter(1, arg);
        return query.getResultList();
    }

    /**
     * Obtiene una lista de registros del tipo de entidad enviada como
     * par\u00e1metro.
     *
     * @param <T> la entidad
     * @param sql la sentencia sql
     * @param args lista de par\u00e1metros de la sentencia sql
     * @param resultClass la clase de la entidad resultado
     * @return lista de registros
     */
    protected <T> List<T> getResultList(StringBuilder sql, List<Object> args, Class<T> resultClass) {
        TypedQuery<T> query = this.createQuery(sql, resultClass);
        for (int i = 0; i < args.size(); i++) {
            if (args.get(i) instanceof Date) {
                query.setParameter(i + 1, (Date) args.get(i), TemporalType.DATE);
            } else {
                query.setParameter(i + 1, args.get(i));
            }
        }
        return query.getResultList();
    }

    /**
     * Retorna una referencia al objeto que maneja las operaciones de
     * persistencia definidas por JPA.
     *
     * @return Referencia al objeto que maneja las operaciones de persistencia.
     * En caso de que el objeto no este inicializado lanza la excepci\u00f3n
     * @see java.lang.IllegalStateException
     */
    protected EntityManager getEntityManager() {
        if (this.em == null) {
            throw new IllegalStateException(
                    "EntityManager has not been set on Service before usage");
        }
        return this.em;
    }
}
