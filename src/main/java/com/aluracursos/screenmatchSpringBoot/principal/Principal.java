package com.aluracursos.screenmatchSpringBoot.principal;

import com.aluracursos.screenmatchSpringBoot.model.DatosEpisodio;
import com.aluracursos.screenmatchSpringBoot.model.DatosSerie;
import com.aluracursos.screenmatchSpringBoot.model.DatosTemporadas;
import com.aluracursos.screenmatchSpringBoot.model.Episodio;
import com.aluracursos.screenmatchSpringBoot.service.ConsumoAPI;
import com.aluracursos.screenmatchSpringBoot.service.ConvierteDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=c8914aa7";
    private ConvierteDatos conversor = new ConvierteDatos();

    public void muestraElMenu() {
        System.out.println("Por favor escribe la serie que desea buscar ");
        //Busca los datos generales de la series
        var nombreSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        var datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);

        //Busca los datos de todas las temporadas
        List<DatosTemporadas> temporadas = new ArrayList<>();
        for (int i = 1; i <= datos.totalDeTemporadas(); i++) {
            json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+")
                    + "&Season=" + i + API_KEY);
            var datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class);
            temporadas.add(datosTemporadas);
        }

        // temporadas.forEach(System.out::println);

        //Mostrar el titulo de los episodios paras las temporadas
//        for (int i = 0; i < datos.totalDeTemporadas(); i++) {
//            List<DatosEpisodio> episodiosTemporada=temporadas.get(i).episodios();
//
//            for (int j = 0; j < episodiosTemporada.size(); j++) {
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }
//        }

        // temporadas.forEach(t-> t.episodios().forEach(e-> System.out.println(e.titulo())));

        //Convertir todas las informaciones a una lista del tipo DatosEpisodio
        List<DatosEpisodio> datosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());


        //Top 5 episodios
//        System.out.println("Top 5 episodios");
//        datosEpisodios.stream()
//                .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
//                .peek(e -> System.out.println("Primer filtro (N/A)" + e))
//                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
//                .peek(e -> System.out.println("Segunda Ordenacion (M>M)" + e))
//                .map(e -> e.titulo().toUpperCase())
//                .peek(e -> System.out.println("Tercer Filtro Mayuscula (m>M)" + e))
//                .limit(5)
//                .forEach(System.out::println);


        //Convirtiendo los datos a una lista del tipo Episodio
        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d)))
                .collect(Collectors.toList());
        //episodios.forEach(System.out::println);

        //Busqueda de episodios a patir de x año
//        System.out.println("Indica el año apartir del cual deseas ver los episodios");
//        var fecha = teclado.nextInt();
//        teclado.nextLine();


        //  LocalDate fechaBusqueda=LocalDate.of(fecha,1,1);
        // DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // episodios.stream()
        //        .filter(e->e.getFechaDeLanzamiento() != null && e.getFechaDeLanzamiento().isAfter(fechaBusqueda))
        //       .forEach(e-> System.out.println(
        //              "Temporada"+e.getTemporada()+
        //                      "Episodio"+e.getTitulo()+
        //                     "Fecha de Lanzamiento"+e.getFechaDeLanzamiento().format(dtf)
        //    ));

        //Busca episodios por pedazo del titulo
//        System.out.println("Por favor escriba el titulo del episodio");
//        var pedazoTitulo=teclado.nextLine();
//        Optional<Episodio> episodioBuscado = episodios.stream()
//
//                .filter(e -> e.getTitulo().toUpperCase().contains(pedazoTitulo.toUpperCase()))
//                .findFirst();
//        if(episodioBuscado.isPresent()){
//            System.out.println("Episodio encontrado");
//            System.out.println("Los datos son:"+episodioBuscado.get());
//        }else{
//            System.out.println("Episodio no encontrado");
//        }
        Map <Integer,Double>evaluacionesPorTemporada=episodios.stream()
                .filter(e->e.getEvaluacion()>0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getEvaluacion)));
        System.out.println(evaluacionesPorTemporada);

        DoubleSummaryStatistics est= episodios.stream()
                .filter(e->e.getEvaluacion()>0.0)
                .collect(Collectors.summarizingDouble(Episodio::getEvaluacion));
        System.out.println("MEdia de las Evaluaciones:"+est);
        System.out.println("Episodio Mejor Evaluado:"+est);
        System.out.println("Episodio Peor Evaluado:"+est);


    }

}
