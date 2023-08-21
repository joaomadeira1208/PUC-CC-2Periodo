import mypackage.MyIO;

public class Exercicio45 {
    public static void main(String[] args) {
        String palavra = MyIO.readString("Digite a palavra: ");
        int caracteres = palavra.length();
        int vogais = 0;
        int letras = 0;
        int consoantes = 0;
        int nao_letras = 0;

        for(int i = 0; i < caracteres; i++) {
            char aux = Character.toUpperCase(palavra.charAt(i));
            if(aux == 'A' || aux == 'E' || aux == 'I' || aux == 'O' || aux == 'U') {
                vogais++;
            }
            else if(aux >= 'A' && aux <= 'Z'){
                consoantes++;
            }
            else {
                nao_letras++;
            }
        }
        letras = vogais + consoantes;

        MyIO.println("Letras = " + letras);
        MyIO.println("Nao letras = " + nao_letras);
        MyIO.println("Vogais = " + vogais);
        MyIO.println("Consoantes = " + consoantes);
    }
}