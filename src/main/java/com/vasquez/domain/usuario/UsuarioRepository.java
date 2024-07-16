package com.vasquez.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //Page<Usuario> findByActivoTrue(Pageable paginacion);
    UserDetails findByCorreoElectronico(String correoElectronico);
}
