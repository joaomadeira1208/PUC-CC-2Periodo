import java.util.Random;

public class AlteracaoIterativa {
    
    // Função para comparar se duas strings são iguais
    public static boolean equalStrings(String str_1, String str_2) {
        if(str_1.length() != str_2.length()) {
            return false;
        }
        for(int i = 0; i < str_1.length(); i++) {
            if(str_1.charAt(i) != str_2.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    
    // Função para converter uma string em um array de caracteres
    public static char[] stringToCharArray(String string) {
        char[] arrayChar = new char[string.length()];
        for(int i = 0; i < string.length(); i++) {
            arrayChar[i] = string.charAt(i);
        }
        return arrayChar;
    } 
    
    
    
    // Função que realiza a substituição de letras na string
    public static String letterShuffle(String string, Random gerador) {
        char letraMinuscula_1 = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
        char letraMinuscula_2 = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
        char[] aux = stringToCharArray(string);

        for(int i = 0; i < string.length(); i++) {
            if(string.charAt(i) == letraMinuscula_1) {
                aux[i] = letraMinuscula_2;
            }
        }

        return new String(aux);
    }
    
    
    public static void main(String[] args) {
        String stringEntrada;
        Random gerador = new Random();
        gerador.setSeed(4);
        do {
            stringEntrada = MyIO.readLine();
            if(!equalStrings(stringEntrada, "FIM")) {
                String stringSaida = letterShuffle(stringEntrada, gerador);
                MyIO.println(stringSaida);
            }
        } while(!equalStrings(stringEntrada, "FIM"));
    }
}
