
package PlayerLogica;
/**
 * alguns métodos dessa classe foram sobrecarregados para retornar dados diferentes do objeto múica principalmente
 * já que nem sempre é possível acessar diretamente o getters e setters do objeto música.
 * Ela é filha de MediTocar, basicamente ela está trabalhando com arraylists das playlists criadas pelo usuário 
 * e as músicas do arquivo texto, principalmente com o metodos de próxima música e anterior**/
import java.util.ArrayList;
import java.util.List;

import javafx.scene.media.MediaPlayer;

import java.util.Collections;
public class TocasVariasMusicas extends MediaTocar{
	 private TratamentoDadosTexto reproduzir=new  TratamentoDadosTexto();
	 private static  List<Musica> TodasMusicas= new ArrayList<>();
	 private  List<PlayList> Playlists= reproduzir.lerPlayListsDoArquivo();
	 private int indiceAtual;
	 
	
	public Musica ProcurarEmtodasMusicas(String nome) {
		for(Musica musica: TodasMusicas) {
			if(musica.getTitulo().equals(nome)) {
	
			  return musica;
			}
		}
	 return null;
	}


	 public void AdicionarPlaylist(PlayList playlist) {
			Playlists.add(playlist);
			 
	  }
	 public PlayList procurarPlaylist(String nome) {
		 for(PlayList list: Playlists ) {
			 if(list.getNome().equals(nome)){
				 return list;
			 }		 
		 }
		 return null;
	  }
	 public void AlterarPlaylist(String novoNome, String capa,String antigonome) {
		 for(PlayList list: Playlists ) {
			 if(list.getCover().equals(capa) && list.getNome().equals(antigonome)){				
				 list.setNome(novoNome);
		
			 }		 
		 }
		
	  }

	 public void ajustarnoFilho(double tempo) {
		 super.ajustarTempo(tempo);
	 }
	 public String duracaoMusica(Musica musica) {
		 for(Musica musicasTotais: TodasMusicas){
			  if(musica.getTitulo().equals(musicasTotais.getTitulo())) {
				  return musica.converter();
			  }
		 }
		 return null;
	 }
	 public String duracaoMusica(Musica musica, double tempo) {
		 for(Musica musicasTotais: TodasMusicas){
			  if(musica.getTitulo().equals(musicasTotais.getTitulo())) {
				  return musica.converter(tempo);
			  }
		 }
		 return null;
	 }
	 
	 public TocasVariasMusicas() {
		TodasMusicas = reproduzir.lerMusicasDoArquivo();
	}

	 
	public  List<Musica> getTodasMusicas() {
		return TodasMusicas;
	}


	public void setTodasMusicas(List<Musica> todasMusicas) {
		TodasMusicas = todasMusicas;
	}


	public List<PlayList> getPlaylists() {
		return Playlists;
	}


	public void setPlaylists(List<PlayList> playlists) {
		Playlists = playlists;
	}


	public int getIndiceAtual() {
		return indiceAtual;
	 }

    	public void setIndiceAtual(int indiceAtual) {
		this.indiceAtual = indiceAtual;
	 }

 
 	
	public void AdicionarMusicaAtual(Musica musica) {
		if(musica!=null) {
		super.setMusica(musica.getMusicaMp3());
		}
	}
	
	public String AcessarMusicaAtualMae() {
		return super.getMusica();
	}
	
	public int indiceMusicaAtual(String musicaAtual) {
    this.indiceAtual=0;
  	for(Musica musicas: TodasMusicas) {
			indiceAtual++;
			if(musicas.getMusicaMp3().equals(musicaAtual)){
			
				return indiceAtual;
			}
		}
		
		return 0;
	}

	public String ProximaMusica(int atual) {
		int proxima=atual+1;
		int aux=0;
		for(Musica musicas: TodasMusicas) {	
		aux++;
               
		  if(proxima==aux) {
		  
             play(musicas.getMusicaMp3());
             return musicas.getMusicaMp3();
	      } 
		}
		return null;
	}


    public String MusicaAnterior(int atual) {

		int anterior=atual-1;
		int aux=0;
		for(Musica musicas: TodasMusicas) {	
		aux++;
               
		  if(anterior==aux) {
		  
             play(musicas.getMusicaMp3());
             return musicas.getMusicaMp3();
	      } 
		}
		return null;	
    }
    
    public String MusicaInicialString() {
    	
    	for(Musica musica: TodasMusicas) {
    	 super.setMusica(musica.getMusicaMp3());
    	  AdicionarMusicaAtual(musica);
    	
    	  return musica.getMusicaMp3();
    	 }
    	return null;
     }
     
   public  String procurarMusica(String Nome) {
    	for(PlayList playlist : Playlists){
    		return playlist.procurarMusica(Nome);
    	}
     return null;
    }
    public String procurarMusicaFora(String titulo) {
	      for (Musica musica : TodasMusicas) {
	         if (musica.getTitulo().equals(titulo)) {
	            return musica.getMusicaMp3();
	         }
	      }
	      return null;
	   }
	  public String MusicaAtualSTring(Musica Musica) {
    	
  	   for(Musica musica: TodasMusicas) {
  		   if(musica.getTitulo().equals(Musica.getTitulo())) {
  			  AdicionarMusicaAtual(musica);
  	    	  return musica.getMusicaMp3();
  	    	 }   
        }
  	   return null;
       }
     
     public Musica MusicaAtual(String nomeMp3) {
    	 
  	   for(Musica musica: TodasMusicas) {
  		   if(musica.getMusicaMp3().equals(nomeMp3)) {
  			  AdicionarMusicaAtual(musica);
  	    	  return musica;
  	    	 }   
        }
  	   return null;
       }
	

  
}
