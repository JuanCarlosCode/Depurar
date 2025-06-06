import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GestorEstudiantes {

    // Calcula la nota media de un estudiante
    public static double calcularNotaMedia(Estudiante estudiante) {
        double suma = 0;
        for (int i = 0; i < estudiante.getNotas().length; i++) { // Error: índice fuera de rango
            suma += estudiante.getNotas()[i];
        }
        if (suma!=0)  return suma / estudiante.getNotas().length; // Error si el array está vacío
        else return 0;
    }

    // Encuentra al estudiante con la mejor nota media
    public static Estudiante encontrarMejorEstudiante(Estudiante[] estudiantes) {
        try {
            Estudiante mejor = null;
            double mejorNota = -1;

            for (Estudiante estudiante : estudiantes) {
                double media = calcularNotaMedia(estudiante); // Posible fallo aquí
                if (media > mejorNota) {
                    mejorNota = media;
                    mejor = estudiante;
                }
            }
            return mejor; // Error si la lista está vacía
        }catch (NullPointerException e){
            throw new RuntimeException(e);
        }
    }

    // Guarda los resultados en un fichero
    public static void guardarResultados(Estudiante[] estudiantes, String rutaFichero) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaFichero))) {
            for (Estudiante estudiante : estudiantes) {
                writer.write("Nombre: " + estudiante.getNombre() + ", Nota Media: " +
                        calcularNotaMedia(estudiante)); // Posible fallo si calcularNotaMedia lanza una excepción
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el fichero: " + e.getMessage());
        }
    }
}
