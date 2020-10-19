package finalproject;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
public class FinalProject extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("OptionB");
       scene.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent dualclick) {
                if(dualclick.getClickCount()==2){
                    stage.setFullScreen(true);
                   
                }
            }
            
        });
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    } 
}
