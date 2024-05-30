package Controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.scene.Parent;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.File;
import java.io.IOException;

import PlayerLogica.*;

public class ControladorNovasMusicas {
	
	private StringProperty nomePlaylist = new SimpleStringProperty();
	private PlayList playAlterar = new PlayList();
	private ControladorTocador principalController;
	private static int contador = 0;
	@FXML
	private TextField textFieldTexto;
	@FXML
	private ImageView imgemPlayList;
	@FXML
	private ImageView img;
	private StringBuilder textoEditavel = new StringBuilder();
	@FXML
	private Label songName;
	private TocasVariasMusicas tocadorVar = new TocasVariasMusicas();
	@FXML
	private Label artist;
	@FXML
	private Label NomePlayList;
	@FXML
	private ImageView ImgMUsicaAtual;

	@FXML
	private ScrollPane scrollPane;
	@FXML
	private Label TíyulodaMusica;
	
	@FXML
	private Label NomedoArtista;

	public void setPrincipalController(ControladorTocador principalController) {
		this.principalController = principalController;
	}

	public TocasVariasMusicas getTocadorVar() {
		return tocadorVar;
	}

	public void setTocadorVar(TocasVariasMusicas tocadorVar) {
		this.tocadorVar = tocadorVar;
	}

	public TextField getTextFieldTexto() {
		return textFieldTexto;
	}

	public void setTextFieldTexto(TextField textFieldTexto) {
		this.textFieldTexto = textFieldTexto;
	}

	public ImageView getImgemPlayList() {
		return imgemPlayList;
	}

	public void setImgemPlayList(ImageView imgemPlayList) {
		this.imgemPlayList = imgemPlayList;
	}

	public ImageView getImg() {
		return img;
	}

	public void setImg(ImageView img) {
		this.img = img;
	}

	public StringBuilder getTextoEditavel() {
		return textoEditavel;
	}

	public void setTextoEditavel(StringBuilder textoEditavel) {
		this.textoEditavel = textoEditavel;
	}

	public Label getSongName() {
		return songName;
	}

	public void setSongName(Label songName) {
		this.songName = songName;
	}

	public Label getArtist() {
		return artist;
	}

	public void setArtist(Label artist) {
		this.artist = artist;
	}

	public Label getNomePlayList() {
		return NomePlayList;
	}

	public void setNomePlayList(Label nomePlayList) {
		NomePlayList = nomePlayList;
	}

	public ImageView getImgMUsicaAtual() {
		return ImgMUsicaAtual;
	}

	public void setImgMUsicaAtual(ImageView imgMUsicaAtual) {
		ImgMUsicaAtual = imgMUsicaAtual;
	}

	public Label getTíyulodaMusica() {
		return TíyulodaMusica;
	}

	public void setTíyulodaMusica(Label tíyulodaMusica) {
		TíyulodaMusica = tíyulodaMusica;
	}

	public Label getNomedoArtista() {
		return NomedoArtista;
	}

	public void setNomedoArtista(Label nomedoArtista) {
		NomedoArtista = nomedoArtista;
	}

	public void setNomePlaylist(String nomePlaylist) {
		this.nomePlaylist.set(nomePlaylist);
	}
  /**toda vez que o fzml écarregado de um outro controlador precisa-se exibir as imageview, labels e etc com o
    nome e imagem do objeto musica, assim adicionará as imagens diferente paracada música, esse método deve ser chamado 
    após o carregamento desse classe de controle e antes de adicionar dentro de uma Vbox
    Este é um exemplo de método
 **/
	public void setData(Musica musica) {
		Image image = new Image(getClass().getResourceAsStream(musica.getFilePath()));
		img.setImage(image);
		songName.setText(musica.getTitulo());
		artist.setText(musica.getArtista());
	}
	/**idem ao anterior, porém com um objeto diferente**/
	public void AdicionarPlayListTela(PlayList playlist) {
		setNomePlaylist(playlist.getNome());
		Image image = new Image(getClass().getResourceAsStream(playlist.getCover()));
		imgemPlayList.setImage(image);
		NomePlayList.setText(playlist.getNome());
		

	}

	public void AdicionarArtista(Artista artista) {
		Image image = new Image(getClass().getResourceAsStream(artista.getFoto()));
		imgemPlayList.setImage(image);
		NomePlayList.setText(artista.getNome());
		

	}

	public void AdicionarAlbum(Album album) {
		Image image = new Image(getClass().getResourceAsStream(album.getFotoCapa()));
		imgemPlayList.setImage(image);
		NomePlayList.setText(album.getTitulo());

	}

	public void setMusicAtual(Musica musica) {
		Image image = new Image(getClass().getResourceAsStream(musica.getFilePath()));
		ImgMUsicaAtual.setImage(image);
		TíyulodaMusica.setText(musica.getTitulo());
		NomedoArtista.setText(musica.getArtista());

	}

	public void abrirArquivo() {
		// Criar um seletor de arquivo
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("TrocarImagem playlist");

		// Definir os filtros de extensão permitidos
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Imagens", "*.jpg", "*.jpeg", "*.png", "*.gif"),
				new FileChooser.ExtensionFilter("Todos os Arquivos", "*.*"));

		// Mostrar o seletor de arquivo e obter o arquivo selecionado
		File arquivo = fileChooser.showOpenDialog(null);

		// Verificar se um arquivo foi selecionado
		if (arquivo != null) {
			// Carregar a imagem do arquivo selecionado
			Image imagem = new Image(arquivo.toURI().toString());
			imgemPlayList.setImage(imagem);

		}
	}

	void carregarPlayList(PlayList playlist) {
		try {
			FXMLLoader fxml = new FXMLLoader();
			fxml.setLocation(getClass().getResource("/application/TocadorPlay.fxml"));
			VBox playlistMaior = fxml.load();
			ControladorPlaylist controle = fxml.getController();
			controle.setPrincipalController(this.principalController);
			controle.setInfoPlaylist(playlist);
			controle.setTocadorVar(tocadorVar);
			

			this.principalController.AdicionarComponentes(playlistMaior);
		} catch (IOException e) {

		}

	}

	public void musicaAtual(Musica musica) {
		try {
			this.principalController.getVboxMusicaAtual().getChildren().clear();
			FXMLLoader fxml = new FXMLLoader();
			fxml.setLocation(getClass().getResource("/application/MusicaAtual.fxml"));
			VBox MusicaAtual = fxml.load();

			setMusicAtual(musica);

			this.principalController.AdicionarMuscaAtual(MusicaAtual);
		} catch (IOException e) {

		}
	}

	@FXML
	void NovaPlaylist(MouseEvent event) {
		if (event.getSource() == imgemPlayList) {
			for (PlayList playlist : tocadorVar.getPlaylists()) {
				if (playlist.getNome().equals(NomePlayList.getText())) {
			
					carregarPlayList(playlist);
				}

			}

		}
		if (event.getSource() == NomePlayList) {
			NomePlayList.setVisible(false);
			textFieldTexto.setText(NomePlayList.getText());
			textFieldTexto.setVisible(true);
			textFieldTexto.requestFocus();

			// Obter o texto atual do Label
			String textoAtual = NomePlayList.getText();
			// Inicializar o StringBuilder com o texto atual
			textoEditavel.setLength(0);
			textoEditavel.append(textoAtual);
			// Exibir o texto atual no Label como um campo de edição
			NomePlayList.setText(textoEditavel.toString());

		}

	}

	@FXML
	void EventoImagem(MouseEvent event) {

		if (event.getSource() == imgemPlayList) {

			for (PlayList playlist : tocadorVar.getPlaylists()) {
				if (playlist.getNome().equals(NomePlayList.getText())) {
					carregarPlayList(playlist);
				}

			}

		}

		if (event.getSource() == NomePlayList) {

			playAlterar = tocadorVar.procurarPlaylist(NomePlayList.getText());
			NomePlayList.setVisible(false);
			textFieldTexto.setText(NomePlayList.getText());
			textFieldTexto.setVisible(true);
			textFieldTexto.requestFocus();

			// Obter o texto atual do Label
			String textoAtual = NomePlayList.getText();
			// Inicializar o StringBuilder com o texto atual
			textoEditavel.setLength(0);
			textoEditavel.append(textoAtual);
			// Exibir o texto atual no Label como um campo de edição
			NomePlayList.setText(textoEditavel.toString());

		}
	}

	/**
	 * Verifica se a tecla enter foi presssionada para salvar no label da playlist o
	 * novo nome tribuido
	 **/
	@FXML
	private void TecladoPressionado(KeyEvent event) {
		// Verificar se a tecla pressionada foi Enter
		if (event.getCode() == KeyCode.ENTER) {
			// Obter o novo texto do TextField
			String novoTexto = textFieldTexto.getText();
			// Atualizar o texto do Label
			NomePlayList.setText(novoTexto);

			tocadorVar.AlterarPlaylist(novoTexto, this.playAlterar.getCover(), this.playAlterar.getNome());
			// Exibir o Label novamente e ocultar o TextField
			NomePlayList.setVisible(true);
			textFieldTexto.setVisible(false);

		}
	}

	/*
	 * método para acessar o slider dentro de outroocntrolador, carrega o fxml, o
	 * controlador e chama o método de lá paraconeguir atualizar o slider
	 */
	public void carregarSlider() {

		FXMLLoader fxml = new FXMLLoader();
		fxml.setLocation(getClass().getResource("/application/TocadorMenu.fxml"));
		ControladorTocador controle = fxml.getController();
		controle.setTocadorVar(tocadorVar);

		this.principalController.iniciarSlider();

	}

	/*
	 * a cada clique em botões de play, próximo e anterior e´necessário atualizar
	 * amúsica atual na tela carregando o fxml, o controlador responsãvel pelo
	 * layout
	 */
	void atualizarFxmlMusicaAtual() {
		try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			this.principalController.getVboxMusicaAtual().getChildren().clear();

			fxmlLoader.setLocation(getClass().getResource("/application/MusicaAtual.fxml"));
			VBox musicaAtual = fxmlLoader.load();
			ControladorNovasMusicas songController = fxmlLoader.getController();
			songController.setPrincipalController(principalController);
			songController.setMusicAtual(tocadorVar.MusicaAtual(tocadorVar.getMusica()));
			this.principalController.getVboxMusicaAtual().getChildren().add(musicaAtual);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método de action de uma imageview, nesse caso ela é responsável como indicar
	 * que vai toar uma múica linkada a essa imagem nesse caso o slider foi
	 * carregado diferente, pois necessitava de uma variavel decontrole para caso
	 * não tenha sido instaciado o mesmo
	 **/
	@FXML
	void LidarcomImagem(MouseEvent event) {
		if (event.getSource() == img) {
			 tocadorVar.stop();
				String nome = tocadorVar.procurarMusicaFora(songName.getText());
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
