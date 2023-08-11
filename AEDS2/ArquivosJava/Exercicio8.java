import mypackage.Arq;
import mypackage.MyIO;

public class Exercicio8 {
    public static String cifraCesar(String str, int deslocamento) {
        String cifrado = "";
        for(int i = 0; i < str.length() - 1; i++) {
            char caractere = str.charAt(i);
            if(Character.isLetter(caractere)) {
                char base = Character.isLowerCase(caractere) ? 'a' : 'A';
                char charCriptado = (char) (base + (caractere - base - deslocamento) % 26);
                cifrado += charCriptado;
            }
            else {
                cifrado += caractere;
            }
        }

        return cifrado;
    }
    
    
    
    public static void main(String[] args) {
        String nomeArquivo = MyIO.readLine("Digite o nome do arquivo que voce deseja: ");
        String str = "";
        Arq.openRead(nomeArquivo);

        while(Arq.hasNext() == true) {
            str += Arq.readLine();
            str += "\n";
        }

        Arq.close();

        str = cifraCesar(str, 3);

        System.out.println(str);

        
    }    
}
