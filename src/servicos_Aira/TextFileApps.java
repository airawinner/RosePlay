package servicos_Aira;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;


public class TextFileApps {
public static Scanner leitor;
    public static ArrayList<String> AbrirArquivoparaLeitura(String arquivo) {
        ArrayList<String> retorno = new ArrayList<>();
        try (Scanner leitor = new Scanner(Paths.get(arquivo))) {
        	  while (leitor.hasNextLine()) {
                  String linha = leitor.nextLine();
                  retorno.add(linha);
              }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo.");
            System.exit(1);
        }
        return retorno;
    }

    public static void fecharArquivoParaLeitura() {
        if (leitor != null) {
            leitor.close();
        }
    }
    public static void gravarArquivo(List<String> linhas, String arquivo) {
        try {
        	Path path = Paths.get(arquivo);
            Files.write(path, linhas);
            System.out.println("Arquivo gravado com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao gravar o arquivo.");
            e.printStackTrace();
        }
    }
}
