import mypackage.MyIO;

public class Exercicio43 {
    public static void main(String[] args) {
        String palavra = MyIO.readString("Digite a palavra: ");
        int caracteres = palavra.length();
        int contador_maiusculas = 0;

        for(int i = 0; i < palavra.length(); i++) {
            if(palavra.charAt(i) > 'A' - 1 && palavra.charAt(i) < 'Z' + 1 ) {
                contador_maiusculas++;
            }
        }

        MyIO.println("Caracteres = " + caracteres);
        MyIO.println("Caracteres maiuscula = " + contador_maiusculas);
    }
}