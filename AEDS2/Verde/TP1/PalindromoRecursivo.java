public class PalindromoRecursivo {
    // Método para verificar se duas strings são iguais.
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


    // Método para verificar se uma string é um palindromo
    public static boolean isPalindromo(String string, int index) {
        boolean resp = true;

        if(index < string.length()) {
            if(string.charAt(index) != string.charAt(string.length() - 1 - index)) {
                resp = false;
            }
            else {
                index++; 
                resp = isPalindromo(string, index); // Chamada recursiva com o próprio indice
            }
        }
        return resp;

    }

    // Main
    public static void main(String[] args) {
        String string;
        do {
            string = MyIO.readLine();
            if(!equalStrings(string, "FIM")) {
                int index = 0;
                if(isPalindromo(string, index)) {
                    MyIO.println("SIM");
                }
                else {
                    MyIO.println("NAO");
                }
            }
        }while(!equalStrings(string, "FIM"));
    }
}
