

public class Exercicio6 {
    public static int maior_array(int array[], int tamanho, int maior){
        if(tamanho != 0){
            if(array[tamanho - 1] > maior){
                maior = array[tamanho - 1];
                return maior_array(array, tamanho -1, maior);
            }
        }
        return maior;
    }
}