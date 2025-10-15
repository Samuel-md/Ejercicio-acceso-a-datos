import java.io.File;
import java.io.IOException;

public class Main {

    private static final int LIMITE_COPIAS = 2;

    public static File buscarArchivoLibre(File original) {
        if (!original.exists()) {
            return original;
        }

        String nombreCompleto = original.getName();
        int posicionPunto = nombreCompleto.lastIndexOf('.');
        String base, extension;

        if (posicionPunto != -1) {
            base = nombreCompleto.substring(0, posicionPunto);
            extension = nombreCompleto.substring(posicionPunto);
        } else {
            base = nombreCompleto;
            extension = "";
        }

        File carpetaPadre = original.getParentFile();

        for (int i = 1; i <= LIMITE_COPIAS; i++) {
            String nuevoNombre = base + "_copia" + i + extension;
            File posibleArchivo = new File(carpetaPadre, nuevoNombre);

            if (!posibleArchivo.exists()) {
                System.out.println("→ Archivo disponible encontrado: " + nuevoNombre);
                return posibleArchivo;
            }
        }

        String nombreCarpeta = base + "_duplicados";
        File carpetaDestino = new File(carpetaPadre, nombreCarpeta);
        System.out.println("→ Límite de copias alcanzado. Revisando carpeta: " + carpetaDestino.getName());

        if (!carpetaDestino.exists()) {
            carpetaDestino.mkdirs();
            System.out.println("→ Carpeta creada en: " + carpetaDestino.getAbsolutePath());
            return new File(carpetaDestino, nombreCompleto);
        } else {
            File nuevoIntento = new File(carpetaDestino, nombreCompleto);
            return buscarArchivoLibre(nuevoIntento);
        }
    }

    public static void main(String[] args) throws IOException {
        File rutaPrincipal = new File("unidad1_pruebas");
        if (!rutaPrincipal.exists()) {
            rutaPrincipal.mkdirs();
        }

        String nombreBase = "archivo_demo.txt";
        File archivoInicial = new File(rutaPrincipal, nombreBase);

        System.out.println("Procesando archivo: " + archivoInicial.getName());
        System.out.println("Ubicación raíz: " + rutaPrincipal.getAbsolutePath());
        System.out.println("--------------------------");

        int repeticiones = 7;
        for (int intento = 1; intento <= repeticiones; intento++) {
            File nuevoArchivo = buscarArchivoLibre(archivoInicial);

            try {
                if (nuevoArchivo.createNewFile()) {
                    System.out.println("Intento " + intento + ": Creado correctamente → " + nuevoArchivo.getAbsolutePath());
                } else {
                    System.out.println("Intento " + intento + ": El archivo ya existía → " + nuevoArchivo.getAbsolutePath());
                }
            } catch (IOException ex) {
                System.err.println("Error al crear el archivo: " + ex.getMessage());
            }
            System.out.println("--------------------------");
        }

        System.out.println("Comprobación final tras " + repeticiones + " intentos:");
        File ultimo = buscarArchivoLibre(archivoInicial).getParentFile().equals(rutaPrincipal)
                ? archivoInicial
                : buscarArchivoLibre(archivoInicial);

        if (ultimo.exists()) {
            System.out.println("Datos del archivo " + ultimo.getName() + ":");
            System.out.println("• ¿Es un fichero? " + ultimo.isFile());
            System.out.println("• Tamaño (en bytes): " + ultimo.length());
        }
    }
}
