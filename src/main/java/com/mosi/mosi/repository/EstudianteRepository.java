package com.mosi.mosi.repository;

import com.mosi.mosi.bean.Estudiante;
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
            "       pai.PAI_Nombre as pais, " +
            "       USU.USU_Nombre_Usuario, " +
            "       est.Est_id," +
            "       PAI.PAI_ID, " +
            "       EST.EST_SEM, " +
            "       est.ESP_Id, " +
            "       est.CAR_Id, " +
            "       est.UNI_Id " +
            "        from EST_Estudiantes EST " +
            "              inner join PAI_Paises pai on PAI.PAI_Id = EST.PAI_Id " +
            "              INNER JOIN USU_USUARIOS USU ON USU.USU_Id = EST.USU_id " +
            "             where usu.usu_id = :idUsu";
    @Query(nativeQuery = true, value = SQL_CONSULTA_ESTUDIANTE)
    List<Object[]> consultaEstudiante(@Param("idUsu") Integer usuId);

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
            "                               ESP.ESP_Nombre as especialidad,  " +
            "                               CAR.CAR_Nombre as carrera,  " +
            "                               UNI.UNI_Nombre as universidad  " +
            "                                from EST_Estudiantes EST  " +
            "                                      INNER JOIN PAI_Paises pai on PAI.PAI_Id = EST.PAI_Id  " +
            "                                      INNER JOIN USU_USUARIOS USU ON USU.USU_Id = EST.USU_id  " +
            "                                      INNER JOIN ESP_Especialidad ESP on EST.ESP_Id = ESP.ESP_Id  " +
            "                                      INNER JOIN CAR_CarrerasUniv CAR on EST.CAR_Id = CAR.CAR_Id  " +
            "                                      INNER JOIN UNI_Universidades UNI on EST.UNI_Id = UNI.UNI_Id" +
            "                                        where usu.usu_id = :idUsu";
    @Query(nativeQuery = true, value = SQL_CONSULTA_PERFIL_COMPLETO)
    List<Object[]> consultaEstudianteCompleto(@Param("idUsu") Integer usuId);

    String SQL_UPDATE_ESTUDIANTE_PRINCIPAL = "UPDATE EST_Estudiantes SET EST_Principal = :Estatus where EST_Id = :idEstudiante";
    @Transactional
    @Modifying
    @Query(value=SQL_UPDATE_ESTUDIANTE_PRINCIPAL, nativeQuery=true)
    int updatePerfilPrincipal(@Param("idEstudiante") int idEstudiante,@Param("Estatus") int Estatus);


    String SQL_CONSULTA_ESTUDIANTE_ACTIVO = "SELECT EST_Id from EST_Estudiantes where USU_Id = :idUsu and EST_Principal = 1 ";
    @Query(nativeQuery = true, value = SQL_CONSULTA_ESTUDIANTE_ACTIVO)
    Integer consultaPerfilActivo(@Param("idUsu") Integer usuId);

}
