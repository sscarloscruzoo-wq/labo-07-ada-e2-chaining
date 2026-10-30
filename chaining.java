package ejercicio2_chaining_csv;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class Ejercicio2ChainingCSV {

    public static void main(String[] args) throws Exception {
        // Inicializamos la tabla hash con una capacidad m = 7
        TablaHashChaining tabla = new TablaHashChaining(7);
        String codigoPrueba = null;

        // 1. Leer el archivo CSV de caso 2
        try (BufferedReader br = Files.newBufferedReader(
                Path.of("data/caso2_colisiones_chaining.csv"), StandardCharsets.UTF_8)) {

            br.readLine(); // Omitir cabecera
            String linea;
            int fila = 0;

            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(",", 3);

                // TODO 1 & 2: Crear el registro y guardarlo en la tabla mediante encadenamiento
                RegistroURL u = new RegistroURL(Integer.parseInt(p[0]), p[1], p[2]);
                tabla.insertar(u);

                // Guardamos un código de prueba (por ejemplo, el de la fila 15) para la búsqueda posterior
                if (++fila == 15) {
                    codigoPrueba = u.codigo();
                }
            }
        }

        // TODO 3: Imprimir la estructura de cadenas de la tabla hash
        System.out.println("=== 1. ESTRUCTURA DE LA TABLA HASH (CHAINING) ===");
        tabla.imprimir();

        // TODO 4: Mostrar estadísticas (Colisiones, Cadena Máxima y Factor de Carga)
        System.out.println("\n=== 2. ESTADÍSTICAS DEL SISTEMA ===");
        System.out.println("Total de colisiones: " + tabla.getColisiones());
        System.out.println("Longitud máxima de cadena: " + tabla.cadenaMaxima());
        System.out.println("Factor de carga (alpha = n/m): " + tabla.factorCarga());

        // TODO 5: Probar el método buscar() con un código existente y mostrar comparaciones
        System.out.println("\n=== 3. PRUEBA DE BÚSQUEDA ===");
        if (codigoPrueba != null) {
            TablaHashChaining.Resultado resultado = tabla.buscar(codigoPrueba);
            System.out.println("Código buscado: " + codigoPrueba);
            System.out.println("Bucket asignado: " + resultado.bucket());
            System.out.println("Número de comparaciones realizadas: " + resultado.comparaciones());
            if (resultado.registro() != null) {
                System.out.println("URL original encontrada: " + resultado.registro().urlOriginal());
            }
        }
    }
}
