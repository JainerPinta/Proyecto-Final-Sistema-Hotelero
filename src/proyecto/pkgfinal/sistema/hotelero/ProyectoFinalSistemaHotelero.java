/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.pkgfinal.sistema.hotelero;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jainer Pinta
 */
public class ProyectoFinalSistemaHotelero extends Application{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //probar
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
         try{
            Parent root = FXMLLoader.load(getClass().getResource("/vista/Frm_VentanaLogin.fxml"));
            Scene escena1 = new Scene(root);
            stage.setScene(escena1);
            stage.setTitle("Sistema Hotelero");
            stage.show();
        } catch(IOException e){
            e.printStackTrace();
        }        
    }
    
}
