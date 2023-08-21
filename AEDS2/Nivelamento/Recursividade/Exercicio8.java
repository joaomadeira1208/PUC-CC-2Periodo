

public class Exercicio8 {
    public static void sort_array(int array[], int tamanho){
        if(tamanho != 0){
           if(array[tamanho-1] > array[tamanho-2]){
           return sort_array(array,tamanho - 1);
           }else{
           int aux = array[tamanho - 1];
           array[tamanho -1] = array[tamanho -2];
           array[tamanho - 2] = aux;
           return sort_array(array, tamanho - 1);
        }
   }
}