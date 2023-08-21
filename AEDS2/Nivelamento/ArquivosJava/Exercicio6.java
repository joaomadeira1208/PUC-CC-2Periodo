import mypackage.Arq;
import mypackage.MyIO;

public class Exercicio6 {
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
        String auxReverso = "";
        for(int i = aux.length() - 1; i >= 0; i--) {
            auxReverso += aux.charAt(i);
        }

        Arq.openWrite(nomeArquivo2);
        Arq.println(auxReverso);
        Arq.close();
    }
}
