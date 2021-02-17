package com.mosi.mosi.repository;

import com.mosi.mosi.bean.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<Users,Integer> {
    Users findByEmail(String email);

    Users findById(int id);
    String SQL_EXIST_EMAIL = "SELECT top 1 USU_email FROM USU_USUARIOS WHERE USU_email = :email";
    @Query(nativeQuery = true, value = SQL_EXIST_EMAIL)
    String buscarEmail(@Param("email") String email);

    String  SQL_UDATE_TOKEN_USUARIO = "update USU_USUARIOS SET USU_Token = :token where USU_Id= :usuId";

    @Transactional
    @Modifying
    @Query(value=SQL_UDATE_TOKEN_USUARIO, nativeQuery=true)
    int updateTokenByIdUser(@Param("usuId") int usuId, @Param("token") String token);
}
