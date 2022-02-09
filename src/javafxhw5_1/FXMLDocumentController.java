/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxhw5_1;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static sun.audio.AudioPlayer.player;

/**
 *
 * @author Carlos Garcia
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private MediaView view;
   
    @FXML
    private Button play;
    
    @FXML
    private Button pause;
    
    @FXML
    private Button stop;
    
    @FXML
    private Button mute;
    
    @FXML
    private Button open;
    
    String startVideo = "SampleVideo2.mp4";
    
    Media media;
    MediaPlayer player;
    
    public void displayVideo(String fileText){
        File videoFile = new File(fileText);
        media = new Media(videoFile.toURI().toString());
        player = new MediaPlayer(media);
        
        player.setOnEndOfMedia(() ->
        {
            player.stop();
        });
        
        //player.setAutoPlay(true);
        
        play.setOnAction(event ->
        {
            player.play();
        });
        
        pause.setOnAction(event ->
        {
            player.pause();
        });
        
        stop.setOnAction(event ->
        {
            player.stop();
        });
        
        mute.setOnAction(event ->
        {
            if(player.isMute() == true)
            {
                player.setMute(false);
                mute.setText("Mute");
            }
            else{
                player.setMute(true);
                mute.setText("UnMute");
            }
        });
        
        open.setOnAction(event ->
        {
            FileChooser fileChooser = new FileChooser();
            File selectedVideo = fileChooser.showOpenDialog(null);
            String path = selectedVideo.toURI().toString();
            if(selectedVideo != null)
            {
                try{
                    
                    media = new Media(path);
                    player.stop();
                    if(player.isMute() == true)
                    {
                        player.setMute(false);
                        mute.setText("Mute");
                    }
                    player = new MediaPlayer(media);
                    view.setMediaPlayer(player);
                    view.setFitWidth(640);
                    view.setFitHeight(480);
                }
                catch(Exception e){
                }
            }
            
        });
        view.setMediaPlayer(player);
        
    }
    //Media media = new Media(videoFile.toURI().toString());
    //MediaPlayer player = new MediaPlayer(media);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayVideo(startVideo);
    }    

    
}
