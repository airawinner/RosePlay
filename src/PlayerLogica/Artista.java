package PlayerLogica;

public class Artista {
 private String nome;
 private String Foto;
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getFoto() {
	return Foto;
}
public void setFoto(String foto) {
	Foto = foto;
}
public Artista(String nome, String foto) {
	super();
	this.nome = nome;
	Foto = foto;
}
public Artista() {

}
 
}
