import mypackage.MyIO;

public class Exercicio39 {
    public static void main(String[] args) {
        String string = MyIO.readString("Digite a String: ");
        char caractere = MyIO.readChar("Digite o caractere: ");
        int tamanho = string.length();
        int contC = 0;
        for(int i = 0; i < tamanho; i++){
            if(string.charAt(i) == caractere){
                contC++;
            }
        }
        MyIO.println(contC);
    }
}
