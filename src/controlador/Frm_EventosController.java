/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Controlador.ConexionDB;
import controlador.daos.ClienteServiciosDao;
import controlador.daos.EventoDao;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lista.controlador.Lista;
import modelo.Evento;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class Frm_EventosController implements Initializable {

    @FXML
    private ComboBox<String> cbTipo;
    @FXML
    private ComboBox<String> cbJornada;
    @FXML
    private DatePicker txtFecha;
    @FXML
    private TableView<Evento> TablaEve;
    @FXML
    private TableColumn<?, ?> colID;
    @FXML
    private TableColumn<?, ?> colTipoEvento;
    @FXML
    private TableColumn<?, ?> colPrecio;
    @FXML
    private TableColumn<?, ?> colJornada;
    @FXML
    private TableColumn<?, ?> colFecha;
    @FXML
    private TableColumn<?, ?> colDuracion;
    @FXML
    private TextField txPrecio;
    @FXML
    private TextField txcliente;
    @FXML
    private Button btnselect;
    @FXML
    private Button btIngresar;
    @FXML
    private ComboBox<?> cbInicio;
    @FXML
    private ComboBox<?> cbFin;

    /**
     * Initializes the controller class.
     */
    
    private EventoDao eventodao = new EventoDao();
    private ClienteServiciosDao csdao = new ClienteServiciosDao();
    private ConexionDB conexionDB = new ConexionDB();
    private Connection conexion = conexionDB.conectar();
    @FXML
    private TableColumn<?, ?> colCliente;
    @FXML
    private ImageView hola;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txcliente.setEditable(false);
        ObservableList listacbx = FXCollections.observableArrayList("Celebracion", "Conferencia", "Evento Cultural", "Reunión");
        cbTipo.setItems(listacbx);
        listacbx = FXCollections.observableArrayList("Matutina", "Vespertina", "Nocturna");
        cbJornada.setItems(listacbx);
        validarFecha();
        limpiar();
    }

    /**
     * Se agrega las horas a los combo box segun sea la jornada
     * @param event 
     */
    @FXML
    private void ActionJornada(ActionEvent event) {
        cargarCombos();
    }
    
    /**
     * Se agrega las horas a los combo box segun sea la jornada
     */
    private void cargarCombos() {
        ObservableList listacbx;
        int select = cbJornada.getSelectionModel().getSelectedIndex();
        switch (select) {
            case 0:
                cbInicio.getItems().clear();
                cbFin.getItems().clear();
                listacbx = FXCollections.observableArrayList("8am", "9am", "10am", "11am", "12am");
                cbInicio.setItems(listacbx);
                cbFin.setItems(listacbx);
                break;
            case 1:
                cbInicio.getItems().clear();
                cbFin.getItems().clear();
                listacbx = FXCollections.observableArrayList("1pm", "2pm", "3pm", "4pm", "5pm", "6pm", "7pm");
                cbInicio.setItems(listacbx);
                cbFin.setItems(listacbx);
                break;
            case 2:
                cbInicio.getItems().clear();
                cbFin.getItems().clear();
                listacbx = FXCollections.observableArrayList("8pm", "9pm", "10pm", "11pm", "12pm", "1am", "2am");
                cbInicio.setItems(listacbx);
                cbFin.setItems(listacbx);
                break;
        }
    }
    
    /**
     * Se valida que el Usuario no pueda ingresar una fecha anteior a la actual
     */
    private void validarFecha() {
        txtFecha.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0);
            }
        });
    }
    
    /**
     * Se valida que todos los campos de Ingreso no esten vacios
     * @return False si los campos de Ingreso estan vacios, True si los campos
     * estan ingresados
     */
    private boolean validar() {
        return txPrecio.getText().trim().length() > 0 && txcliente.getText().trim().length() > 0 && !cbJornada.getSelectionModel().isEmpty() && !cbFin.getSelectionModel().isEmpty() && !cbInicio.getSelectionModel().isEmpty() && !cbTipo.getSelectionModel().isEmpty()
                && txtFecha.getValue() != null;
    }
    
    /**
     * Setea un nulo a la clase Evento y limpia los TextFields
     */
    private void limpiar() {
        eventodao.setEvento(null);
        cargarTabla();
        txcliente.setText("");
        txPrecio.setText("");
        txtFecha.setValue(LocalDate.now());
    }
    
    /**
     * Cargara la Tabla trayendo los datos de la base de datos en el metodo listar
     */
    private void cargarTabla() {
        Lista<Evento> lista = new Lista<>();
        lista = eventodao.listar();
        ObservableList<Evento> listaFX = FXCollections.observableArrayList();
        for (int i = 0; i < lista.sizeLista(); i++) {
            listaFX.add(lista.consultarDatoPosicion(i));
        }
        colID.setCellValueFactory(new PropertyValueFactory("IdServicio"));
        colTipoEvento.setCellValueFactory(new PropertyValueFactory("nombreServicio"));
        colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        colJornada.setCellValueFactory(new PropertyValueFactory("Jordana"));
        colFecha.setCellValueFactory(new PropertyValueFactory("fecha"));
        colDuracion.setCellValueFactory(new PropertyValueFactory("duracion"));
        colCliente.setCellValueFactory(new PropertyValueFactory("cliente"));
        TablaEve.setItems(listaFX);
    }
    
    /**
     * Seteara los datos de la clase Evento y los guardara asi como el Asginar Servicio
     */
    private void Ingresar() {
        if (verificarFechaEventos()) {
        } else {
            if (validar()) {
                eventodao.getEvento().setCliente(txcliente.getText());
                eventodao.getEvento().setNombreServicio(cbTipo.getSelectionModel().getSelectedItem());
                eventodao.getEvento().setJordana(cbJornada.getSelectionModel().getSelectedItem());
                eventodao.getEvento().setPrecio(Double.parseDouble(txPrecio.getText()));
                eventodao.getEvento().setFecha(txtFecha.getValue().toString());
                if (generarDuracion().equals(1)) {
                    eventodao.getEvento().setDuracion(generarDuracion() + " hora");
                } else {
                    eventodao.getEvento().setDuracion(generarDuracion() + " horas");
                }
                if (eventodao.guardar() && ingresarServiciosClientes()) {
                    crearAlerta(Alert.AlertType.INFORMATION, "OK", "Datos Guardados", "Los datos han sido guardados");
                    limpiar();
                } else {
                    crearAlerta(Alert.AlertType.ERROR, "Error", "Datos no guardados", "Ha surgido un error al guardar los datos");
                }
            } else {
                crearAlerta(Alert.AlertType.ERROR, "Error", "Vacio", "Campos Vacios");
            }
        }
    }
    
    /**
     * Cargara la Ventana de Clientes para traer el nombre del mismo
     */
    public void seleccionarCliente() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Frm_IngresarCliente.fxml"));
            loader.setLocation(getClass().getResource("/vista/Frm_IngresarCliente.fxml"));
            Parent root = loader.load();
            Frm_IngresarClienteController contro = loader.getController();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            contro.btnseleccionar.setVisible(true);
            stage.showAndWait();
            this.txcliente.setText(contro.test);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Creara una alerta segun sea el caso
     * @param tipo
     * @param titulo
     * @param cabecera
     * @param mensaje 
     */
    private void crearAlerta(Alert.AlertType tipo, String titulo, String cabecera, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecera);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
    /**
     * Restara la hora fin e hora inicio para calcular la duración
     * @return duración
     */
    private String generarDuracion() {
        int ini = 0, fin = 0, re = 0;
        if (cbJornada.getSelectionModel().getSelectedIndex() == 0 || cbJornada.getSelectionModel().getSelectedIndex() == 2) {
            ini = cbInicio.getSelectionModel().getSelectedIndex() + 8;
            fin = cbFin.getSelectionModel().getSelectedIndex() + 8;
        } else {
            ini = cbInicio.getSelectionModel().getSelectedIndex() + 1;
            fin = cbFin.getSelectionModel().getSelectedIndex() + 1;
        }
        re = fin - ini;
        return String.valueOf(re);
    }
    
    /**
     * Traera los datos de la base de datos gracias a dos sentecnias para verificar si las fechas se repiten
     * @return true si las fechas se repiten y  false si no se repiten
     */
    private Boolean verificarFechaEventos() {
        try {
            String sql = "select eventos.Fecha,eventos.Jornada,eventos.Cliente from eventos";
            Statement st = conexion.createStatement();
            ResultSet rt = st.executeQuery(sql);
            while (rt.next()) {
                if (cbJornada.getSelectionModel().getSelectedItem().equals(rt.getString("Jornada")) && txtFecha.getValue().toString().equals(rt.getString("Fecha"))) {
                    crearAlerta(Alert.AlertType.INFORMATION, "ERROR", "OCUPADO", "Fechas iguales: El cliente o clienta: " + rt.getString("Cliente") + " ya tiene un evento en esta fecha");
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Ingresara el eventoIngresao en AsignarServiciosCLiente Automaticamente
     * @return true si se ha guardado correctamente y false si no 
     */
    private Boolean ingresarServiciosClientes() {
        csdao.getServicios().setCliente(txcliente.getText());
        csdao.getServicios().setNombreServicio("Evento de: "+ cbTipo.getSelectionModel().getSelectedItem());
        csdao.getServicios().setUso(Integer.parseInt(generarDuracion()));
        csdao.getServicios().setPrecio(Double.parseDouble(txPrecio.getText()));
        if(csdao.guardar()){
            return true;
        }
        return false;
    }
    


    @FXML
    private void actionSelect(ActionEvent event) {
        seleccionarCliente();
    }

    @FXML
    private void actionIngresar(ActionEvent event) {
        Ingresar();
    }

    /**
     * Verificara que que se coloque de manera correcta las horas
     * @param event 
     */
    @FXML
    private void actioini(ActionEvent event) {
        int ini = cbInicio.getSelectionModel().getSelectedIndex();
        int fin = cbFin.getSelectionModel().getSelectedIndex();
        if (fin <= ini && ini != -1 && fin != -1) {
            crearAlerta(Alert.AlertType.ERROR, "ERROR", "ERROR", "No se puede insertar en estas horas");
            cbInicio.getSelectionModel().select(0);
            cbFin.getSelectionModel().select(1);
        }
    }

    /**
     * Verificara que que se coloque de manera correcta las horas
     * @param event 
     */
    @FXML
    private void actionfin(ActionEvent event) {
        int ini = cbInicio.getSelectionModel().getSelectedIndex();
        int fin = cbFin.getSelectionModel().getSelectedIndex();
        if (fin <= ini && ini != -1 && fin != -1) {
            crearAlerta(Alert.AlertType.ERROR, "ERROR", "ERROR", "No se puede insertar en estas horas");
            cbInicio.getSelectionModel().select(0);
            cbFin.getSelectionModel().select(1);
        }
    }

}
