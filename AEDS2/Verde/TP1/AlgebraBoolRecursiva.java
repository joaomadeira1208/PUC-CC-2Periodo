public class AlgebraBoolRecursiva {

    // Método para remover espaços e vírgulas de um array de caracteres.
    public static char[] removerEspacoVirgula(char[] arrayChar) {
        char[] novoArray = new char[arrayChar.length];
        int index = 0;
        for (int i = 0; i < arrayChar.length; i++) {
            if (arrayChar[i] != ' ' && arrayChar[i] != ',') {
                novoArray[index] = arrayChar[i];
                index++;
            }
        }
        return novoArray;
    }

    // Método para converter uma string em um array de caracteres.
    public static char[] stringToChar(String string) {
        char[] arrayChar = new char[string.length()];
        for (int i = 0; i < string.length(); i++) {
            arrayChar[i] = string.charAt(i);
        }
        return arrayChar;
    }

    // Método para extrair os valores das entradas.
    public static char[] valores(char[] arrayChar) {
        int n = arrayChar[0] - '0';
        char[] valores = new char[n];
        for (int i = 0; i < n; i++) {
            valores[i] = arrayChar[i + 1];
        }

        return valores;
    }

    // Método para substituir variáveis por seus valores binários em uma expressão booleana.
    public static char[] substituirEntradasPorBinarios(char[] arrayChar, char[] valores) {
        for(int i = 0; i < valores.length; i++) {
            for(int j = 0; j < arrayChar.length; j++) {
                if(arrayChar[j] >= 'A' && arrayChar[j] <= 'Z') {
                    char numeroBinario = valores[i];
                    for(int k = j; k < arrayChar.length; k++) {
                        if(arrayChar[k] == (char) (i + 65)) {
                            arrayChar[k] = numeroBinario;
                        }
                    }
                }
            }
        }
        
        return arrayChar;
    }

    // Método para extrair apenas a expressão booleana de um array de caracteres.
    public static char[] apenasExpressaoBooleana(char[] arrayChar) {
        int n = (int) arrayChar[0] - '0';
        int tamanho = arrayChar.length;
        char[] expressaoBooleana = new char[tamanho - (n + 1)];
        for(int i = 0; i < n + 1; i++) {
            for(int j = 0; j < tamanho - 1; j++) {
                arrayChar[j] = arrayChar[j+1];
            }
            tamanho--;
        }

        for(int i = 0; i < expressaoBooleana.length; i++) {
            expressaoBooleana[i] = arrayChar[i];
        }
        return expressaoBooleana;
    }

    // Método para calcular a operação booleana NOT.
    public static char booleanNot(char[] valores, int quantidadeValores) {
        char resp = '1';
        for(int i = 0; i < quantidadeValores; i++) {
            if(valores[i] == '1') {
                resp = '0';
                i = quantidadeValores;
            }
        }
        return resp;
    }

    // Método para calcular a operação booleana AND.
    public static char booleanAnd(char[] valores, int quantidadeValores) {
        char resp = '1';
        for(int i = 0; i < quantidadeValores; i++) {
            if(valores[i] == '0') {
                resp = '0';
                i = quantidadeValores;
            }
        }
        return resp;
    }
    
    // Método para calcular a operação booleana OR.
    public static char booleanOr(char[] valores, int quantidadeValores) {
        char resp = '0';
        for(int i = 0; i < quantidadeValores; i++) {
            if(valores[i] == '1') {
                resp = '1';
                i = quantidadeValores;
            }
        }
        return resp;
    }

    
    /*  Método que avalia uma expressão booleana representada por um array de caracteres arrayChar e retorna o resultado da avaliação como um único caractere. 
    A avaliação é realizada de forma recursiva, com o parâmetro index indicando a posição atual no array durante o processo de avaliação. A avaliação pode ser 
    resumida a percorrer o array de caracteres procurando um ')', após encontrar a função percorre de volta a procura de um '(', assim encontrando uma operação. 
    Após encontrar uma operação, ela verifica se essa operação está sujeita a operação booleana NOT, AND ou OR e encontra a solução booleana a partir da função correspondente
    */
    public static char avaliacaoBooleana(char[] arrayChar, int index) {
        char[] valores = new char[20];
        int quantidadeValores = 0;
        if(index < arrayChar.length) {
            if(arrayChar[index] == ')') {
                int j = index;
                while(j >= 0 && arrayChar[j] != '(') {
                    j--;
                }
                if(j >= 0) {
                    if(arrayChar[j] == '(') {
                        if(arrayChar[j - 1] == 't') { // Se for not
                            valores[quantidadeValores] = arrayChar[j+1];
                            quantidadeValores++;
                            char resp = booleanNot(valores, quantidadeValores);
                            arrayChar[j - 3] = resp;
                            for(int k = 0; k < 4 + quantidadeValores; k++) {
                                for(int x = j - 2; x < arrayChar.length - 2; x++) {
                                    arrayChar[x] = arrayChar[x + 1];
                                }
                            }
                            index = 0;
                        }

                        if(arrayChar[j - 1] == 'd') { // Se for and
                            for(int y = j + 1; y < index; y++) {
                                valores[quantidadeValores] = arrayChar[y];
                                quantidadeValores++;
                            }
                            char resp = booleanAnd(valores, quantidadeValores);
                            arrayChar[j - 3] = resp;
                            for(int k = 0; k < 4 + quantidadeValores; k++) {
                                for(int x = j - 2; x < arrayChar.length - 2; x++) {
                                    arrayChar[x] = arrayChar[x+1];
                                }
                            }
                            index = 0;
                        }

                        if(arrayChar[j - 1] == 'r') { // Se for or
                            for(int y = j + 1; y < index; y++) {
                                valores[quantidadeValores] = arrayChar[y];
                                quantidadeValores++;
                            }
                            char resp = booleanOr(valores, quantidadeValores);
                            arrayChar[j - 2] = resp;
                            for(int k = 0; k < 3 + quantidadeValores; k++) {
                                for(int x = j - 1; x < arrayChar.length - 2; x++) {
                                    arrayChar[x] = arrayChar[x+1];
                                }
                            }
                            index = 0;
                        }
                    }
                }
            }
            else {
                index++;
            }
            
            return avaliacaoBooleana(arrayChar, index);
        }
        return arrayChar[0];
    }

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


    // Main
    public static void main(String[] args) {
        String string;
        do {
            string = MyIO.readLine();
            if(!equalStrings(string, "0")) {
                char[] arrayChar = stringToChar(string);
                arrayChar = removerEspacoVirgula(arrayChar);
                char[] valores = valores(arrayChar);
                arrayChar = substituirEntradasPorBinarios(arrayChar, valores);
                arrayChar = apenasExpressaoBooleana(arrayChar);
                char resp = avaliacaoBooleana(arrayChar,0);
                MyIO.println(resp);
            }
        } while(!equalStrings(string, "0"));
    }
}