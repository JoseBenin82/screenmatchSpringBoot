package com.aluracursos.screenmatchSpringBoot;

import com.aluracursos.screenmatchSpringBoot.model.DatosEpisodio;
import com.aluracursos.screenmatchSpringBoot.model.DatosSerie;
import com.aluracursos.screenmatchSpringBoot.model.DatosTemporadas;
import com.aluracursos.screenmatchSpringBoot.principal.Principal;
import com.aluracursos.screenmatchSpringBoot.service.ConsumoAPI;
import com.aluracursos.screenmatchSpringBoot.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchSpringBootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchSpringBootApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal();
        principal.muestraElMenu();


    }
}
