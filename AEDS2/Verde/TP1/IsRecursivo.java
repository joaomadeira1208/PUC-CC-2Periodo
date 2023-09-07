public class IsRecursivo {

     // Método para comparar duas strings e verificar se são iguais.
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

    // Método RECURSIVO para verificar se uma string contém apenas vogais. Caso encontre qualquer caractere diferente das vogais retorna falso.
    public static boolean isVogais(String string, int index) {
        if(index < string.length()) {
            if(string.charAt(index) != 'A' && string.charAt(index) != 'E' && string.charAt(index) != 'I' && string.charAt(index) != 'O' && string.charAt(index) != 'U' && string.charAt(index) != 'a' && string.charAt(index) != 'e' && string.charAt(index) != 'i' && string.charAt(index) != 'o' && string.charAt(index) != 'u') {
                return false;
            }
            index++;
            return isVogais(string, index);
        }
        return true;
    }

    // Método RECURSIVO para verificar se uma string contém apenas consoantes. Caso encontre um caractere que não é uma letra ou uma letra que é uma vogal retorna falso.
    public static boolean isConsoantes(String string, int index) {
        if(index < string.length()) {
            if((string.charAt(index) >= 'A' && string.charAt(index) <= 'Z') || (string.charAt(index) >= 'a' && string.charAt(index) <= 'z')) {
                if(string.charAt(index) == 'A' || string.charAt(index) == 'E' || string.charAt(index) == 'I' || string.charAt(index) == 'O' || string.charAt(index) == 'U' || string.charAt(index) == 'a' || string.charAt(index) == 'e' || string.charAt(index) == 'i' || string.charAt(index) == 'o' || string.charAt(index) == 'u') {
                    return false;
                }
            }
            else {
                return false;
            }
            index++;
            return isConsoantes(string, index);
        }
        return true;
    }
    
    // Método RECURSIVO para verificar se uma string corresponde a um número inteiro. Caso encontre um caractere que não é um algarismo retorna falso.
    public static boolean isInteiro(String string, int index) {
        if(index < string.length()) {
            if(string.charAt(index) < '0' || string.charAt(index) > '9') {
                return false;
            }
            index++;
            return isInteiro(string, index);
        }

        return true;
    }

    /*  Método RECURSIVO para verificar se uma string corresponde a um número real. Caso encontre um caractere que não é um algarismo, um ponto final ou uma virgula retorna falso. Porém, caso tenha mais de um ponto
    ou virgula retorna falso. Além disso, caso a string for igual "." ou "," retorna falso.
    */
    public static boolean isReal(String string, int index, int contadorPontosVirgulas) {
        if(equalStrings(string, ".") || equalStrings(string, ",")) {
            return false;
        }
        if(index < string.length()) {
            if(string.charAt(index) == '.' || string.charAt(index) == ',') {
                contadorPontosVirgulas++;
                if(contadorPontosVirgulas > 1) {
                    return false;
                }
            }
            else if(string.charAt(index) < '0' || string.charAt(index) > '9') {
                return false;
            }
            index++;
            return isReal(string, index, contadorPontosVirgulas);
        }
        
        return true;
    }
    
    // Main
    public static void main(String[] args) {
        String string;
        do {
            string = MyIO.readLine();
            if(!equalStrings(string, "FIM")) {
                if(isVogais(string, 0)) {
                    MyIO.print("SIM ");
                }
                else {
                    MyIO.print("NAO ");
                }

                if(isConsoantes(string, 0)) {
                    MyIO.print("SIM ");
                }
                else {
                    MyIO.print("NAO ");
                }

                if(isInteiro(string, 0)) {
                    MyIO.print("SIM ");
                }
                else {
                    MyIO.print("NAO ");
                }

                if(isReal(string, 0, 0)) {
                    MyIO.println("SIM");
                }
                else {
                    MyIO.println("NAO");
                }
            }
        }while(!equalStrings(string, "FIM"));
    }
}