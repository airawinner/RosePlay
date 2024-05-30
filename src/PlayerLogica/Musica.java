package PlayerLogica;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.util.Duration;

public class Musica {
    private String titulo;
    private String filePath;
    private String Artista;
    private  String  MusicaMp3;
    private int duracao;
    
    
    
	public int getDuracao() {
		return duracao;
	}


	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}


	public  String getMusicaMp3() {
		return MusicaMp3;
	}


	public void setMusicaMp3(String musicaMp3) {
		MusicaMp3 = musicaMp3;
	}


	public String getArtista() {
		return Artista;
	}



	public void setArtista(String artista) {
		Artista = artista;
	}


	public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Musica() {
  	  
    }
 
	public Musica(String titulo, String filePath, String artista, String musicaMp3, int duracao) {
		super();
		this.titulo = titulo;
		this.filePath = filePath;
		Artista = artista;
		MusicaMp3 = musicaMp3;
		this.duracao = duracao;
	}
    /** cada muica tem um  tempo total e precisa converter ele a cada segundo tualizado percorrido do slder para ser exibido
     * na tela, esses dois métodos fazem isso**/
	public String converter() {	
	    double valorAtualMinutos = duracao / 60;
	    int minutos = (int) valorAtualMinutos;
	    int segundos = (int) (duracao - (minutos * 60));
	    String tempoFormatado = String.format("%02d:%02d", minutos, segundos);		
		return  tempoFormatado;
	}
	public String converter(double tempo) {
		
	    double valorAtualMinutos = tempo / 60;
	    int minutos = (int) valorAtualMinutos;
	    int segundos = (int) (tempo - (minutos * 60));
	    String tempoFormatado = String.format("%02d:%02d", minutos, segundos);		
		return  tempoFormatado;
	}
}
