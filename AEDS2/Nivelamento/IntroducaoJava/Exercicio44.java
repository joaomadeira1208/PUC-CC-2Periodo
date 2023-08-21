import mypackage.MyIO;

public class Exercicio44 {
    public static void main(String[] args) {
        String palavra = MyIO.readString("Digite a palavra: ");

        for(int i = 0; i < palavra.length(); i++) {
            if(palavra.charAt(i) == 'A' || palavra.charAt(i) == 'a') {
                MyIO.println("A letra A aparece pela primeira vez em " + palavra + " na " + (i+1) + " letra da palavra.");
                i = palavra.length();
            }
        }
    }
}