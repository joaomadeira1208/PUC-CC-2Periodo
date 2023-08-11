import mypackage.Arq;
import mypackage.MyIO;

public class Exercicio4 {
    public static void main(String[] args) {
        String nomeArquivo1 = MyIO.readLine("Digite o nome do primeiro arquivo: ");
        String nomeArquivo2 = MyIO.readLine("Digite o nome do segundo arquivo: ");
        String aux = "";
        
        Arq.openRead(nomeArquivo1);

        while(Arq.hasNext() == true) {
            aux += Arq.readLine();
            aux += "\n";
        }

        Arq.close();

        Arq.openWrite(nomeArquivo2);
        Arq.println(aux);
        Arq.close();
    }
}
