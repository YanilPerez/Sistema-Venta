package pe.edu.upeu.sysalmacenfx.control;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import pe.edu.upeu.sysalmacenfx.dto.ComboBoxOption;
import pe.edu.upeu.sysalmacenfx.modelo.Usuario;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class GenerateSaleViewController {

    // Campos FXML para los controles de la UI
    @FXML
    private TextField serial, codProduct, price, customerName, productName, stock, seller, total, date, codCustomer;

    @FXML
    private Spinner<Integer> quantity;

    @FXML
    private TableView<Usuario> tableSale;

    @FXML
    private TableColumn<Usuario, String> colNro, colCod, colProduct, colQuantity, colPrice, colTotal;

    // Aquí se debería cargar la lista de usuarios (o productos) para la tabla
    private List<Usuario> listarUsuario; // Asegúrate de que esta lista se inicialice con datos.

    private String filtro = "";  // Variable de filtro para la búsqueda

    // Método para buscar al cliente por DNI
    public void searchCustomer(ActionEvent actionEvent) {
        String dniCliente = codCustomer.getText();
        if (!dniCliente.isEmpty()) {
            // Aquí deberías buscar el cliente por su DNI, por ejemplo, desde la base de datos
            // Para efectos del ejemplo, vamos a simular que encontramos un cliente
            Usuario cliente = new Usuario();  // Simula la búsqueda de un cliente
            cliente.setNombre("Juan");
            cliente.setApellido("Perez");
            customerName.setText(cliente.getNombre() + " " + cliente.getApellido());
        } else {
            // Si no hay DNI, mostrar un mensaje
            showAlert(Alert.AlertType.ERROR, "Error", "Por favor ingrese el DNI del cliente.");
        }
    }

    // Método para buscar un producto por código
    public void searchProduct(ActionEvent actionEvent) {
        String codProd = codProduct.getText();
        if (!codProd.isEmpty()) {
            // Aquí deberías buscar el producto por su código, por ejemplo, desde la base de datos
            // Para efectos del ejemplo, vamos a simular que encontramos un producto
            productName.setText("Producto Ejemplo");
            stock.setText("50");
            price.setText("100");
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Por favor ingrese el código del producto.");
        }
    }

    // Método para agregar el producto al carrito de compras (tabla)
    public void addShoppingCart(ActionEvent actionEvent) {
        String product = productName.getText();
        int cantidad = quantity.getValue();
        double precio = Double.parseDouble(price.getText());
        double totalProducto = cantidad * precio;

        // Aquí deberías agregar la venta del producto al carrito (tabla)
        // Supongamos que agregar un producto con el siguiente formato
        //tableSale.getItems().add(new Usuario(product, cantidad, precio, totalProducto));  // Esto es solo un ejemplo.

        // Actualizar el total
        double totalVenta = tableSale.getItems().stream()
                .mapToDouble(Usuario::getTotal)  // Asegúrate de tener un método getTotal en Usuario
                .sum();
        total.setText(String.format("%.2f", totalVenta));
    }

    // Método para cancelar la venta
    public void cancel(ActionEvent actionEvent) {
        // Limpiar todos los campos y la tabla
        serial.clear();
        codProduct.clear();
        customerName.clear();
        productName.clear();
        stock.clear();
        price.clear();
        seller.clear();
        total.clear();
        tableSale.getItems().clear();
    }

    // Método para generar la venta
    public void generateSale(ActionEvent actionEvent) {
        // Aquí deberías agregar la lógica para generar la venta (guardar en base de datos, etc.)
        showAlert(Alert.AlertType.INFORMATION, "Venta generada", "La venta se ha realizado con éxito.");
    }

    // Método para mostrar alertas
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Método para filtrar usuarios (clientes)
    public void searchCustomerFilter(ActionEvent actionEvent) {
        if (filtro == null || filtro.isEmpty()) {
            tableSale.getItems().clear();
            tableSale.getItems().addAll(listarUsuario);
        } else {
            String lowerCaseFilter = filtro.toLowerCase();
            List<Usuario> usuariosFiltrados = listarUsuario.stream()
                    .filter(usuario -> usuario.getNombre().toLowerCase().contains(lowerCaseFilter)
                            || usuario.getApellido().toLowerCase().contains(lowerCaseFilter)
                            || usuario.getUser().toLowerCase().contains(lowerCaseFilter)
                            || String.valueOf(usuario.getDni()).contains(lowerCaseFilter)
                            || String.valueOf(usuario.getTelf()).contains(lowerCaseFilter))
                    .collect(Collectors.toList());

            tableSale.getItems().clear();
            tableSale.getItems().addAll(usuariosFiltrados);
        }
    }
}

