import mypackage.MyIO;
import java.util.Random;

public class AlteracaoRecursiva {
    
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
    public static void main(String[] args) {
        
    }
}