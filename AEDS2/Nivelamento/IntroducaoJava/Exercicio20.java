import mypackage.MyIO;

public class Exercicio20 {
    public static void main(String[] args) {
        int tamanho = 5;
        int tamanho2 = 7;
        int tamanho_intersecao = 0;
        int[] array = new int[tamanho];
        int[] array2 = new int[tamanho2];
        int[] array_uniao_aux = new int[tamanho + tamanho2];
        int tamanho_uniao = 0;

        for(int i = 0; i < tamanho; i++) {
            boolean termoAdicionado = false;
            array[i] = MyIO.readInt("Digite o " + (i+1) + "-o termo do array 1: ");
            for(int k = 0; k < tamanho_uniao; k++) {
                if(array[i] == array_uniao_aux[k]) {
                    termoAdicionado = true;
                    break;
                }
            }
            if(!termoAdicionado) {
                array_uniao_aux[tamanho_uniao] = array[i];
                tamanho_uniao++;
            }
        }
        for(int i = 0; i < tamanho2; i++) {
            boolean termoAdicionado = false;
            array2[i] = MyIO.readInt("Digite o " + (i+1) + "-o termo do array 2: ");
            for(int k = 0; k < tamanho_uniao; k++) {
                if(array2[i] == array_uniao_aux[k]) {
                    termoAdicionado = true;
                    break;
                }
            }
            if(!termoAdicionado) {
                array_uniao_aux[tamanho_uniao] = array2[i];
                tamanho_uniao++;
            }
        }

        int[] array_uniao = new int[tamanho_uniao];

        MyIO.println("Uniao:");

        for(int i = 0; i < tamanho_uniao; i++) {
            array_uniao[i] = array_uniao_aux[i];
            MyIO.println(array_uniao[i]);
        }


       

        int[] array_intersecao_aux = new int[Math.min(tamanho, tamanho2)];

        for(int i = 0; i < tamanho; i++) {
            for(int j = 0; j < tamanho2; j++) {
                boolean termoAdicionado = false;
                for(int k = 0; k < tamanho_intersecao; k++) {
                    if(array_intersecao_aux[k] == array[i]) {
                        termoAdicionado = true;
                        break;
                    }
                }
                if((array[i] == array2[j]) && !termoAdicionado) {
                    array_intersecao_aux[tamanho_intersecao] = array[i];
                    tamanho_intersecao++;
                    break;
                }
            }
        }

        int[] array_intersecao = new int[tamanho_intersecao];
        MyIO.println("");
        MyIO.println("Intersecao:");

        for(int i = 0; i < tamanho_intersecao; i++) {
            array_intersecao[i] = array_intersecao_aux[i];
            MyIO.println(array_intersecao[i]);
        }

        

    

        


    }    
}
