package PlayerLogica;

import java.util.ArrayList;

import java.util.List;

import servicos_Aira.*;
import servicos_Aira.TextFileApps;

/**
 * Essa classe utiliza uma de serviços para acessar o arquivo texto e passar
 * todas as informações para os objetos e também salvar as alterações ao longo
 * da execução do programa
 **/
public class TratamentoDadosTexto {
	private List<Musica> TodasMusicas = new ArrayList<>();
	private List<PlayList> Playlists = new ArrayList<>();
	private List<Artista> Artistas = new ArrayList<>();
	private List<Album> Albuns = new ArrayList<>();

	private ArrayList<String> Texto = new ArrayList<>();
	private static String arquivo = "Dados.txt";
	public static String arquivoPlayList = "DadosPlayList.txt";
	public static String artistas = "artistas.txt";
	public static String albuns = "albuns.txt";

	public List<Musica> getTodasMusicas() {
		return TodasMusicas;
	}

	public void setTodasMusicas(List<Musica> todasMusicas) {
		TodasMusicas = todasMusicas;
	}

	public ArrayList<String> getTexto() {
		return Texto;
	}

	public void setTexto(ArrayList<String> texto) {
		Texto = texto;
	}

	public static String getArquivo() {
		return arquivo;
	}

	public static void setArquivo(String arquivo) {
		TratamentoDadosTexto.arquivo = arquivo;
	}

	public TratamentoDadosTexto() {

	}

	public TratamentoDadosTexto(List<Musica> todasMusicas, ArrayList<String> texto) {
		super();
		TodasMusicas = todasMusicas;
		Texto = texto;
	}

	public Musica procurarMusicaAqui(String nome) {
		TodasMusicas = lerMusicasDoArquivo();
		for (Musica musica : TodasMusicas) {
			if (musica.getTitulo().equals(nome)) {

				return musica;
			}
		}
		return null;
	}

	public int converter(String numeroString) {
		int numero = 0;
		try {
			numero = Integer.parseInt(numeroString);
			// numero inteiro convertido
		} catch (NumberFormatException e) {
		
		}
		return numero;
	}

	public List<Musica> lermusicasdaPlaylist(int indice) {
		List<Musica> musicas = new ArrayList<>();
		ArrayList<String> linhas = TextFileApps.AbrirArquivoparaLeitura(arquivoPlayList);
		List<String> palavras = new ArrayList<>();
		// teste();
		int aux = 0;

		for (String texto : linhas) {
			if (aux == (indice + 1)) {

				String[] partes = texto.split("#");
				for (int i = 1; i < partes.length; i++) {

					String[] palavrasParte = partes[i].trim().split("\\s+");
					StringBuilder sb = new StringBuilder();
					for (String palavra : palavrasParte) {
						if (!palavra.isEmpty()) {
							sb.append(palavra).append(" ");
						}
					}
					palavras.add(sb.toString().trim());

				}

			}

			aux++;
		}

		int quantidade = 0;
		for (String palavra : palavras) {
			quantidade = converter(palavra);
			
		}
		int contador = 0;
		for (String palavra : palavras) {

			Musica musica = new Musica();
			musica = procurarMusicaAqui(palavra);
			if (musica != null) {
				musicas.add(musica);
			}
			contador++;
			if (contador == quantidade) {
				break;
			}
		}

		return musicas;
	}

	public List<PlayList> lerPlayListsDoArquivo() {

		ArrayList<String> linhas = TextFileApps.AbrirArquivoparaLeitura(arquivoPlayList);
		int indiceLinha = 0;
		for (String linha : linhas) {
			String[] dados = linha.split("\\?");
			if (dados.length == 2) {
			
				String titulo = dados[0].trim();
				String CaminhoImagem = dados[1].trim();
				PlayList playlist = new PlayList();
				playlist.setCover(CaminhoImagem);
				playlist.setNome(titulo);

				playlist.setMusicas(lermusicasdaPlaylist(indiceLinha));
			
				Playlists.add(playlist);
			}
			indiceLinha++;

		}

		return Playlists;
	}

	public List<Musica> lerMusicasDoArquivo() {

		ArrayList<String> linhas = TextFileApps.AbrirArquivoparaLeitura(arquivo);

		for (String linha : linhas) {
			String[] dados = linha.split("\\?");
			if (dados.length == 5) {
				String titulo = dados[0].trim();
				String artista = dados[1].trim();
				String filePath = dados[2].trim();
				String mp3 = dados[3].trim();
				Integer duracao = Integer.valueOf(dados[4].trim());
				Musica musica = new Musica(titulo, filePath, artista, mp3, duracao);
				TodasMusicas.add(musica);
			
			

			}
		}

		return TodasMusicas;
	}

	public List<Album> lerAlbuns() {

		ArrayList<String> linhas = TextFileApps.AbrirArquivoparaLeitura(albuns);

		for (String linha : linhas) {
			String[] dados = linha.split("\\?");
			if (dados.length == 2) {
				String nome = dados[0].trim();
				String foto = dados[1].trim();
				Album album = new Album(nome, foto);
				Albuns.add(album);

			}
		}

		return Albuns;
	}

	public List<Artista> lerArtistas() {

		ArrayList<String> linhas = TextFileApps.AbrirArquivoparaLeitura(artistas);

		for (String linha : linhas) {
			String[] dados = linha.split("\\?");
			if (dados.length == 2) {

				String nome = dados[0].trim();
				String foto = dados[1].trim();
				Artista artista = new Artista(nome, foto);
				Artistas.add(artista);

			}
		}

		return Artistas;

	}

	public void converterPlaylistsEmTexto(List<PlayList> playlists) {
		ArrayList<String> linhas = new ArrayList<>();

		for (PlayList playlist : playlists) {
			String texto = playlist.getCover();
			String resultado = texto.replaceFirst("src", "");
			playlist.setCover(resultado);
	

			StringBuilder linhaPlaylist = new StringBuilder(playlist.getNome() + " ? " + playlist.getCover());
			linhas.add(linhaPlaylist.toString());
			int cont = 0;
			StringBuilder linhaMusicas = new StringBuilder("#");
			for (Musica musica : playlist.getMusicas()) {
				linhaMusicas.append(" ").append(musica.getTitulo());
				linhaMusicas.append(" #");
				cont++;

			}
			String valor = Integer.toString(cont);
			linhaMusicas.append(valor);
			linhas.add(linhaMusicas.toString());
		}

	

		TextFileApps.gravarArquivo(linhas, arquivoPlayList);
	}

}
