package com.angular.services;

import com.angular.modelo.Usuario;
import com.angular.util.TokenUtil;
import javax.ejb.Stateless;

@Stateless
public class ServicioUsuario extends GenericCrud {
    
    public Usuario crear(Usuario usuario) {
        usuario.setToken(TokenUtil.generate());
        super.insert(usuario);
        return usuario;
    }
}
