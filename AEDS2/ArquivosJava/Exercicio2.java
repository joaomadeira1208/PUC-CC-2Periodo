import mypackage.Arq;
import mypackage.MyIO;

 public class Exercicio2 {
    public static void main(String[] args) {
        String nomeArquivo = MyIO.readLine("Digite o nome do arquivo que voce deseja ler: ");
        String str = "";
        Arq.openRead(nomeArquivo);
        while(Arq.hasNext() == true) {
            str += Arq.readLine();
            str += "\n";
        }

        System.out.println(str);
    }   
}
