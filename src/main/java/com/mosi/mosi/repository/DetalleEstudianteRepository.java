package com.mosi.mosi.repository;

import com.mosi.mosi.bean.Asignatura;
import com.mosi.mosi.bean.DetalleEstudiante;
import com.mosi.mosi.bean.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetalleEstudianteRepository extends JpaRepository<DetalleEstudiante, Integer> {

    String SQL_CONSULTA_ESTUDIANTE_EMPRESA = "select * from DET_ESTUDIANTE DET" +
            " inner join PAM_PaisMaestro pam on DET.DET_ID = pam.DET_Id " +
            "left join POS_Postulaciones pos on pos.ASI_Id = det.ASI_Id and EST_Id = :estId" +
            "            where ((DET.CAR_Id = :idCar AND PAM.PAI_ID = :idPais) OR (DET_SEM= :semestre ) AND (DET_Tipo=:tipo))";
    @Query(nativeQuery = true, value = SQL_CONSULTA_ESTUDIANTE_EMPRESA)
    List<DetalleEstudiante> consultar_estudiantes_empresa(@Param("idCar") Integer idsCar,
                                                          @Param("idPais") Integer idPais,
                                                          @Param("semestre") Integer semestre,
                                                          @Param("tipo") Integer tipo,
                                                          @Param("estId") Integer estId);

    String SQL_CONSULTAR_LUGAR_TRABAJO= "select ASI.ASI_Lugar from ASI_Asignatura ASI " +
            "INNER JOIN DET_Estudiante DE on ASI.ASI_id = DE.ASI_Id WHERE ASI.ASI_id =:asiId";
    @Query(nativeQuery = true,value = SQL_CONSULTAR_LUGAR_TRABAJO)
    Integer consultaLugarTrabajo (@Param("asiId") Integer asiId);

    List<DetalleEstudiante> findByAsignatura (Asignatura asignatura);
    List<DetalleEstudiante> findByAsignaturaAndEmpresa (Asignatura asignatura, Empresa empresa);
}
