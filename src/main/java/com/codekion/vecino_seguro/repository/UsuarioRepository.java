package com.codekion.vecino_seguro.repository;


import com.codekion.vecino_seguro.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE u.correo = ?1")
    Optional<Usuario> findByUsername(String username);

    @Query("SELECT u FROM Usuario u WHERE u.correo = ?1 AND u.password = ?2")
    Optional<Usuario> findByUsernameAndPassword(String username, String password);
}