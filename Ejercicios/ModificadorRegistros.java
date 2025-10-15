import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ModificadorRegistros {

    public static void main(String[] args) {

        String registroActualizado = "";

        String archivoEntrada = "registro_operaciones.csv";
        StringBuilder contenido = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoEntrada))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }

            String textoOriginal = contenido.toString();
            registroActualizado = textoOriginal.replace(";", " | ");

            System.out.println(" Lectura y actualización de registros completada");
            System.out.println("---------------------------------------------");
            System.out.print(registroActualizado);
            System.out.println("---------------------------------------------");

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        try (FileWriter fw = new FileWriter("registro_operaciones_modificado.txt")) {
            fw.write(registroActualizado);
            System.out.println("\nArchivo guardado como: registro_operaciones_modificado.txt");
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo modificado: " + e.getMessage());
        }
    }
}

