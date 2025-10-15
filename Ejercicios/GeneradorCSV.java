import java.io.FileWriter;
import java.io.IOException;

public class GeneradorCSV {

    public static void main(String[] args) {
        String nombreArchivo = "registro_operaciones.csv";

        String cabecera = "Intento;Accion;Resultado;Ruta\n";
        String linea1 = "1;Crear archivo;Éxito;unidad1_pruebas/archivo_demo.txt\n";
        String linea2 = "2;Crear copia;Éxito;unidad1_pruebas/archivo_demo_copia1.txt\n";
        String linea3 = "3;Crear copia;Falló;unidad1_pruebas/archivo_demo_copia2.txt\n";
        String linea4 = "4;Mover archivo;Éxito;unidad1_pruebas/duplicados/archivo_demo.txt\n";

        String contenido = cabecera + linea1 + linea2 + linea3 + linea4;

        try (FileWriter fw = new FileWriter(nombreArchivo)) {
            fw.write(contenido);

            System.out.println("Archivo CSV generado correctamente:");
            System.out.println("→ Nombre: " + nombreArchivo);
            System.out.println("→ Contenido guardado:");
            System.out.println("--------------------------------");
            System.out.print(contenido);
            System.out.println("--------------------------------");

        } catch (IOException e) {
            System.err.println("Error al escribir el CSV: " + e.getMessage());
        }
    }
}
