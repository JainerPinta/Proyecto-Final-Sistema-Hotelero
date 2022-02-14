/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.daos.EmpleadoDao;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lista.controlador.Lista;
import modelo.Empleado;
import modelo.enums.TipoEmpleado;

/**
 * FXML Controller class
 *
 * @author Jainer Pinta
 */
public class Frm_ReporteEmpleadosController implements Initializable {
    private @FXML ComboBox cbxCargo;
    private EmpleadoController ec = new EmpleadoController();
    private EmpleadoDao ed = new EmpleadoDao();
    
    private @FXML TableView tblEmpleados;
    private @FXML TableColumn<Empleado, String> colID;
    private @FXML TableColumn<Empleado, String> colNombres;
    private @FXML TableColumn<Empleado, String> colApellidos;
    private @FXML TableColumn<Empleado, String> colCedula;
    private @FXML TableColumn<Empleado, String> colTelefono;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCombo();
        cargarTabla();
    }   
    
    /**
     * Genera el reporte de empleados en formato pdf.
     */
    @FXML
    private void generarReporte(){
        String ruta = obtenerRutaGuardado();
        if(ruta != null){
            ec.setEmpleados(ed.listar());
            if(ec.generarReporte(cbxCargo.getSelectionModel().getSelectedItem().toString(), ruta)){
                crearAlerta(Alert.AlertType.INFORMATION, "OK", "Reporte generado correctamente", "El reporte se ha guardado correctamente en la ruta especificada");
            }else{
                crearAlerta(Alert.AlertType.ERROR, "ERROR", "Reporte no generado", "El reporte no se ha podido generar correctmanete");
            }
        }
    }
    
    /**
     * Obtener el directorio de guardado para el reporte a traces de un DirectoryChooser
     * @return Retorna el directorio de guardado.
     */
    private String obtenerRutaGuardado(){
        DirectoryChooser dc = new DirectoryChooser();
        File directorio = dc.showDialog(new Stage());
        return directorio.getAbsolutePath();
    }
    
    /**
     * Carga el ComboBox con los tipos de empleados del Enum TipoEmpleado.
     */
    private void cargarCombo(){
        cbxCargo.getItems().clear();
        cbxCargo.getItems().add("Todo");
        for (int i = 0; i < TipoEmpleado.values().length; i++) {
            cbxCargo.getItems().add(TipoEmpleado.values()[i]);
        }
        cbxCargo.getSelectionModel().select(0);
    }

    /**
     * Crea una alerta al usuario.
     * @param tipo Tipo de alerta.
     * @param titulo Titulo de la ventana alerta
     * @param cabecera Cabecera de la ventana alerta.
     * @param mensaje  Mensaje que se mostrara al usuario.
     */
     private void crearAlerta(Alert.AlertType tipo, String titulo, String cabecera, String mensaje){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecera);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
    /**
     * Carga tabla con empledos segun el cargo seleccionado
     */
    @FXML
    private void cargarTabla(){
        ec.setEmpleados(ed.listar());
        Lista<Empleado> aux = ec.getEmpleados();
        ObservableList<Empleado> listaFX = FXCollections.observableArrayList();
        for (int i = 0; i < aux.sizeLista(); i++) {
            if (cbxCargo.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase(aux.consultarDatoPosicion(i).getRol().getCargo())) {
                listaFX.add(aux.consultarDatoPosicion(i));
            }else if(cbxCargo.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("TODO")){
                listaFX.add(aux.consultarDatoPosicion(i));
            }
        }
        colID.setCellValueFactory(new PropertyValueFactory<Empleado,String>("identificacion"));
        colNombres.setCellValueFactory(new PropertyValueFactory<Empleado,String>("nombres"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<Empleado,String>("apellidos"));
        colCedula.setCellValueFactory(new PropertyValueFactory<Empleado,String>("cedula"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Empleado,String>("telefono"));
        tblEmpleados.getItems().setAll(listaFX);           
    }
}
