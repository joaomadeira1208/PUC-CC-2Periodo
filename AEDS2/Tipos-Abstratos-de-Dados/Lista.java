import mypackage.MyIO;

public class Lista {
    int removerPilha() {
        if(n == 0) {
            throw new Exception("Erro");
        }

        return array[--n];
    }

    int removerFila() {
        if(n == 0) {
            throw new Exception("Erro");
        }

        int resp = array[0];
        for(int i = 0; i < n - 1; i++) {
            array[i] = array[i+1];
        }
        n--;
        return resp;
    }
}