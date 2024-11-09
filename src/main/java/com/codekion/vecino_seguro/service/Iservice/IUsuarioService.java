package com.codekion.vecino_seguro.service.Iservice;

import com.codekion.vecino_seguro.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    Optional<Usuario> findByUsernameAndPassword(String username, String password);

    Optional<Usuario> findByUsername(String username);

    Optional<Usuario> findById(Integer id);

    Usuario save(Usuario usuario);

    List<Usuario> findAll();

}
