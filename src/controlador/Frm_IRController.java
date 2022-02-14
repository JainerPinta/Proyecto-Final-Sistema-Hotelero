/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.daos.ReservaDao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lista.controlador.Lista;
import modelo.Reservacion;

/**
 * FXML Controller class
 *
 * @author pablo
 */
public class Frm_IRController implements Initializable {

    private ReservaDao rd = new ReservaDao();
    private Frm_IngresarClienteController iccontroller;
   
    private @FXML TextField txtCliente;
    private @FXML TextField txtHabitacion;
    private @FXML TextField txtCosto;
    
    private @FXML TableColumn<?, ?> colCliente;
    private @FXML TableColumn<?, ?> colHabitacion;
    private @FXML TableColumn<?, ?> colFechaReserva;
    private @FXML TableColumn<?, ?> colCosto;
    private @FXML TableColumn<?, ?> colFechaEntrada;
    private @FXML TableColumn<?, ?> colFechaSalida;
    private @FXML TableView<Reservacion> tblReservas;
    private @FXML TableColumn<?, ?> colIdReserva;
    
    private @FXML Button btnClente;
    private @FXML Button btnHabitacion;
    private @FXML Button btnguardar;
    private @FXML Button btnEliminar;
    private @FXML Button btnModificar;
    private @FXML Button btnRegresar;
    
    private @FXML DatePicker jdFechaReserva;
    private @FXML DatePicker jdfechaEntrada;
    private @FXML DatePicker jdFechaSalida;
    private ComboBox<?> cbxservicios;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTabla();
        limpiarDatos();
    }
    
    private void cargarTabla() {
        Lista<Reservacion> lista = rd.listar();
        ObservableList<Reservacion> listaFX = FXCollections.observableArrayList();
        for (int i = 0; i < lista.sizeLista(); i++) {
            listaFX.add(lista.consultarDatoPosicion(i));
        }
        ingresarElementosTabla(listaFX);
    }

    private void ingresarElementosTabla(ObservableList<Reservacion> listaFX) {
        colIdReserva.setCellValueFactory(new PropertyValueFactory("id_reservacion"));
        colCliente.setCellValueFactory(new PropertyValueFactory("cliente"));
        colHabitacion.setCellValueFactory(new PropertyValueFactory("habitacion"));
        colFechaReserva.setCellValueFactory(new PropertyValueFactory("fecha"));
        colFechaEntrada.setCellValueFactory(new PropertyValueFactory("fecha_entrada"));
        colFechaSalida.setCellValueFactory(new PropertyValueFactory("fecha_salida"));
        colCosto.setCellValueFactory(new PropertyValueFactory("costoTotal"));
        tblReservas.setItems(listaFX);
    }

    @FXML
    private void guardarReserva(ActionEvent event) {
        if (verificarCampos()) {
            rd.getReserva().setHabitacion(txtHabitacion.getText());
            rd.getReserva().setCliente(iccontroller.clienteDao.listar().consultarDatoPosicion(iccontroller.select).getNombres() + " " +iccontroller.clienteDao.listar().consultarDatoPosicion(iccontroller.select).getApellidos());
            rd.getReserva().setFecha(jdFechaReserva.getValue());
            rd.getReserva().setFecha_entrada(jdfechaEntrada.getValue());
            rd.getReserva().setFecha_salida(jdFechaSalida.getValue());
            rd.getReserva().setCostoTotal(Double.parseDouble(txtCosto.getText()));
            if (rd.guardar()) {
                crearAlerta(Alert.AlertType.INFORMATION, "OK", "Datos guardados", "Los datos se han almacenado correctamente");
                limpiarDatos();
                cargarTabla();
            } else {
                crearAlerta(Alert.AlertType.ERROR, "Error", "Datos no guardados", "Ha surgido un error al guardar los datos");
            }

        } else {
            crearAlerta(Alert.AlertType.INFORMATION, "Error", "Campos vacios", "Rellene todos los campos para guardar al empleado");
        }
    }
    
    private boolean verificarCampos() {
        if (txtCliente.getText().trim().isEmpty() || txtHabitacion.getText().trim().isBlank()
                || txtCosto.getText().trim().isEmpty() || jdFechaReserva.getValue().toString().equals("")
                || jdFechaSalida.getValue().toString().equals("") || jdfechaEntrada.getValue().toString().equals("")) {
            return false;
        }
        return true;
    }
    
      private void limpiarDatos() {
          rd.setReserva(null);
          txtCliente.setText("");
          txtCosto.setText("");
          txtHabitacion.setText("");
          btnguardar.setDisable(false);
          btnEliminar.setDisable(true);
          btnModificar.setDisable(true);
    }

    @FXML
    private void seleccionarCliente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Frm_IngresarCliente.fxml"));
            Parent root = loader.load();
            Frm_IngresarClienteController contro = loader.getController();
            this.iccontroller = contro;
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            contro.btnseleccionar.setVisible(true);
            stage.showAndWait();
            this.txtCliente.setText(contro.clienteDao.listar().consultarDatoPosicion(contro.select).getApellidos() + " " + contro.clienteDao.listar().consultarDatoPosicion(contro.select).getNombres());
        } catch (IOException ex) {
            Logger.getLogger(Frm_IRController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cargarVentana(String direccion) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(direccion));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(Frm_IRController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void seleccionarHabitacion(ActionEvent event) {
        
    }
    
    private void crearAlerta(Alert.AlertType tipo, String titulo, String cabecera, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecera);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    private void eliminar(ActionEvent event) {
        if (tblReservas.getSelectionModel().getSelectedIndex() != -1) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Elminar");
            alerta.setHeaderText("Esta seguro de eliminar la reserva");
            alerta.setContentText("Los datos de la reserva se borraran despues de confirmar");
            if (alerta.showAndWait().get() == ButtonType.OK) {
                Reservacion reserva = (Reservacion) tblReservas.getSelectionModel().getSelectedItem();
                if (rd.eliminar(reserva.getId_reservacion().intValue())) {
                    crearAlerta(Alert.AlertType.INFORMATION, "Informacion", "Reserva eliminada", "La reserva se ha eliminado correctamente");
                    limpiarDatos();
                    cargarTabla();
                } else {
                    crearAlerta(Alert.AlertType.ERROR, "Error", "Reserva no eliminada", "El empleado no se ha podido eliminar");
                }
            }
        }
    }

    @FXML
    private void modificar(ActionEvent event) {
        if (verificarCampos()) {
                rd.getReserva().setCliente(txtCliente.getText());
                rd.getReserva().setHabitacion(txtHabitacion.getText());
                rd.getReserva().setFecha(jdFechaReserva.getValue());
                rd.getReserva().setFecha_entrada(jdfechaEntrada.getValue());
                rd.getReserva().setFecha_salida(jdFechaSalida.getValue());
                rd.getReserva().setCostoTotal(Double.parseDouble(txtCosto.getText()));
                System.out.println(rd.getReserva().getId_reservacion());
                if (rd.modificar()) {
                    crearAlerta(Alert.AlertType.INFORMATION, "OK", "Datos actualizados", "Los datos se han actualizado correctamente");
                    limpiarDatos();
                    cargarTabla();
                }else{
                    crearAlerta(Alert.AlertType.ERROR, "Error", "Datos no actualizados", "Ha surgido un error al actualizar los datos");
                }

        }else{
            crearAlerta(Alert.AlertType.INFORMATION, "Error", "Campos vacios", "Rellene todos los campos para actualizar al empleado");
        }
    }
    

    @FXML
    private void generarPDF(ActionEvent event) {
        
    }

    @FXML
    private void Regresar(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void seleccionarReserva(MouseEvent event) {
        cargrReserva();
    }

    private void cargrReserva() {
        Reservacion r = tblReservas.getSelectionModel().getSelectedItem();
        rd.getReserva().setId_reservacion(r.getId_reservacion());
        txtCliente.setText(r.getCliente());
        txtHabitacion.setText(r.getHabitacion());
        jdFechaReserva.setValue(r.getFecha());
        jdfechaEntrada.setValue(r.getFecha_entrada());
        jdFechaSalida.setValue(r.getFecha_salida());
        txtCosto.setText(String.valueOf(r.getCostoTotal()));
        btnEliminar.setDisable(false);
        btnModificar.setDisable(false);
        btnguardar.setDisable(true);
    }

    @FXML
    private void cancelar(ActionEvent event) {
        limpiarDatos();
    }
}
