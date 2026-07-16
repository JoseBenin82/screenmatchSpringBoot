package com.aluracursos.screenmatchSpringBoot;

import com.aluracursos.screenmatchSpringBoot.model.DatosEpisodio;
import com.aluracursos.screenmatchSpringBoot.model.DatosSerie;
import com.aluracursos.screenmatchSpringBoot.model.DatosTemporadas;
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
        var consumoApi= new ConsumoAPI();
        var json=consumoApi.obtenerDatos("http://www.omdbapi.com/?t=game+of+thrones&apikey=c8914aa7");
        System.out.println(json);
        ConvierteDatos conversor =new ConvierteDatos();
        var datos=conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);
        json=consumoApi.obtenerDatos("https://www.omdbapi.com/?t=game+of+thrones&Season=1&episode=1&apikey=c8914aa7");
        DatosEpisodio episodios=conversor.obtenerDatos(json, DatosEpisodio.class);
        System.out.println(episodios);

        List<DatosTemporadas> temporadas=new ArrayList<>();
        for (int i = 1; i <=datos.totalDeTemporadas() ; i++) {
        json=consumoApi.obtenerDatos("https://www.omdbapi.com/?t=game+of+thrones&Season="+i+"&apikey=c8914aa7");
        var datosTemporadas=conversor.obtenerDatos(json, DatosTemporadas.class);
        temporadas.add(datosTemporadas);
        }
        temporadas.forEach(System.out::println);
    }
}
