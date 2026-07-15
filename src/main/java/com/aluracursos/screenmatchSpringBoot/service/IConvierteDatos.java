package com.aluracursos.screenmatchSpringBoot.service;

public interface IConvierteDatos {

   <T>T  obtenerDatos(String json, Class<T>clase);

}
