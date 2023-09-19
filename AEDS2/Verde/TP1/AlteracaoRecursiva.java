import java.util.Random;
public class AlteracaoRecursiva {
    
    // Método para comparar se duas strings são iguais
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

    // Método para converter uma string em um array de caracteres
    public static char[] stringToChar(String string) {
        char[] arrayChar = new char[string.length()];
        for(int i = 0; i < string.length(); i++) {
            arrayChar[i] = string.charAt(i);
        }
        return arrayChar;
    }

    // Método RECURSIVO para realizar a substituição de letras na string
    public static char[] letterShuffle(char[] arrayChar, int index, char letraMinuscula_1, char letraMinuscula_2) {
        if(index < arrayChar.length) {
            if(arrayChar[index] == letraMinuscula_1) {
                arrayChar[index] = letraMinuscula_2;
            }
            index++;
            return letterShuffle(arrayChar, index, letraMinuscula_1, letraMinuscula_2);
        }
        return arrayChar; 
    }

    //Main
    public static void main(String[] args) {
        String stringEntrada;
        Random gerador = new Random();
        gerador.setSeed(4);
        do {
            stringEntrada = MyIO.readLine();
            if(!equalStrings(stringEntrada, "FIM")) {
                char letraMinuscula_1 = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
				char letraMinuscula_2 = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
                char[] arrayChar = stringToChar(stringEntrada);
                arrayChar = letterShuffle(arrayChar,0, letraMinuscula_1, letraMinuscula_2);
                for(int i = 0; i < arrayChar.length; i++) {
                    MyIO.print(arrayChar[i]);
                }
                MyIO.println("");
            }
        } while(!equalStrings(stringEntrada, "FIM"));
    }
}