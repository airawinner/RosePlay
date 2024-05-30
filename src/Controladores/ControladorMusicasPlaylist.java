package Controladores;

import java.io.IOException;

import PlayerLogica.Musica;
import PlayerLogica.TocasVariasMusicas;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.control.Slider;

public class ControladorMusicasPlaylist {
	private ControladorTocador principalController;
	private ControladorMusicasPlaylist controladorMusicasPlay;
	private TocasVariasMusicas tocadorVar = new TocasVariasMusicas();
	
	@FXML
	private ImageView idPlay;
	@FXML
	private Label songName;
	@FXML
	private ImageView remover;
	@FXML
	private Label nomeMusica;
   public  static int  contador=0;
	@FXML
	private ImageView imgPlaylistMusica;

	@FXML
	private TextField textFieldTexto;
   
	public ControladorMusicasPlaylist getControladorMusicasPlay() {
		return controladorMusicasPlay;
	}

	public void setControladorMusicasPlay(ControladorMusicasPlaylist controladorMusicasPlay) {
		this.controladorMusicasPlay = controladorMusicasPlay;
	}

	@FXML
	void NovaPlaylist(MouseEvent event) {

	}

	public TocasVariasMusicas getTocadorVar() {
		return tocadorVar;
	}

	public void setTocadorVar(TocasVariasMusicas tocadorVar) {
		this.tocadorVar = tocadorVar;
	}

	public Label getSongName() {
		return songName;
	}

	public void setSongName(Label songName) {
		this.songName = songName;
	}

	public Label getNomeMusica() {
		return nomeMusica;
	}

	public void setNomeMusica(Label nomeMusica) {
		this.nomeMusica = nomeMusica;
	}

	public ImageView getImgPlaylistMusica() {
		return imgPlaylistMusica;
	}

	public void setImgPlaylistMusica(ImageView imgPlaylistMusica) {
		this.imgPlaylistMusica = imgPlaylistMusica;
	}
	

	public ControladorTocador getPrincipalController() {
		return principalController;
	}

	public void setPrincipalController(ControladorTocador principalController) {
		this.principalController = principalController;
	}

	public void setmusicas(Musica musica) {
		Image imageId = new Image(getClass().getResourceAsStream(musica.getFilePath()));
		Image image = new Image("/img/play.png");

		imgPlaylistMusica.setImage(imageId);
		nomeMusica.setText(musica.getTitulo());
		idPlay.setImage(image);

	}

	@FXML
	void TecladoPressionado(KeyEvent event) {
		// metodounitil
	}
	void atualizarFxmlMusicaAtual() {
		try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			this.principalController.getVboxMusicaAtual().getChildren().clear();
           
			fxmlLoader.setLocation(getClass().getResource("/application/MusicaAtual.fxml"));
			VBox musicaAtual = fxmlLoader.load();
			
			ControladorNovasMusicas songController = fxmlLoader.getController();
			songController.setMusicAtual(tocadorVar.MusicaAtual(tocadorVar.getMusica()));
			this.principalController.getVboxMusicaAtual().getChildren().add(musicaAtual);
		

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    public void carregarSlider() {
    	
   	 FXMLLoader fxml = new FXMLLoader();
           fxml.setLocation(getClass().getResource("/application/TocadorMenu.fxml"));    
           ControladorTocador controle= fxml.getController();
           controle.setTocadorVar(tocadorVar);
           
           this.principalController.iniciarSlider();
           
   		
   }
	@FXML
	void TocarMusica(MouseEvent event) {
		if (event.getSource() == remover) {
			//tocadorVar.procurarPlaylist(null);
		}
      
		if (event.getSource() == idPlay) {
			 tocadorVar.stop();
				
	
				String nome =tocadorVar.procurarMusicaFora(nomeMusica.getText());
				tocadorVar.AdicionarMusicaAtual(tocadorVar.MusicaAtual(nome));
				tocadorVar.playPlaylist(tocadorVar.AcessarMusicaAtualMae());
				
				if (contador == 0 && ControladorTocador.getIniciado()==0){
		
				this.principalController.iniciarSlider();
				 contador=1;
				}else {
					this.principalController.pausarSlider();
					this.principalController.reiniciarSlider();	
				}
				this.principalController.getBotaoPlayInicial().setVisible(false);
				this.principalController.getBotaoPlay().setVisible(false);
				this.principalController.getBotaoPause().setVisible(true);
				this.principalController.getLabelTime().setText(tocadorVar.duracaoMusica(tocadorVar.MusicaAtual(tocadorVar.getMusica())));
		        atualizarFxmlMusicaAtual();
		         
			} else {
				tocadorVar.stop();
			 this.principalController.pausarSlider();
				
			}
	}


}
