package Controladores;

import javafx.animation.Timeline;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.scene.image.Image;
import javafx.util.Duration;
import PlayerLogica.*;
/*
 * ===========================================================================
 * Avisos
 * ===========================================================================
 * Quando eu estava terminando o projeto, percebi que teria sido mais fácil fazer uma classe com um método 
 * abstrato para carregar qualquer fxml. Isso teria deixado o código mais padronizado e com menos linhas de código.
 * Por favor, leve isso em consideração. :)
 * 
 * Você pode tocar a música pelo ImageView de play ou clicando na tela principal (menu) em cima da imagem de capa.
 * Já dentro das playlists, só toca uma música se clicado em cima do ícone verdinho.
 * 
 * Albuns e artistas: se clicado em cima da Label, apenas exibe-os vindo de um arquivo de texto. O objetivo é apenas exibir;
 * não há métodos de pesquisa e nem uma ação ligada a eles quando clicados.
 * O código está grande, mas tem poucas funcionalidades. O que complicou mesmo foi ter mais de um controlador nos fxmls.
 * 
 * Os métodos de próximo e anterior, juntamente com os ImageView, só funcionam para percorrer a playlist que contém todas as músicas
 * e não as personalizadas.
 * 
 * Você pode personalizar a playlist apenas alterando o nome e adicionando uma imagem. Essa imagem deve estar obrigatoriamente
 * dentro de algum lugar após a pasta "src", normalmente a pasta "img".
 * 
 * O slider (o de volume e o de percorrer o tempo da música) foi feito de um jeitinho diferente. Seria mais fácil usar uma
 * timeline do que ficar dando set a cada segundo percorrido, pois deu menos problemas para adaptar. Principalmente porque
 * dentro dos fxmls indexados nas VBox do Menu, eu precisava ter acesso a ele.
 */


public class ControladorTocador {
	private InfoAlbumArtista informacoes = new InfoAlbumArtista();
	private TocasVariasMusicas tocadorVar = new TocasVariasMusicas();
    private TratamentoDadosTexto tratamento=new  TratamentoDadosTexto();
    private  static  int iniciado=0;
	@FXML
	private ImageView salvar;
	@FXML
	private Label albuns;

	@FXML
	private Label artistas;
	@FXML
	private HBox HboxMusicaAtual = new HBox();
	@FXML
	private VBox VboxMaior;
	@FXML
	private VBox VboxMusicaAtual;
	@FXML
	private VBox Recentes;
	@FXML
	private ImageView botaoProximo;
	private VBox playlistsContainer = new VBox();
	@FXML
	private ImageView BotaoAnterior;
	@FXML
	private ScrollPane ScrollPlayLists;
	@FXML
	private Slider volumeSlider;
	@FXML
	private Timeline timeline;
	@FXML
	private ImageView ImageMenu;
	
	@FXML
	private Slider slider;
	

	@FXML
	private HBox recentlyPlayedContainer;

	@FXML
	private HBox favoriteContainer;

	@FXML
	private Label labelTime;

	@FXML
	private Label LabelPlayLists;
	@FXML
	private Label Contador;
	@FXML
	private ImageView NovaPlayList;
	@FXML
	private ImageView BotaoPlay;
	@FXML
	private ImageView BotaoPause;
	@FXML
	private ImageView reproducaoAleatoria;
	@FXML
	private ImageView ReproducaoUnica;
	@FXML
	private ScrollPane Scroller;
	@FXML
	private ImageView botaoPlayInicial;
	@FXML
	private Label Menu;
	@FXML
	private Label TodasMusicas;
    
	public TratamentoDadosTexto getTratamento() {
		return tratamento;
	}

	public void setTratamento(TratamentoDadosTexto tratamento) {
		this.tratamento = tratamento;
	}

	public InfoAlbumArtista getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(InfoAlbumArtista informacoes) {
		this.informacoes = informacoes;
	}

	public TocasVariasMusicas getTocadorVar() {
		return tocadorVar;
	}

	public void setTocadorVar(TocasVariasMusicas tocadorVar) {
		this.tocadorVar = tocadorVar;
	}

	public Label getAlbuns() {
		return albuns;
	}

	public void setAlbuns(Label albuns) {
		this.albuns = albuns;
	}

	public Label getArtistas() {
		return artistas;
	}

	public void setArtistas(Label artistas) {
		this.artistas = artistas;
	}

	public HBox getHboxMusicaAtual() {
		return HboxMusicaAtual;
	}

	public void setHboxMusicaAtual(HBox hboxMusicaAtual) {
		HboxMusicaAtual = hboxMusicaAtual;
	}

	public VBox getVboxMaior() {
		return VboxMaior;
	}

	public void setVboxMaior(VBox vboxMaior) {
		VboxMaior = vboxMaior;
	}

	public VBox getVboxMusicaAtual() {
		return VboxMusicaAtual;
	}

	public void setVboxMusicaAtual(VBox vboxMusicaAtual) {
		VboxMusicaAtual = vboxMusicaAtual;
	}

	public VBox getRecentes() {
		return Recentes;
	}

	public void setRecentes(VBox recentes) {
		Recentes = recentes;
	}

	public ImageView getBotaoProximo() {
		return botaoProximo;
	}

	public void setBotaoProximo(ImageView botaoProximo) {
		this.botaoProximo = botaoProximo;
	}

	public VBox getPlaylistsContainer() {
		return playlistsContainer;
	}

	public void setPlaylistsContainer(VBox playlistsContainer) {
		this.playlistsContainer = playlistsContainer;
	}

	public void setBotaoAnterior(ImageView botaoAnterior) {
		BotaoAnterior = botaoAnterior;
	}

	public ScrollPane getScrollPlayLists() {
		return ScrollPlayLists;
	}

	public void setScrollPlayLists(ScrollPane scrollPlayLists) {
		ScrollPlayLists = scrollPlayLists;
	}

	public Slider getVolumeSlider() {
		return volumeSlider;
	}

	public void setVolumeSlider(Slider volumeSlider) {
		this.volumeSlider = volumeSlider;
	}

	public Timeline getTimeline() {
		return timeline;
	}

	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}

	public ImageView getImageMenu() {
		return ImageMenu;
	}

	public void setImageMenu(ImageView imageMenu) {
		ImageMenu = imageMenu;
	}


	public Slider getSlider() {
		return slider;
	}

	public void setSlider(Slider slider) {
		this.slider = slider;
	}

	
	public HBox getRecentlyPlayedContainer() {
		return recentlyPlayedContainer;
	}

	public void setRecentlyPlayedContainer(HBox recentlyPlayedContainer) {
		this.recentlyPlayedContainer = recentlyPlayedContainer;
	}

	public HBox getFavoriteContainer() {
		return favoriteContainer;
	}

	public void setFavoriteContainer(HBox favoriteContainer) {
		this.favoriteContainer = favoriteContainer;
	}

	public Label getLabelTime() {
		return labelTime;
	}

	public void setLabelTime(Label labelTime) {
		this.labelTime = labelTime;
	}

	public Label getLabelPlayLists() {
		return LabelPlayLists;
	}

	public void setLabelPlayLists(Label labelPlayLists) {
		LabelPlayLists = labelPlayLists;
	}

	public Label getContador() {
		return Contador;
	}

	public static int getIniciado() {
		return iniciado;
	}

	public static void setIniciado(int iniciado) {
		ControladorTocador.iniciado = iniciado;
	}

	public void setContador(Label contador) {
		Contador = contador;
	}

	public ImageView getNovaPlayList() {
		return NovaPlayList;
	}

	public void setNovaPlayList(ImageView novaPlayList) {
		NovaPlayList = novaPlayList;
	}

	public ImageView getBotaoPlay() {
		return BotaoPlay;
	}

	public void setBotaoPlay(ImageView botaoPlay) {
		BotaoPlay = botaoPlay;
	}

	public ImageView getBotaoPause() {
		return BotaoPause;
	}

	public void setBotaoPause(ImageView botaoPause) {
		BotaoPause = botaoPause;
	}

	public ImageView getReproducaoAleatoria() {
		return reproducaoAleatoria;
	}

	public void setReproducaoAleatoria(ImageView reproducaoAleatoria) {
		this.reproducaoAleatoria = reproducaoAleatoria;
	}

	public ImageView getReproducaoUnica() {
		return ReproducaoUnica;
	}

	public void setReproducaoUnica(ImageView reproducaoUnica) {
		ReproducaoUnica = reproducaoUnica;
	}

	public ScrollPane getScroller() {
		return Scroller;
	}

	public void setScroller(ScrollPane scroller) {
		Scroller = scroller;
	}

	public ImageView getBotaoPlayInicial() {
		return botaoPlayInicial;
	}

	public void setBotaoPlayInicial(ImageView botaoPlayInicial) {
		this.botaoPlayInicial = botaoPlayInicial;
	}

	public Label getMenu() {
		return Menu;
	}

	public void setMenu(Label menu) {
		Menu = menu;
	}

	public Label getTodasMusicas() {
		return TodasMusicas;
	}

	public void setTodasMusicas(Label todasMusicas) {
		TodasMusicas = todasMusicas;
	}

	
	@FXML
	/**
	  quando atualizado a tela, é carregado todas as playlists e diicondo em uma VBox no cantinho esquerdo da tela **/
	void lerPlayList() {
		try {
			playlistsContainer.getChildren().clear();

			for (PlayList playlist : tocadorVar.getPlaylists()) {
				
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/application/Playlists.fxml"));
				VBox playlistPane = fxmlLoader.load();
				ControladorNovasMusicas controlePlaylist = fxmlLoader.getController();
				controlePlaylist.setPrincipalController(this);
				
				controlePlaylist.AdicionarPlayListTela(playlist);
				controlePlaylist.setTocadorVar(tocadorVar);
			
				playlistsContainer.getChildren().add(playlistPane);

			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
   /**quando clicado no imageview home é carreado novamentes osfxmlquando todas as musicas **/

	void menu() {
		try {
			this.Recentes.getChildren().clear();
			this.recentlyPlayedContainer.getChildren().clear();
	        this.VboxMaior.getChildren().clear();
			for (Musica musica : tratamento.getTodasMusicas()) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/application/song.fxml"));
				VBox vBox = fxmlLoader.load();
				ControladorNovasMusicas songController = fxmlLoader.getController();
				songController.setPrincipalController(this);
				songController.setData(musica);
				recentlyPlayedContainer.getChildren().add(vBox);
			}

			Scroller.setContent(recentlyPlayedContainer);
			Recentes.getChildren().add(recentlyPlayedContainer);
			VboxMaior.getChildren().addAll(Scroller);



		} catch (IOException e) {
			e.printStackTrace();
		}
	}
/** método de action do imageview separadamente**/
	@FXML
	void ConfiurarMenu(MouseEvent event) {
		if (event.getSource() == ImageMenu) {
			this.Recentes.getChildren().clear();
			this.recentlyPlayedContainer.getChildren();
	        this.VboxMaior.getChildren().clear();
			menu();
		}
	}
/**adicionando informações dos artistas quando clicado na label correspondente,  o mesmo para albuns e playlists**/
	void adicionarArtista() {
		try {
			playlistsContainer.getChildren().clear();
			for (Artista artista : informacoes.getArtistas()) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/application/Artistas&Albuns.fxml"));
				VBox ArtistaPane = fxmlLoader.load();
				ControladorNovasMusicas controlePlaylist = fxmlLoader.getController();
				controlePlaylist.AdicionarArtista(artista);
				
				playlistsContainer.getChildren().add(ArtistaPane);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void ajustartempo(MouseEvent event) {
		double tempo = slider.getValue();
		tocadorVar.ajustarnoFilho(tempo);
	}

	public void adicionarAlbum() {
		try {
			playlistsContainer.getChildren().clear();
			for (Album album : informacoes.getAlbuns()) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/application/Artistas&Albuns.fxml"));
				VBox ArtistaPane = fxmlLoader.load();
				ControladorNovasMusicas controlePlaylist = fxmlLoader.getController();
				controlePlaylist.AdicionarAlbum(album);
				
				playlistsContainer.getChildren().add(ArtistaPane);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
/**action principal de play, pause,próximo e anterior, uma abordagem possível seria utilizar uma variavel de controle para
 * poder identificar se o mediaplayer do javafx foi instanciado ou utilizar dois imageviews na mesma posição, um seria para a primeira musica e depois nunca mais é exibido e outro 
 * continua sendo exibido normalmente e alternando com o imageview de pause**/
	@FXML
	void lidarcomMouse(MouseEvent event) {
		int duracao = tocadorVar.MusicaAtual(tocadorVar.AcessarMusicaAtualMae()).getDuracao();
		labelTime.setText(tocadorVar.duracaoMusica(tocadorVar.MusicaAtual(tocadorVar.AcessarMusicaAtualMae())));
		if (event.getSource() == ReproducaoUnica) {
			labelTime.setText(tocadorVar.duracaoMusica(tocadorVar.MusicaAtual(tocadorVar.AcessarMusicaAtualMae())));
			atualizarIconesMusicaAtual(tocadorVar.MusicaAtual(tocadorVar.AcessarMusicaAtualMae()));
			

			if (slider.getValue() == duracao) {
				reiniciarSlider();
				tocadorVar.play(tocadorVar.MusicaInicialString());
			}
		}

		if (event.getSource() == botaoPlayInicial) {

			tocadorVar.AjustarVolume(volumeSlider.getValue());
			//tocadorVar.AdicionarMusicaAtual(tocadorVar.MusicaAtual(tocadorVar.MusicaInicialString()));
			tocadorVar.playPlaylist(tocadorVar.MusicaInicialString());
			BotaoPlay.setVisible(false);
			botaoPlayInicial.setVisible(false);
			BotaoPause.setVisible(true);
			labelTime.setText(tocadorVar.duracaoMusica(tocadorVar.MusicaAtual(tocadorVar.AcessarMusicaAtualMae())));
			iniciarSlider();

		}

		if (event.getSource() == BotaoPlay) {
			// O clique ocorreu no botaoPLAY
			tocadorVar.AjustarVolume(volumeSlider.getValue());
			BotaoPlay.setVisible(false);
			BotaoPause.setVisible(true);

			if (!tocadorVar.getMusica().equals(tocadorVar.AcessarMusicaAtualMae())) {
				// A música é diferente, reinicie o slider
				reiniciarSlider();
			} else {
				// A música é a mesma, continue de onde parou
				timeline.play();
			}

			labelTime.setText(tocadorVar.duracaoMusica(tocadorVar.MusicaAtual(tocadorVar.getMusica())));
			tocadorVar.play(tocadorVar.AcessarMusicaAtualMae());
		}
		if (event.getSource() == BotaoPause) {
			// O clique ocorreu no botaoPAUSE
			if (tocadorVar.getMusica().equals(tocadorVar.AcessarMusicaAtualMae())) {
				// A música é a mesma, pausar a reprodução
				tocadorVar.pause();

			}

			BotaoPause.setVisible(false);
			BotaoPlay.setVisible(true);
			pausarSlider();
		}

		if (event.getSource() == botaoProximo) {
			tocadorVar.stop();
			labelTime.setText(tocadorVar.duracaoMusica(tocadorVar.MusicaAtual(tocadorVar.AcessarMusicaAtualMae())));
			int aux = tocadorVar.indiceMusicaAtual(tocadorVar.AcessarMusicaAtualMae());
			tocadorVar.ProximaMusica(aux);
			atualizarIconesMusicaAtual(tocadorVar.MusicaAtual(tocadorVar.AcessarMusicaAtualMae()));

			reiniciarSlider();

		}
		if (event.getSource().equals(artistas)) {
		
			adicionarArtista();
		}
		if (event.getSource() == albuns) {
			
			adicionarAlbum();
		}
		if (event.getSource() == BotaoAnterior) {
			tocadorVar.stop();
			int aux = tocadorVar.indiceMusicaAtual(tocadorVar.AcessarMusicaAtualMae());
			tocadorVar.MusicaAnterior(aux);

			atualizarIconesMusicaAtual(tocadorVar.MusicaAtual(tocadorVar.AcessarMusicaAtualMae()));
			labelTime.setText(tocadorVar.duracaoMusica(tocadorVar.MusicaAtual(tocadorVar.AcessarMusicaAtualMae())));

			reiniciarSlider();
		}
		if (event.getSource().equals(LabelPlayLists)) {
	
			lerPlayList();
		}

	}
/** quando clicado em adicionar uma nova playlist ele carrega as informações padrão,chamando o fxml com o layout e também  o respectivo controlador**/
	@FXML
	void NovaPlaylist(MouseEvent event) {
		if (event.getSource() == NovaPlayList) {
			try {

				PlayList playlist = new PlayList();
				tocadorVar.AdicionarPlaylist(playlist);
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/application/Playlists.fxml"));
				VBox playlistPane = fxmlLoader.load();
				ControladorNovasMusicas controlePlaylist = fxmlLoader.getController();
				controlePlaylist.setPrincipalController(this);
				controlePlaylist.setTocadorVar(tocadorVar);
			
				controlePlaylist.AdicionarPlayListTela(playlist);

				playlistsContainer.getChildren().add(playlistPane);

				recentlyPlayedContainer.getChildren().clear();
				Recentes.getChildren().clear();
				VboxMaior.getChildren().clear();
				FXMLLoader fxml = new FXMLLoader();
				fxml.setLocation(getClass().getResource("/application/TocadorPlay.fxml"));
				VBox playlistMaior = fxml.load();
				ControladorPlaylist controle = fxml.getController();
				controle.setPrincipalController(this);
				controle.setData();
				controle.setTocadorVar(tocadorVar);
			
				VboxMaior.getChildren().add(playlistMaior);

			} catch (IOException e) {
				e.printStackTrace();

			}

		}
	}
/**o slider utiliza uma timeline  para poder atualizar e contabilizar o tempo de música percorrida, e tamém pausar quando ocorrer um evento de ação do botão pause
 * é mais fácil usar uma timeline do que umcontador e só usando o setvalue() do slider **/
	public void iniciarSlider() {
		int duracaoTotalSegundos = tocadorVar.MusicaAtual(tocadorVar.AcessarMusicaAtualMae()).getDuracao(); // Obtenha a
			iniciado=1;																								// duração
																											// total em																										// segundos

		timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> atualizarSlider(duracaoTotalSegundos)));
		timeline.setCycleCount(duracaoTotalSegundos);
		timeline.setRate(1);
		timeline.setOnFinished(e -> tocadorVar.stop());
		this.setTimeline(timeline);
		slider.setMax(duracaoTotalSegundos);
		slider.setBlockIncrement(1);
		slider.setMajorTickUnit(1);
		slider.setMinorTickCount(1);
		timeline.playFromStart();
	}

	public void atualizarSlider(int duracaoTotalSegundos) {
		if (slider.getValue() < duracaoTotalSegundos) {
			slider.setValue(slider.getValue() + 1);
			String tempoFormatado = tocadorVar.duracaoMusica(tocadorVar.MusicaAtual(tocadorVar.AcessarMusicaAtualMae()),
					slider.getValue());
			Contador.setText(tempoFormatado);
		} else {
			pausarSlider();

		}
	}

	public void reiniciarSlider() {
		timeline.stop();
		slider.setValue(0);
		this.setTimeline(timeline);
		labelTime.setText(tocadorVar.duracaoMusica(tocadorVar.MusicaAtual(tocadorVar.AcessarMusicaAtualMae())));
		timeline.playFromStart();

	}

	public void pausarSlider() {
		if (timeline != null) {
			timeline.pause();
		}
	}
/**chamar um metodo de outra classe de lógica de conversão de string para double e formatação de texto para ajustar o volume atráves do valor atual do slider de volume*/
	public void ajustarVolume() {
		double volume = volumeSlider.getValue(); // Obtém o valor do slider de volume

		if (tocadorVar != null) {
			tocadorVar.AjustarVolume(volume);
		}
	}
/**A cada clique de mudar de tela, precisa-se atualizar as inforamções na tela de qual música está sendo exibida**/
	void atualizarIconesMusicaAtual(Musica Musica) {
		try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			VboxMusicaAtual.getChildren().clear();

			fxmlLoader.setLocation(getClass().getResource("/application/MusicaAtual.fxml"));
			VBox musicaAtual = fxmlLoader.load();
			ControladorNovasMusicas songController = fxmlLoader.getController();
			songController.setPrincipalController(this);
			songController.setMusicAtual(tocadorVar.MusicaAtual(tocadorVar.getMusica()));
		
			VboxMusicaAtual.getChildren().add(musicaAtual);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
/**inicializa três fxmls para adicionar dentro de um fxml maior, no caso o pertecente a esse controlador.
 * Inicialmente temos algumas configurações,como todas as músicas para ser exibidas e as playlist já criadas**/
	@FXML
	public void initialize() {
		BotaoPause.setVisible(false);
		try {
		
			for (Musica musica : tratamento.lerMusicasDoArquivo()) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/application/song.fxml"));
		
                tocadorVar.setMusica(musica.getMusicaMp3());
			
				VBox vBox = fxmlLoader.load();
				ControladorNovasMusicas songController = fxmlLoader.getController();
				songController.setPrincipalController(this);

				songController.setData(musica);
				songController.setTocadorVar(tocadorVar);
			
				recentlyPlayedContainer.getChildren().add(vBox);
			}

			for (PlayList playlist : tocadorVar.getPlaylists()) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/application/Playlists.fxml"));
				VBox playlistPane = fxmlLoader.load();
				ControladorNovasMusicas controlePlaylist = fxmlLoader.getController();
				controlePlaylist.setPrincipalController(this);
				controlePlaylist.AdicionarPlayListTela(playlist);
				controlePlaylist.setTocadorVar(tocadorVar);
			

				playlistsContainer.getChildren().add(playlistPane);

			}

			ScrollPlayLists.setContent(playlistsContainer);
			FXMLLoader fxmlLoader = new FXMLLoader();
			this.getVboxMusicaAtual().getChildren().clear();
			fxmlLoader.setLocation(getClass().getResource("/application/MusicaAtual.fxml"));
			VBox musicaAtual = fxmlLoader.load();
			ControladorNovasMusicas songController = fxmlLoader.getController();
			songController.setPrincipalController(this);
			tocadorVar.AdicionarMusicaAtual(tocadorVar.MusicaAtual(tocadorVar.MusicaInicialString()));
			songController.setMusicAtual(tocadorVar.MusicaAtual(tocadorVar.MusicaInicialString()));
			VboxMusicaAtual.getChildren().add(musicaAtual);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
/** método construtor pra evitar Exceções verificadas **/
	public ControladorTocador() {
		this.Recentes = getRecentes();
		this.recentlyPlayedContainer = getRecentlyPlayedContainer();
	}
/**
 * para adicionar componentes na Vbox precisar limpar a parte dela que contém configurações.Repassar tudo de novo
 * normalmente utiliza-se esse  método em um controlador externo, no caso adicionando as configurações do TocadorPlay.fxml
 * 
 * 
 * **/
	public void AdicionarComponentes(VBox vbox) {
		this.Recentes.getChildren().clear();
		this.recentlyPlayedContainer.getChildren();
        this.VboxMaior.getChildren().clear();
		this.VboxMaior.getChildren().add(vbox);
	}
/** método auxiliar para poder acessar o vbox de um controlador externo, pois nem sempre os getters e setters são válidos **/
	public void AdicionarMuscaAtual(VBox musica) {

		VboxMusicaAtual.getChildren().add(musica);

	}
/**Ao clicar na imageview de três pontinhos perto do home salvará todas as mudanças dentro dos arquivos textos **/
	@FXML
	void salvarInformaoes(MouseEvent event) {
		if (event.getSource() == salvar) {
          tratamento.converterPlaylistsEmTexto(tocadorVar.getPlaylists());
		}
	}

}
