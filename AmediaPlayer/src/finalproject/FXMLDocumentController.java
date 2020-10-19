package finalproject;

import java.io.File;
import java.net.URL;
import javafx.util.Duration;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

public class FXMLDocumentController implements Initializable {
    @FXML
    private Label label;
    @FXML
    private Slider slider;
    @FXML
    public Slider seekSlider;
    @FXML
    private double rate;
    @FXML
    private String filepath;
    @FXML
    private String recentpath;
    @FXML
    private ListView<String> recent;
    ObservableList<String> rclist = FXCollections.observableArrayList();
    @FXML
    private MediaView mv;
    @FXML
    public MediaPlayer mp;
    @FXML
    private Media me;
    @FXML 
    private Button openfile;
    @FXML
    private boolean isplaying;
    int playing=0;
    @FXML
    String rc;
    @FXML
    File file;
    @FXML
    File recentfile;
    
    public void openfile(ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("All files", "*.*"),
        new FileChooser.ExtensionFilter("mp4", "*.mp4"),
        new FileChooser.ExtensionFilter("mp3", "*.mp3"),
        new FileChooser.ExtensionFilter("wav", "*.wav"));
         file = fc.showOpenDialog(null);
        System.out.println(file);
        filepath = file.toURI().toString();
        playstart(filepath);
        recent.getItems().add(file.getPath());
    }
    public void playstart(String pathname){
        if(playing!=0)
            mp.stop();
        if(pathname != null){
            me = new Media(pathname);
            mp = new MediaPlayer(me);
            mv.setMediaPlayer(mp);
            mp.play();
            playing++;
             isplaying= true;   
           slider.setValue(mp.getVolume()*100);
           slider.valueProperty().addListener((Observable observable) -> {
               mp.setVolume(slider.getValue()/100);
           });
           mp.currentTimeProperty().addListener((ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) -> {
               seekSlider.setValue(newValue.toSeconds());
           });
           seekSlider.setOnMouseClicked((MouseEvent event1) -> {
               mp.seek(Duration.seconds(seekSlider.getValue()));
            });  
        }
        else
        {
            System.out.println("File not found");
        } 
    }
    public void rcplay(ActionEvent event){
         rc=recent.getSelectionModel().getSelectedItem();
         File filepat = new File(rc);
         recentpath = filepat.toURI().toString();
         recent.getItems().add(filepat.getPath());
         playstart(recentpath);
        }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
     @FXML
    public void play(ActionEvent event){
        mp.play();
        isplaying= true;        
    }
     @FXML
    public void pause(ActionEvent event){
        mp.pause();
        mp.setRate(1);
    }
    @FXML
    public void fast(ActionEvent event){
        if(rate<1)
            rate=1;
        rate= rate + 0.20;
        mp.setRate(rate);
    }@FXML
    public void normal(ActionEvent event){
        mp.setRate(1);
    }@FXML
    public void slow(ActionEvent event){
        if(rate>1)
            rate=1;
        rate= rate - 0.20;
        mp.setRate(rate);
    }  
}
 