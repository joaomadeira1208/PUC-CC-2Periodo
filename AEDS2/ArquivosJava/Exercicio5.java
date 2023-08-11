import mypackage.Arq;
import mypackage.MyIO;

public class Exercicio5 {
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
        String copiaMaiuscula = aux.toUpperCase();

        Arq.openWrite(nomeArquivo2);
        Arq.println(copiaMaiuscula);
        Arq.close();
        
    }
}
