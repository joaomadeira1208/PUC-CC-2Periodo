public class Is {
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

    // Método para verificar se uma string contém apenas consoantes.
    public static boolean isConsoantes(String string) {
        boolean resp = true;
        for(int i = 0; i < string.length(); i++) {
            if((string.charAt(i) >= 'A' && string.charAt(i) <= 'Z') || (string.charAt(i) >= 'a' && string.charAt(i) <= 'z')) {
                if(string.charAt(i) == 'A' || string.charAt(i) == 'E' || string.charAt(i) == 'I' || string.charAt(i) == 'O' || string.charAt(i) == 'U' || string.charAt(i) == 'a' || string.charAt(i) == 'e' || string.charAt(i) == 'i' || string.charAt(i) == 'o' || string.charAt(i) == 'u') { // Achou uma vogal
                    resp = false;
                    i = string.length();
                }
            }
            else { // Caractere não é letra
                resp = false; 
                i = string.length();
            }
        }
        
        return resp;
    }
    // Método para verificar se uma string contém apenas vogais.
    public static boolean isVogais(String string) {
        boolean resp;
        resp = true;
        for(int i = 0; i < string.length(); i++) {
            if(string.charAt(i) == 'A' || string.charAt(i) == 'E' || string.charAt(i) == 'I' || string.charAt(i) == 'O' || string.charAt(i) == 'U' || string.charAt(i) == 'a' || string.charAt(i) == 'e' || string.charAt(i) == 'i' || string.charAt(i) == 'o' || string.charAt(i) == 'u') {
            }
            else { // Achou um caractere que não é uma vogal.
                resp = false;
                i = string.length();
            }
            
        }
        return resp;
    }

    // Método para verificar se uma string corresponde a um número inteiro.
    public static boolean isInteiro(String string) {
        boolean resp = true;
        for(int i = 0; i < string.length(); i++) {
            if(string.charAt(i) < '0' || string.charAt(i) > '9') { // Encontrou algum caractere que não é um número.
                resp = false;
                i = string.length();
            }
        }
        return resp;
    }

    // Método para verificar se uma string corresponde a um número real
    public static boolean isReal(String string) {
        int contador = 0;
        boolean resp = true;
        if(!equalStrings(string, ",") && !equalStrings(string, ".")) {
            for(int i = 0; i < string.length(); i++) {
                if(string.charAt(i) == ',' || string.charAt(i) == '.') {
                    contador++;
                    if(contador > 1) { // Verificação se existe mais de um '.' ou ',' na string.
                        resp = false;
                        i = string.length();
                    }
                }
            
                else if(string.charAt(i) < '0' || string.charAt(i) >'9') { // Encontrou algum caractere que não é um número, um '.' ou uma ','.
                    resp = false;
                    i = string.length();
                }
            }
        }
        else { // String = "." ou ",".
            resp = false;
        }

        return resp;
    }


    
    public static void main(String[] args) {
        String string;
        do {
            string = MyIO.readLine();
            if(!equalStrings(string, "FIM")) {
                if(isVogais(string)) {
                    MyIO.print("SIM ");
                }
                else {
                    MyIO.print("NAO ");
                }

                if(isConsoantes(string)) {
                    MyIO.print("SIM ");
                }
                else {
                    MyIO.print("NAO ");
                }

                if(isInteiro(string)) {
                    MyIO.print("SIM ");
                }
                else {
                    MyIO.print("NAO ");
                }

                if(isReal(string)) {
                    MyIO.print("SIM");
                }
                else {
                    MyIO.print("NAO");
                }
                MyIO.println("");
            }
        } while(!equalStrings(string, "FIM"));
    }
}