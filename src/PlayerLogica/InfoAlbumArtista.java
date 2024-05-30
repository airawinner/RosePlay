package PlayerLogica;

import java.util.ArrayList;
import java.util.List;

public class InfoAlbumArtista {
 
	private  List<Artista> Artistas= new ArrayList<>();
	private  List<Album> Albuns= new ArrayList<>();
	private TratamentoDadosTexto tratamento= new TratamentoDadosTexto();
	
	public List<Artista> getArtistas() {
		return Artistas;
	}
	public void setArtistas(List<Artista> artistas) {
		Artistas = artistas;
	}
	public List<Album> getAlbuns() {
		return Albuns;
	}
	public void setAlbuns(List<Album> albuns) {
		Albuns = albuns;
	}
	public InfoAlbumArtista(List<Artista> artistas, List<Album> albuns) {
		super();
		Artistas = artistas;
		Albuns = albuns;
	}
	public InfoAlbumArtista() {
	 this.Albuns= tratamento.lerAlbuns();
	 this.Artistas=tratamento.lerArtistas();
	 
	}
	
	
	
	  
  
}
