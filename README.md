# Objeto-a-un-JSON-existente
Se añaden nuevos nombres a un JSON ya creado usando el "New Persona".

#  Descripción
Proyecto Java (Java 17 + Maven) que muestra cómo leer, modificar y volver a guardar un archivo JSON existente usando Gson. Se carga una lista de objetos Persona desde personas.json, se agrega un nuevo objeto a la lista y se guarda nuevamente.
Requisitos
Java 17 o superior
Maven 3 o superior
Conexión a Internet para que Maven descargue la dependencia de Gson.


#  Estructura del proyecto
ObjetoaJsonExistente/
 ├─ pom.xml
 └─ src/
     └─ main/
         └─ java/
             └─ com/
                 └─ ejercicios/
                     └─ json/
                         ├─ Main.java
                         └─ Persona.java
Dependencia principal (pom.xml)
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.10.1</version>
</dependency>
Código principal
Persona.java
package com.ejercicios.json;

public class Persona {
    private String nombre;
    private int edad;

    public Persona() {}
    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return nombre + " (" + edad + ")";
    }
}
Main.java
package com.ejercicios.json;

import com.google.gson.Gson;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        File archivo = new File("personas.json");
        List<Persona> personas = new ArrayList<>();

        // Leer lista existente si el archivo existe
        if (archivo.exists()) {
            try (Reader r = new FileReader(archivo)) {
                Persona[] arr = gson.fromJson(r, Persona[].class);
                personas = new ArrayList<>(List.of(arr));
            }
        }

        // Agregar nueva persona
        personas.add(new Persona("Sofía", 30));

        // Guardar lista actualizada
        try (Writer w = new FileWriter(archivo)) {
            gson.toJson(personas, w);
        }

        // Mostrar lista en consola
        System.out.println("Personas actuales:");
        personas.forEach(System.out::println);
    }
}
#  Funcionamiento
Se comprueba si personas.json existe.
Se carga la lista existente de objetos Persona.
Se agrega una nueva Persona a la lista.
Se guarda la lista completa nuevamente en personas.json.
Se imprime la lista actualizada en consola.


#  Ejemplo de salida
Personas que aparecen en el run:
Ashley (24)
Adara (2)
Kaylee (5)

#  Contenido del archivo personas.json después de ejecutar
[
  {"nombre":"Ashley","edad":24},
  {"nombre":"Adara","edad":2},
  {"nombre":"Kaylee","edad"5}
]

#  Cómo ejecutar
1.  Abrir la carpeta Ejercicio4 en IntelliJ IDEA (File > Open…).
2.  Abrir Main.java y pulsar Run ▶️. Maven descargará automáticamente Gson.
   
#  Buenas prácticas
Validar que el archivo JSON esté bien formado antes de leerlo.
Usar GsonBuilder().setPrettyPrinting() para generar JSON legible.
Manejar excepciones con try-catch para errores de I/O.
Mantener la codificación UTF-8 al leer/escribir archivos.
Este ejercicio simula un CRUD básico sobre JSON, agregando objetos a listas existentes.
