import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class EscrituraBinaria {

    public static void main(String[] args) {
        File carpetaDestino = new File("unidad1_pruebas");
        if (!carpetaDestino.exists()) {
            carpetaDestino.mkdirs();
        }

        File ficheroBinario = new File(carpetaDestino, "mensaje_bytes.bin");
        System.out.println("Creando archivo en: " + ficheroBinario.getAbsolutePath());

        try (FileOutputStream salida = new FileOutputStream(ficheroBinario)) {
            salida.write(77);
            salida.write(117);
            salida.write(110);
            salida.write(100);
            salida.write(111);

            byte[] cadena = {32, 66, 121, 116, 101, 115, 33};
            salida.write(cadena);

            System.out.println("Escritura finalizada en " + ficheroBinario.getName());
        } catch (IOException ex) {
            System.err.println("Ocurrió un error al guardar los datos: " + ex.getMessage());
        }
    }
}
