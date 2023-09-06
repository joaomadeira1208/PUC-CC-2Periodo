import mypackage.MyIO;

public class AlgebraBoolRecursiva {

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

    public static char[] stringToChar(String string) {
        char[] arrayChar = new char[string.length()];
        for (int i = 0; i < string.length(); i++) {
            arrayChar[i] = string.charAt(i);
        }
        return arrayChar;
    }

    public static char[] valores(char[] arrayChar) {
        int n = arrayChar[0] - '0';
        char[] valores = new char[n];
        for (int i = 0; i < n; i++) {
            valores[i] = arrayChar[i + 1];
        }

        return valores;
    }

    public static char[] substituirEntradasPorBinarios(char[] arrayChar, char[] valores) {
        for (int i = 0; i < valores.length; i++) {
            for (int j = 0; j < arrayChar.length; j++) {
                if (arrayChar[j] >= 'A' && arrayChar[j] <= 'Z') {
                    char valorBinario = valores[i];

                    for (int k = j; k < arrayChar.length; k++) {
                        if ((int) arrayChar[k] == 65 + i) {
                            arrayChar[k] = valorBinario;
                        }
                    }
                }
            }
        }
        return arrayChar;
    }

    public static void main(String[] args) {
        String string = MyIO.readLine();
        char[] arrayChar = stringToChar(string);
        arrayChar = removerEspacoVirgula(arrayChar);
        char[] valores = valores(arrayChar);
    }
}