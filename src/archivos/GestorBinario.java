package archivos;

import examen.Carrito;
import examen.Producto;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class GestorBinario {
     public static void serializarProducto(List<Producto> lista, String path){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
            
            out.writeObject(lista);
            
            System.out.println("Se serializo correctamente");
        } catch(Exception e) {
            
        }
    }
    
    public static List<Producto> deserializarProducto(String path) {
        List<Producto> lista = new ArrayList<>();
        File archivo = new File(path);

        if (!archivo.exists()) {
            return lista;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
            lista = (List<Producto>) in.readObject();
            System.out.println("Se deserializo correctamente");
        } catch (Exception e) {
            System.out.println("Error al deserializar: " + e.getMessage());
        }

        return lista;
    }
    
    public static void generarTicket(List<Carrito> lista, double total, String path) throws IOException{

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {

        for (Carrito carrito : lista) {
            String linea = carrito.getProducto().getNombre() + carrito.getCantidad() + carrito.getSubtotal();
            bw.write(linea);
            bw.newLine();
        }
        bw.write((int) total);
        bw.newLine();
        }
        System.out.println("Lista actualizada correctamente");
    }
}
