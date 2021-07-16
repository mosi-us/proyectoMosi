package com.mosi.mosi;

import com.mosi.mosi.segurity.JWTAuthorizationFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class MosiApplication {
	private static Logger log = LogManager.getLogger(MosiApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(MosiApplication.class, args);
		log.info("Servicio Iniciado");
	}

}

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/user",
						"/setUserName",
						"/guardarPerfilEstudiante",
						"/consultarEstudiante",
						"/resgistroNuevoEstudiante",
						"/userLogin",
						"/signInEstudiante",
						"/buscar_Practica_desafio",
						"/postularEstudiante",
						"/detalleAsignatura",
						"/cambiarPerfilPrincipal",
						"/agregarPerfilPrincipal",
						"/agregarPasatiempos",
						"/agregarSYT",
						"/AgregarDesafio_Practica",
						"/guardarPerfilEmpresa",
						"/consultarPerfilEmpresa",
						"/guardarPerfilEDetallestudiante",
						"/verDesafio_Practica",
						"/verPostulantes","/consultarPreguntas",
						"/verPostulantes","/verPerfilEstudiante",
						"/seleccionarEstudiante","/verEstudiantesSeleccionados",
						"/sugerirEstudiantes",
						"/rechazarEstudiante",
						"/verPerfilEmpresaEstudiante",
						"/EnviarEmail",
						"/olvidarContrasena" ,
						"/reset_password",
						"/subirImagen",
						"/getImagen",
						"/subirVideo",
						"/ModificarPerfilEstudianteDatosAcademicos","/ModificarPerfilEstudianteDatosGenerales",
						"/eliminarPostulacion",
						"/listarPostulacionesEstudiante",
						"/rechazarPostulacion","/getCarreraByEstudents",
						"/cambiarClave","/desbloquearBloquearPerfil","/buscarPerfil",
						"/modificarPerfilEmpresa","/cambiarNombre","/cambiarCorreoElectronico",
						"/CrearPublicacion","/EditarPublicacion","/EliminarPublicacion",
						"/crearComentario","/EditarComentario","/eliminarComentario",
						"/reaccionarPublicacionComentario","/editarReaccion","/EliminarReaccion",
						"/seguirPersona",
						"/dejarSeguirPersona",
						"/verSeguidores",
						"/verSeguidos",
						"/verCantidadSeguidores","/eliminarSeguidor"

				).permitAll()
				.antMatchers(HttpMethod.GET,
						"/consultarDeportes",
						"/consultarIdiomas",
						"/consultarCarreras",
						"/consultarUniversidades",
						"/consultarPaises",
						"/consultarPasatiempos",
						"/consultarSyT",
						"/consultarEspecialidades",
						"/consultarDeportes",
						"/consultarHabilidadesBlandas",
						"/consultarCiudades","/getallEstudiantes"
					).permitAll()
				.anyRequest().authenticated();
	}
}
