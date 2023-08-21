import mypackage.Arq;
import mypackage.MyIO;

public class Exercicio1 {
    public static void main(String[] args) {
        String nomeArquivo = MyIO.readString("Digite o nome do arquivo: ");
        String fraseArquivo = MyIO.readLine("Digite a frase para ser armazenada no arquivo: ");
        Arq.openWrite(nomeArquivo + ".txt");
        Arq.println(fraseArquivo);
        Arq.close();


    }
}
