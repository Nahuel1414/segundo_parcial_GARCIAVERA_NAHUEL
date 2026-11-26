package controllers;

import archivos.GestorBinario;
import examen.Carrito;
import examen.Producto;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewController implements Initializable {
    
    @FXML
    private TextField txtCant;
    
    @FXML
    private ListView listCarrito;
    
    @FXML
    private Button btnAgregar;
    
    @FXML
    private Button btnConfirmar;
    
    @FXML
    private Label lblCant;
    
    @FXML
    private Label lblCarrito;
    
    @FXML
    private Label lblMensaje;
    
    @FXML
    private TableView<Producto> tabla;
    
    @FXML
    private TableColumn<Producto, String> cNombre;
    
    @FXML
    private TableColumn<Producto, Double> cPrecio;
    
    @FXML
    private TableColumn<Producto, Integer> cStock;
    
    @FXML
    private List<Producto> productos = new ArrayList();
    
    @FXML
    private List<Carrito> carrito = new ArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productos = GestorBinario.deserializarProducto("productos.dat");
        
        cNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        cPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        cStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        
        actualizarTabla();
    }    
    
    private void actualizarTabla() {
        tabla.getItems().clear();
        for(Producto p : productos) {
            tabla.getItems().add(p);
        }
    }
    
    public void agregar() {
        String txtCant = this.txtCant.getText();
        Producto seleccionado = tabla.getSelectionModel().getSelectedItem();
        
        if (seleccionado == null) {
            mostrarMensaje("Seleccione un producto");
        }
        
        int cantidad;
        try {
            cantidad = Integer.parseInt(txtCant);
        } catch(NumberFormatException e) {
            mostrarMensaje("La cantidad debe ser un numero.");
            return;
        }
        
        if(cantidad <= 0){
            mostrarMensaje("La cantidad debe ser mayor a 0");
        }
        
        if(cantidad > seleccionado.getStock()) {
            mostrarMensaje("Stock insuficiente");
        }
        
        seleccionado.setStock(seleccionado.getStock() - cantidad);
        
        carrito.add(new Carrito(cantidad, seleccionado));
        
        actualizarTabla();
    }
    
    private void mostrarMensaje(String mensaje){
        lblMensaje.setText(mensaje);
    }
    
    public void confirmar() throws IOException {
        if (carrito.isEmpty()) {
            mostrarMensaje("Carrito vacio");
            return;
        }
        
        double total = 0;
        for (Carrito c : carrito) {
            total += c.getSubtotal();
        }
        
        GestorBinario.serializarProducto(productos, "productos.dat");
        GestorBinario.generarTicket(carrito, total, "archivo.txt");
        carrito.clear();
        
        actualizarTabla();
        actualizarCarrito();
    }
    
    private void actualizarCarrito() {
        listCarrito.getItems().clear();
        listCarrito.getItems().addAll(carrito);
    }
}
