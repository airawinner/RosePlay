package PlayerLogica;

import java.util.ArrayList;

public class Album {
    private String titulo;
    private String FotoCapa;
    /*O objetivo é apenas consultar dados,então não teráuma arraylit de musicas */
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getFotoCapa() {
		return FotoCapa;
	}
	public void setFotoCapa(String fotoCapa) {
		FotoCapa = fotoCapa;
	}
	public Album(String titulo, String fotoCapa) {
		super();
		this.titulo = titulo;
		FotoCapa = fotoCapa;
	}
	
    
}
