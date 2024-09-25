import java.io.*;
import java.util.Random;
public class GenerateInfoFiles {
public void createSalesManInfoFile(int salesmanCount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("vendedores.txt"))) {
            for (int i = 1; i <= salesmanCount; i++) {
                writer.write("CC;" + i + ";Nombre_" + i + ";Apellido_" + i);
                writer.newLine();
            }
            System.out.println("Archivo de información de vendedores generado.");
        } catch (IOException e) {
            System.err.println("Error al generar archivo: " + e.getMessage());
        }
    }

    //Método para generar archivo de productos
    public void createProductsFile(int productsCount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("productos.txt"))) {
            Random random = new Random();
            for (int i = 1; i <= productsCount; i++) {
                writer.write(i + ";Producto_" + i + ";" + (random.nextDouble() * 100));
                writer.newLine();
            }
            System.out.println("Archivo de productos generado.");
        } catch (IOException e) {
            System.err.println("Error al generar archivo: " + e.getMessage());
        }
    }

    // Método para generar archivo de ventas de un vendedor
    public void createSalesMenFile(int randomSalesCount, String name, long id) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(name + "_ventas.txt"))) {
            Random random = new Random();
            writer.write("CC;" + id);
            writer.newLine();
            for (int i = 0; i < randomSalesCount; i++) {
                int productId = random.nextInt(100) + 1;  // ID de producto entre 1 y 100
                int quantity = random.nextInt(10) + 1;    // Cantidad entre 1 y 10
                writer.write(productId + ";" + quantity);
                writer.newLine();
            }
            System.out.println("Archivo de ventas de " + name + " generado.");
        } catch (IOException e) {
            System.err.println("Error al generar archivo: " + e.getMessage());
        }
    }

    // Método para leer vendedores del archivo y generar ventas para cada uno
    public void generateSalesForAllSalesmen(int randomSalesCount) {
        try (BufferedReader reader = new BufferedReader(new FileReader("vendedores.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");
                long id = Long.parseLong(fields[1]);  // El segundo campo es la ID
                String name = fields[2];              // El tercer campo es el nombre
                
                createSalesMenFile(randomSalesCount, name, id);  // Genera archivo de ventas
            }
            System.out.println("Archivos de ventas generados para todos los vendedores.");
        } catch (IOException e) {
            System.err.println("Error al leer archivo de vendedores: " + e.getMessage());
        }
    }

    // Método principal para ejecutar el programa
    public static void main(String[] args) {
        GenerateInfoFiles generador = new GenerateInfoFiles();
        
        // Generar archivos de ejemplo
        generador.createSalesManInfoFile(10);        // Genera archivo con 10 vendedores
        generador.createProductsFile(50);            // Genera archivo con 50 productos
        generador.generateSalesForAllSalesmen(5);    // Genera 5 ventas aleatorias para cada vendedor
    }
}
