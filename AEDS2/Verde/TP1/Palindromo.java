public class Palindromo {
    
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
    
    // Método para verificar se uma string é um palindromo ou não
    public static boolean isPalindromo(String str) {
        for(int i = 0; i < str.length() / 2; i++) {
            if(str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    // Main
    public static void main(String[] args) {
        String str;
        do {
            str = MyIO.readLine();
            if(!equalStrings(str, "FIM")) {
                if(isPalindromo(str)) {
                    MyIO.println("SIM");
                }
                else {
                    MyIO.println("NAO");
                }
            }
        }while(!equalStrings(str, "FIM"));
    }

}