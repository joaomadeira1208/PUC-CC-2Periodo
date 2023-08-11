import mypackage.Arq;
import mypackage.MyIO;

 public class Exercicio3 {
    public static void main(String[] args) {
        String nomeArquivo = MyIO.readLine("Digite o nome do arquivo que voce deseja ler: ");
        String str = "";
        Arq.openRead(nomeArquivo);
        while(Arq.hasNext() == true) {
            str += Arq.readLine();
            str += "\n";
        }

        String strMaiuscula = str.toUpperCase();
        System.out.println(strMaiuscula);
    }   
}
