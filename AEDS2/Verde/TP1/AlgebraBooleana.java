public class AlgebraBooleana {
    
    // Método para remover espaços e vírgulas de um array de caracteres.
    public static char[] removerEspaco(char[] arrayChar) {
        char[] arrayFinal = new char[arrayChar.length];
        int tamanho = 0;
        for(int i = 0; i < arrayChar.length; i++) {
            if((arrayChar[i] != ' ') && (arrayChar[i] != ',')) {
                arrayFinal[tamanho] = arrayChar[i];
                tamanho++;
            }
        }
        return arrayFinal;
    }

    // Método para substituir variáveis por seus valores binários em uma expressão booleana.
    public static char[] substituirEntradasPorBinarios(char[] arrayChar, char[] valores) {
        for(int k = 0; k < valores.length; k++) {
            for (int i = 0; i < arrayChar.length; i++) {
                if (arrayChar[i] >= 'A' && arrayChar[i] <= 'Z') {
                    char valorBinario = valores[k]; // Obtém o valor binário correspondente
    
                    // Substitui a variável pelo valor binário correspondente
                    for (int j = i; j < arrayChar.length; j++) {
                        if ((int) arrayChar[j] == k + 65) {
                            arrayChar[j] = valorBinario;
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
        int tamanhoExpressao = arrayChar.length - (n + 1);
        int indexInicio = n + 1;
        char[] expressaoBooleana = new char[tamanhoExpressao];
        for(int i = 0; i < expressaoBooleana.length; i++) {
            expressaoBooleana[i] = arrayChar[indexInicio];
            indexInicio++; 
        }
        return expressaoBooleana;
    }
    
    

        
    // Método para converter uma string em um array de caracteres.
    public static char[] stringToChar(String string) {
        char[] arrayChar = new char[string.length()];
        for(int i = 0; i < string.length(); i++) {
            arrayChar[i] = string.charAt(i);
        }
        return arrayChar;
    }
    
    
    // Método para extrair os valores das entradas.
    public static char[] Valores(String string) {
        int n = string.charAt(0) - '0';
        char[] valoresEntradas = new char[n];
        for(int i = 0, j = 0; i < n; i++, j = j + 2) {
            valoresEntradas[i] = (string.charAt(j + 2));
        }
        return valoresEntradas;
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
    
    /*  Método para avaliar uma expressão booleana. Este método percorre o array de caracteres procurando um ')', após ela volta no array procurando pelo '(' correspondente.
        Após encontrar uma operação, ela verifica se essa operação está sujeita a operação booleana NOT, AND ou OR e encontra a solução booleana a partir da função correspondente.
        Ela substitui a operação e os valores dentro dos parênteses pela solução da operação e repete esse processo até que não haja mais operações dentro dos parênteses.
    */
    public static char avaliacaoBooleana(char[] arrayChar) {
        char respDeCadaExpressao;
        char[] valores = new char[20];
        int quantidadeValores = 0;
        int i = 0;
    
        while (i < arrayChar.length) {
            if (arrayChar[i] == ')') {
                int j = i;
                while (j >= 0 && arrayChar[j] != '(') {
                    j--;
                }
    
                if (j >= 0) {
                    if (j - 1 >= 0 && arrayChar[j - 1] == 't') { // Se for not
                        valores[quantidadeValores] = arrayChar[j + 1];
                        quantidadeValores++;
                        respDeCadaExpressao = booleanNot(valores, quantidadeValores);
                        arrayChar[j - 3] = respDeCadaExpressao;
    
                        for (int k = j - 2; k < arrayChar.length - quantidadeValores - 4; k++) {
                            arrayChar[k] = arrayChar[k + quantidadeValores + 4];
                        }
                        i = 0;
                        quantidadeValores = 0;
                    } else if (j - 1 >= 0 && arrayChar[j - 1] == 'd') { // Se for and
                        int k = j + 1;
                        while (k < i) {
                            valores[quantidadeValores] = arrayChar[k];
                            quantidadeValores++;
                            k++;
                        }
                        respDeCadaExpressao = booleanAnd(valores, quantidadeValores);
                        arrayChar[j - 3] = respDeCadaExpressao;
    
                        for (int x = j - 2; x < arrayChar.length - quantidadeValores - 4; x++) {
                            arrayChar[x] = arrayChar[x + quantidadeValores + 4];
                        }
                        i = 0;
                        quantidadeValores = 0;
                    } else if (j - 1 >= 0 && arrayChar[j - 1] == 'r') { // Se for or
                        int k = j + 1;
                        while (k < i) {
                            valores[quantidadeValores] = arrayChar[k];
                            quantidadeValores++;
                            k++;
                        }
                        respDeCadaExpressao = booleanOr(valores, quantidadeValores);
                        arrayChar[j - 2] = respDeCadaExpressao;
    
                        for (int x = j - 1; x < arrayChar.length - quantidadeValores - 3; x++) {
                            arrayChar[x] = arrayChar[x + quantidadeValores + 3];
                        }
                        i = 0;
                        quantidadeValores = 0;
                    }
                }
            }
            i++;
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
                char[] valoresEntradas = Valores(string);
                char[] arrayChar = removerEspaco(stringToChar(string));
                char[] arrayEntradasSubstituidas = substituirEntradasPorBinarios(arrayChar, valoresEntradas);
                char[] expressaoBooleana = apenasExpressaoBooleana(arrayEntradasSubstituidas);
                MyIO.println(avaliacaoBooleana(expressaoBooleana));
            }
        }while(!equalStrings(string, "0"));
    }
}