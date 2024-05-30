package PlayerLogica;
import javafx.util.Duration;
import java.io.File;
import PlayerLogica.Musica;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MediaTocar implements Tocador { 
    private  static MediaPlayer mediaPlayer;
    private PlayList playlist = new PlayList();
    private String musica;
    private Duration currentPosition;
    
    
 
    public  static MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}
    public void ajustarTempo(double tempo) {
        // Realiza o casting de double para Duration
        Duration duration = new Duration(tempo);

        mediaPlayer.seek(duration);
        mediaPlayer.play();
    }
	public static  void setMediaPlayer(MediaPlayer mediaPlayer) {
		mediaPlayer = mediaPlayer;
	}

	public PlayList getPlaylist() {
		return playlist;
	}

	public void setPlaylist(PlayList playlist) {
		this.playlist = playlist;
	}

	public String getMusica() {
		return musica;
	}

	public void setMusica(String musica) {
		this.musica = musica;
	}

	public Duration getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Duration currentPosition) {
		this.currentPosition = currentPosition;
	}

	public void TocarNovo(String musica) {
		  this.musica = musica;
	      Media media = new Media(new File(musica).toURI().toString());
	      mediaPlayer = new MediaPlayer(media); 
	      mediaPlayer.play();
	}
	public void play(String musica) {
	    if (!musica.equals(this.musica)) {
	        // A música é diferente, crie uma nova instância de Media
	        this.musica = musica;
	        Media media = new Media(new File(musica).toURI().toString());
	        mediaPlayer = new MediaPlayer(media);
	        
	        if (currentPosition != null) {
	            mediaPlayer.seek(currentPosition); // Volte para a posição anteriormente armazenada
	            currentPosition = null; // Redefina a posição armazenada após usá-la
	        }
	    }

	    mediaPlayer.play();
	}
	public void playPlaylist(String musica) {
	
	        // A música é diferente, crie uma nova instância de Media
	        this.musica = musica;
	        Media media = new Media(new File(musica).toURI().toString());
	        mediaPlayer = new MediaPlayer(media);
	        
	        if (currentPosition != null) {
	            mediaPlayer.seek(currentPosition); // Volte para a posição anteriormente armazenada
	            currentPosition = null; // Redefina a posição armazenada após usá-la
	        }
	    

	    mediaPlayer.play();
	}

    
	public void stop() {
	    if (mediaPlayer != null) {
	        currentPosition = mediaPlayer.getCurrentTime(); // Armazena a posição atual
	        mediaPlayer.stop();
	    }
	}

    
    public void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            currentPosition = mediaPlayer.getCurrentTime(); // Armazene a posição atual
        }
    }
    
    @Override
    public void AjustarVolume(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume / 100);
        }
    }
    
  
}
