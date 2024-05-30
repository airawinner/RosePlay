package PlayerLogica;

import java.util.ArrayList;
import java.util.List;

public class PlayList {
   private String nome;
   private String cover;
   private List<Musica> musicas;
   
   public PlayList(String nome) {
      this.nome = nome;
      this.musicas = new ArrayList<>();
   }
   
   public PlayList() {
      this.nome = "Altere o nome";
      this.cover = "/img/ICONEMUSIC.png";
      this.musicas = new ArrayList<>();
   }
   
   public void setMusicas(List<Musica> musicas) {
      this.musicas = musicas;
   }
   
   public void AdicionarMusica(Musica musica) {
      musicas.add(musica); 
   }
   
   public String getNome() {
      return nome;
   }
   
   public void setNome(String nome) {
      this.nome = nome;
   }
   
   public List<Musica> getMusicas() {
      return musicas;
   }
   
   public String getCover() {
      return cover;
   }
   
   public void setCover(String cover) {
      this.cover = cover;
   }
   /**procurando o objeto musica atráves do label de título para poder tocar p mp3 e também atuazar as informações na tela**/
   public Musica procurarMusicaNome(String nome) {
      for (Musica musica : musicas) {
         if (musica.getTitulo().equals(nome)) {
            return musica;
         }
      }
      return null;
   }


   /**esse só retorna o mp3, facilita para não ter que instanciar um novo objeto e fazer atribuição**/
   public String procurarMusica(String titulo) {
      for (Musica musica : musicas) {
         if (musica.getTitulo().equals(titulo)) {
            return musica.getMusicaMp3();
         }
      }
      return null;
   }
 
}
