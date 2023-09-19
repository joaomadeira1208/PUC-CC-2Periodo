public class CiframentoRecursivo {
    
    // Método para comparar duas strings e verificar se são iguais
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
    public static char[] charToString(String entradaString) {
        char[] arrayChar = new char[entradaString.length()];
        for(int i = 0; i < entradaString.length(); i++) {
            arrayChar[i] = entradaString.charAt(i);
        }
        return arrayChar;
    }

    // Método para realizar o ciframento de César em um array de caracteres de forma RECURSIVA.
    public static char[] ciframentoCesar(char[] arrayChar, int index) {
        if(index < arrayChar.length) {
            arrayChar[index] = (char) (arrayChar[index] + 3);
            index++;
            arrayChar = ciframentoCesar(arrayChar, index); // Chamada Recursiva.
        }

        return arrayChar;
        
    }

    //Método para escrever um array de caracteres na tela.
    public static void escreverArrayChar(char[] arrayChar) {
        for(int i = 0; i < arrayChar.length; i++) {
            MyIO.print(arrayChar[i]);
        }
    }
    
    // Main
    public static void main(String[] args) {
        String entradaString;
        do {
            entradaString = MyIO.readLine();
            if(!equalStrings(entradaString, "FIM")) {
                char[] arrayChar = charToString(entradaString);
                arrayChar = ciframentoCesar(arrayChar, 0);
                escreverArrayChar(arrayChar);
                MyIO.println("");
            }
        }while(!equalStrings(entradaString, "FIM"));
        
    }
}