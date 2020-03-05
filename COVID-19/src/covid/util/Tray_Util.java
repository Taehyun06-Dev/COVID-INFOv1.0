package covid.util;

import java.net.URISyntaxException;
import java.util.Set;

import covid.main.COVID_DATA;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class Tray_Util {
	
	public void Info_Warining2(Integer amt) {
		if(new COVID_DATA().getAlertStat() == false) {
			return;
		}
		new JFXPanel();
		Platform.setImplicitExit(false);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {        
				TrayNotification tray = new TrayNotification("추가 확진자 발생", "미확인: "+amt+"총 확진자: ["+new COVID_DATA().getDataTotal()+"명(경로 확인) / "+new COVID_DATA().getDataWhole()+"]명", NotificationType.NOTICE);        
			    tray.setAnimationType(AnimationType.FADE);
				tray.showAndDismiss(Duration.seconds(2));		
				if(new COVID_DATA().getSoundStat() == true) {			
					Media media;
					try {
						media = new Media(this.getClass().getResource("/covid/resources/donea.mp3").toURI().toString());
						final MediaPlayer mediaPlayer = new MediaPlayer(media);
					    mediaPlayer.play();
					} catch (URISyntaxException e) {
					}
				}
			}
		});
	}
	
	public void Info_Warining(Set<String> listp) {
		if(new COVID_DATA().getAlertStat() == false) {
			return;
		}
		new JFXPanel();
		Platform.setImplicitExit(false);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {        
				TrayNotification tray = new TrayNotification("추가 확진자 발생", String.join(", ", listp).concat(" 총 확진자: ")+new COVID_DATA().getDataTotal()+"명", NotificationType.NOTICE);        
			    tray.setAnimationType(AnimationType.FADE);
				tray.showAndDismiss(Duration.seconds(2));		
				if(new COVID_DATA().getSoundStat() == true) {			
					Media media;
					try {
						media = new Media(this.getClass().getResource("/covid/resources/donea.mp3").toURI().toString());
						final MediaPlayer mediaPlayer = new MediaPlayer(media);
					    mediaPlayer.play();
					} catch (URISyntaxException e) {
					}
				}
			}
		});
	}

	public void Info_Succes() {
		if(new COVID_DATA().getAlertStat() == false) {
			System.out.println(false);
			return;
		}
		new JFXPanel();
		Platform.setImplicitExit(false);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {    
				TrayNotification tray = new TrayNotification("동선 확인된 추가 확진자 없음", "총 확진자: ["+new COVID_DATA().getDataTotal()+"명(경로 확인) / "+new COVID_DATA().getDataWhole()+"]명", NotificationType.INFORMATION); 
			    tray.setAnimationType(AnimationType.FADE);
				tray.showAndDismiss(Duration.seconds(2));
				if(new COVID_DATA().getSoundStat() == true) {		
					Media media;
					try {
						media = new Media(this.getClass().getResource("/covid/resources/ask.mp3").toURI().toString());
						final MediaPlayer mediaPlayer = new MediaPlayer(media);
					    mediaPlayer.play();
					} catch (URISyntaxException e) {
					}
				}
			}
		});
	}
}
