package Controladores;

import PlayerLogica.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import PlayerLogica.Musica;
import PlayerLogica.TocasVariasMusicas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;

public class ControladorPlaylist {
	private ControladorTocador principalController;
	private TocasVariasMusicas tocadorVar = new TocasVariasMusicas();
	private TratamentoDadosTexto tratamento=new  TratamentoDadosTexto();
	@FXML
	private VBox VoboxAdionarMusicas;
	@FXML
	private VBox VboxTudoPlayList;
	@FXML
	private ComboBox<String> comboBoxMusicas;
	@FXML
	private VBox VboxPlayList;

	@FXML
	private ImageView Imagemview;

	@FXML
	private Label NomePlaylIST;

	@FXML
	private ImageView imagIcon;

	@FXML
	private Label Incrementar;

	@FXML
	private ScrollPane scrollPane;

	@FXML
	private Label songName;

	@FXML
	private Label nomeMusica;

	@FXML
	private ImageView imgPlaylistMusica;

	@FXML
	private TextField textFieldTexto;

	@FXML
	private ImageView remover;

	public VBox getVoboxAdionarMusicas() {
		return VoboxAdionarMusicas;
	}

	public void setVoboxAdionarMusicas(VBox voboxAdionarMusicas) {
		VoboxAdionarMusicas = voboxAdionarMusicas;
	}

	public VBox getVboxTudoPlayList() {
		return VboxTudoPlayList;
	}

	public void setVboxTudoPlayList(VBox vboxTudoPlayList) {
		VboxTudoPlayList = vboxTudoPlayList;
	}

	public VBox getVboxPlayList() {
		return VboxPlayList;
	}

	public void setVboxPlayList(VBox vboxPlayList) {
		VboxPlayList = vboxPlayList;
	}

	public ImageView getImagemview() {
		return Imagemview;
	}

	public void setImagemview(ImageView imagemview) {
		Imagemview = imagemview;
	}

	public Label getNomePlaylIST() {
		return NomePlaylIST;
	}

	public void setNomePlaylIST(Label nomePlaylIST) {
		NomePlaylIST = nomePlaylIST;
	}

	public ImageView getImagIcon() {
		return imagIcon;
	}

	public void setImagIcon(ImageView imagIcon) {
		this.imagIcon = imagIcon;
	}

	public Label getIncrementar() {
		return Incrementar;
	}

	public void setIncrementar(Label incrementar) {
		Incrementar = incrementar;
	}

	public ScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(ScrollPane scrollPane) {
		this.scrollPane = scrollPane;
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

	public TextField getTextFieldTexto() {
		return textFieldTexto;
	}

	public void setTextFieldTexto(TextField textFieldTexto) {
		this.textFieldTexto = textFieldTexto;
	}

	public TocasVariasMusicas getTocadorVar() {
		return tocadorVar;
	}

	public ControladorPlaylist(TocasVariasMusicas tocadorVar) {
		super();
		this.tocadorVar = tocadorVar;
	}

	public void setTocadorVar(TocasVariasMusicas tocadorVar) {
		this.tocadorVar = tocadorVar;
	}
    	

	public ControladorTocador getPrincipalController() {
		return principalController;
	}

	public void setPrincipalController(ControladorTocador principalController) {
		this.principalController = principalController;
	}

	@FXML
	void NovaPlaylist(MouseEvent event) {

	}

	void atualizartela(PlayList playlist) {
		try {
			VBox mainContainer = new VBox(); // VBox principal para agrupar as músicas
			scrollPane.setContent(mainContainer);
			for (Musica musicaPlaylist : playlist.getMusicas()) {
				
				FXMLLoader fxml = new FXMLLoader();
				fxml.setLocation(getClass().getResource("/application/MusicasPlaylist.fxml"));
				VBox playlistMaior = fxml.load();
               
				ControladorMusicasPlaylist controller = fxml.getController();
			    controller.setPrincipalController(this.principalController);	    
				controller.setmusicas(musicaPlaylist);
                controller.setTocadorVar(tocadorVar);
				// Adicione o VBox da música ao VBox principal
				mainContainer.getChildren().add(playlistMaior);

			}

		} catch (IOException e) {
			e.printStackTrace();

		}

	}
	
	public void abrirArquivo() {
	    // Obter o diretório do projeto
	    String diretorioProjeto = System.getProperty("user.dir");

	    // Criar um seletor de arquivo com o diretório do projeto como diretório inicial
	    FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Trocar Imagem da Playlist");
	    fileChooser.setInitialDirectory(new File(diretorioProjeto));

	    // Definir os filtros de extensão permitidos
	    fileChooser.getExtensionFilters().addAll(
	            new FileChooser.ExtensionFilter("Imagens", "*.jpg", "*.jpeg", "*.png", "*.gif"),
	            new FileChooser.ExtensionFilter("Todos os Arquivos", "*.*"));

	    // Mostrar o seletor de arquivo e obter o arquivo selecionado
	    File arquivo = fileChooser.showOpenDialog(null);

	    // Verificar se um arquivo foi selecionado
	    if (arquivo != null) {
	        // Obter o caminho relativo do arquivo selecionado em relação ao diretório "src"
	        Path pathAbsoluto = Paths.get(arquivo.getAbsolutePath());
	        Path pathBase = Paths.get(diretorioProjeto);
	        Path pathRelativo = pathBase.relativize(pathAbsoluto);
	        String caminhoImagem = pathRelativo.toString().replace("\\", "/");

	        // Atualizar a imagem de capa da playlist
	        PlayList playlist = tocadorVar.procurarPlaylist(NomePlaylIST.getText());
	        playlist.setCover(caminhoImagem);

	        // Carregar a imagem do arquivo selecionado
	        Image imagem = new Image(arquivo.toURI().toString());
	        Imagemview.setImage(imagem);
	    }
	}





	@FXML
	void lidarcomEventosMouse(MouseEvent event) {
		String musica = comboBoxMusicas.getValue();
		if (event.getSource() == imagIcon) {
			for (PlayList playlist : tocadorVar.getPlaylists()) {	
				if (playlist.getNome().equals(NomePlaylIST.getText())) {
			     	playlist.AdicionarMusica(tocadorVar.ProcurarEmtodasMusicas(musica));
					atualizartela(playlist);
				}

			}
		}
		if(event.getSource()==Imagemview) {
			 abrirArquivo();
			 
			 
			
		}
	}

	public void setData() {
		Image image = new Image("/img/tres.jpg");
		Image image2 = new Image("/img/ICONEMUSIC.png");

		Imagemview.setImage(image2);
		imagIcon.setImage(image);

		Incrementar.setText("Vamos Incrementar sua playlist:)");
		NomePlaylIST.setText("Altere o nome");
	}
	
	public void setInfoPlaylist(PlayList playlist) {
		Image image = new Image("/img/tres.jpg");
		Image image2 = new Image(getClass().getResourceAsStream(playlist.getCover()));

		Imagemview.setImage(image2);
		imagIcon.setImage(image);

		Incrementar.setText("Vamos Incrementar sua playlist:)");
		NomePlaylIST.setText(playlist.getNome());
		comboBoxMusicas.setItems(getTitulosMusicas());
		 atualizartela(playlist);
	}

	public ControladorPlaylist() {
		super();
	}



	@FXML
	private ObservableList<String> getTitulosMusicas() {
		ObservableList<String> titulos = FXCollections.observableArrayList();
		for (Musica musica : tratamento.lerMusicasDoArquivo()) {
		    String titulo = musica.getTitulo();
		    if (!titulos.contains(titulo)) {
		        titulos.add(titulo);
		    }
		}


		return titulos;
	}

	@FXML
	public void initialize() {
		comboBoxMusicas.setItems(getTitulosMusicas());
		comboBoxMusicas.setStyle("-fx-background-color: black;");

		try {
			VBox mainContainer = new VBox(); // VBox principal para agrupar as músicas
			scrollPane.setContent(mainContainer);
			for (PlayList playlist : tocadorVar.getPlaylists()) {
				if(playlist.getNome().equals(NomePlaylIST.getText())) {
					for(Musica musica: playlist.getMusicas()) {
				FXMLLoader fxml = new FXMLLoader();
				fxml.setLocation(getClass().getResource("/application/MusicasPlaylist.fxml"));
				VBox playlistMaior = fxml.load();

				ControladorMusicasPlaylist controller = fxml.getController();
				controller.setPrincipalController(this.principalController);
				controller.setmusicas(musica);
				
             
				// Adicione o VBox da música ao VBox principal
				mainContainer.getChildren().add(playlistMaior);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();

		}

	}



}
