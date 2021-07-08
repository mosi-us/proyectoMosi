package com.mosi.mosi.repository;

import com.mosi.mosi.bean.Estudiante;
import com.mosi.mosi.bean.Usuarios;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EstudianteRepository extends CrudRepository<Estudiante,Integer> {

    String SQL_CONSULTA_ESTUDIANTE = "  select est.EST_Nombres AS Nombre, " +
            "       est.EST_Apellidos as apellido, " +
            "       est.EST_Fecha_Nac as fechaNac, " +
            "       usu.USU_id," +
            "       USU.USU_Nombre_Usuario, " +
            "       est.Est_id, " +
            "       PAI.PAI_ID, " +
            "       pai.PAI_Nombre as pais, " +
            "       EST.EST_SEM, " +
            "       est.CAR_id, " +
            "       CCU.CAR_Nombre, " +
            "       est.UNI_Id, " +
            "       uu.UNI_Nombre, " +
            "       est.EST_Descripcion, " +
            "       est.EST_Email, " +
            "       EST.EST_CodPais + ' ' + EST.EST_Telefono as telefono, " +
            "       ciu.CIU_Id," +
            "       ciu.CIU_Nombre," +
            "       est.EST_Lugar " +
            "from EST_Estudiantes EST " +
            "                        inner join PAI_Paises pai on PAI.PAI_Id = EST.PAI_Id " +
            "                        inner join CIU_Ciudades ciu on CIU.CIU_Id = est.CIU_Id " +
            "                        inner join USU_USUARIOS USU ON USU.USU_Id = EST.USU_id " +
            "                        inner join CAR_CarrerasUniv CCU on EST.CAR_Id = CCU.CAR_Id " +
            "                        inner join UNI_Universidades UU on EST.UNI_Id = UU.UNI_Id " +
            "                         where ((usu.usu_id = :idUsu or EST.EST_Id = :estId) and EST.EST_Principal=1)";
    @Query(nativeQuery = true, value = SQL_CONSULTA_ESTUDIANTE)
    List<Object[]> consultaEstudiante(@Param("idUsu") Integer usuId,@Param("estId") Integer estId);

    String SQL_CONSULTA_NOMBRE_DEPORTE = "SELECT * FROM UNI_Universidades WHERE UNI_ID in :idUni ";
    @Query(nativeQuery = true, value = SQL_CONSULTA_NOMBRE_DEPORTE)
    List<Object[]> consultaUniversidadEstudiante(@Param("idUni") List<Integer> idUni);
    
    String SQL_CONSULTA_PERFIL_COMPLETO = "select est.EST_Nombres AS Nombre,  " +
            "                               est.EST_Apellidos as apellido,  " +
            "                               est.EST_Fecha_Nac as fechaNac,  " +
            "                               pai.PAI_Nombre as pais, " +
            "                               USU.USU_Nombre_Usuario as Usuario,  " +
            "                               est.Est_id as id,  " +
            "                                pai.pai_id, " +
            "                               EST.EST_SEM as semestre,  " +
            "                               CAR.CAR_Nombre as carrera,  " +
            "                               UNI.UNI_Nombre as universidad  " +
            "                                from EST_Estudiantes EST  " +
            "                                      INNER JOIN PAI_Paises pai on PAI.PAI_Id = EST.PAI_Id  " +
            "                                      INNER JOIN USU_USUARIOS USU ON USU.USU_Id = EST.USU_id  " +
            "                                      INNER JOIN CAR_CarrerasUniv CAR on EST.CAR_Id = CAR.CAR_Id  " +
            "                                      INNER JOIN UNI_Universidades UNI on EST.UNI_Id = UNI.UNI_Id" +
            "                                        where usu.usu_id = :idUsu or est EST_Id = :estId ";
    @Query(nativeQuery = true, value = SQL_CONSULTA_PERFIL_COMPLETO)
    List<Object[]> consultaEstudianteCompleto(@Param("idUsu") Integer usuId,@Param("estId") Integer estId);

    String SQL_UPDATE_ESTUDIANTE_PRINCIPAL = "UPDATE EST_Estudiantes SET EST_Principal = :Estatus where EST_Id = :idEstudiante";
    @Transactional
    @Modifying
    @Query(value=SQL_UPDATE_ESTUDIANTE_PRINCIPAL, nativeQuery=true)
    int updateStatusPerfil(@Param("idEstudiante") int idEstudiante,@Param("Estatus") int Estatus);


    String SQL_CONSULTA_ESTUDIANTE_ACTIVO = "SELECT * from EST_Estudiantes where est_id = :est_id and EST_Principal = 1 ";
    @Query(nativeQuery = true, value = SQL_CONSULTA_ESTUDIANTE_ACTIVO)
    Estudiante consultaPerfilActivo(@Param("est_id") Integer est_id);

    String SQL_CONSULTA_ESTUDIANTES_ACTIVOS = "SELECT * from EST_Estudiantes where est_id in (:est_id) and EST_Principal = 1 ";
    @Query(nativeQuery = true, value = SQL_CONSULTA_ESTUDIANTES_ACTIVOS)
    List<Estudiante> consultaPerfilesActivos(@Param("est_id") List<Integer> est_id);

    List<Estudiante> findByUsuario(Usuarios user);

    String SQL_CONSULTA_ESTUDIANTE_SUGERIDO= "select * from EST_Estudiantes EST " +
            "where est.CAR_Id in (:carrera) and est.PAI_Id in (:pais) and EST_Principal = 1 ";
    @Query(nativeQuery = true, value = SQL_CONSULTA_ESTUDIANTE_SUGERIDO)
    List<Estudiante> consultarEstudiantesSugeridos(@Param("carrera") List<Integer> carrera,@Param("pais") List<Integer> pais );

    String SQL_CONSULTA_ESTUDIANTE_POR_USUARIO= "select * from EST_Estudiantes EST " +
            "where USU_ID = :usuId ";
    @Query(nativeQuery = true, value = SQL_CONSULTA_ESTUDIANTE_POR_USUARIO)
    List<Estudiante> findEstudianteByUsuario(@Param("usuId") Integer idUser);

    String SQL_CONSULTA_ESTUDIANTE_POR_CARRERA= "select * from EST_Estudiantes EST " +
            "where CAR_ID in (:carIds) and usu_id= :usuId";
    @Query(nativeQuery = true, value = SQL_CONSULTA_ESTUDIANTE_POR_CARRERA)
    List<Estudiante> consultarEstudiantesPorCarreras(@Param("carIds")List<?> perfiles,
                                                     @Param("usuId") Integer usuId);

    List<Estudiante> findByIdGreaterThan(int i);

    String SQL_CONSULTA_ESTUDIANTE_EMPRESA_POR_NOMBRE = "select" +
            "       EST_ID as id, " +
            "1 as tipo " +
            "   from EST_Estudiantes " +
            "   where UPPER(EST_Nombres +' '+ EST_Apellidos) like %:nombreBuscar% and EST_Principal = 1 " +
            "   union" +
            "   select EMP_ID as id," +
            "       2 as tipo " +
            "   from EMP_Empresa" +
            "   where UPPER(EMP_Nombre) like %:nombreBuscar%";
    @Query(nativeQuery = true, value = SQL_CONSULTA_ESTUDIANTE_EMPRESA_POR_NOMBRE)
    List<Object[]> buscarEstudiantesPorNombre(@Param("nombreBuscar") String nombreABuscar);
}
