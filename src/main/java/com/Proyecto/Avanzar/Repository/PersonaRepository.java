package com.Proyecto.Avanzar.Repository;

import com.Proyecto.Avanzar.Models.Persona;
import com.Proyecto.Avanzar.Models.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    @Query(value = "SELECT * FROM persona p JOIN usuarios u ON p.id_persona = u.persona_id_persona WHERE u.username = :username", nativeQuery = true)
    public Persona obtenerPersona(String username);
    
    @Query(value = "SELECT * FROM persona p JOIN usuarios u ON p.id_persona = u.persona_id_persona WHERE u.id = :id", nativeQuery = true)
    public Persona obtenerPersonaUsuario(Long id);

    public Persona findByCedula(String cedula);

    public Persona findByCorreo(String correo);

    @Query(value =
            "SELECT " +
                    "  COUNT(DISTINCT p.id) AS Usuarios, " +
                    "  (SELECT COUNT(*) FROM usuario_rol WHERE rol_rol_id = 3) AS Emprendedoras, " +
                    "  (SELECT COUNT(*) FROM usuarios WHERE visible = true AND enabled = true) AS UsuariosActivos, " +
                    "  (SELECT COUNT(*) FROM usuario_rol WHERE rol_rol_id = 4) AS Clientes, " +
                    "  (SELECT COUNT(*) FROM publicaciones WHERE categoria_id_categoria = 1) AS PublicacionProductos, " +
                    "  (SELECT COUNT(*) FROM publicaciones WHERE categoria_id_categoria = 2) AS PublicacionServicios, " +
                    "  (SELECT COUNT(*) FROM publicaciones) AS TotalPublicaciones " +
                    "FROM " +
                    "  usuarios p", nativeQuery = true)
    Map<String, Object> contarRegistrosEnTablas();
}
