package com.ejercicios.json;

import com.google.gson.Gson;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Persona {
    String nombre; int edad;
    Persona(String n,int e){ nombre=n; edad=e; }
    @Override public String toString(){ return nombre+" ("+edad+")"; }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        File archivo = new File("personas.json");
        List<Persona> personas = new ArrayList<>();

        if (archivo.exists()) {
            try(FileReader fr = new FileReader(archivo)){
                Persona[] arr = gson.fromJson(fr, Persona[].class);
                personas = new ArrayList<>(List.of(arr));
            }
        }

        personas.add(new Persona("Ashley",24));
        personas.add(new Persona("Adara",2));
        personas.add(new Persona("Kaylee",5));



        try(FileWriter fw = new FileWriter(archivo)){
            gson.toJson(personas, fw);
        }

        System.out.println("Personas que se ven en el run");
        personas.forEach(System.out::println);
    }
}
