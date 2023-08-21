import mypackage.MyIO;

public class Exercicio25 {
    public static void main(String[] args) {
        int N = MyIO.readInt("Digite o inteiro N: ");
        float[] elementos = new float[N];
        float menor = Float.MAX_VALUE;
        int menor_posicao = 0;

        for(int i = 0; i < N; i++) {
            elementos[i] = MyIO.readFloat("Digite o " + (i+1) + "-o elemento: ");
            if(menor > elementos[i]) {
                menor = elementos[i];
                menor_posicao = i;
            }
        }

        MyIO.println("A posicao do menor elemento no array = " + menor_posicao + ". Ou seja, o elemento " + (menor_posicao+1) + " e o menor.");
    }
}
