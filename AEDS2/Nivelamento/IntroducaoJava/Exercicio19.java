import mypackage.MyIO;

public class Exercicio19 {
    public static void ordenar_array(int array[], int tamanho){
        for(int i = 0; i < tamanho - 1; i++){
            int menor = i;
            for(int j = i + 1; j < tamanho; j++){
                if(array[menor] > array[j]){
                    menor = j;
                }
            }
            swap(array, menor,i);
        }
    }

    public static void swap(int array[], int a, int b){
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}