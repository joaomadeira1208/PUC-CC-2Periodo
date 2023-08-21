import mypackage.MyIO;

public class Exercicio22 {
    public static void main(String[] args) {
        float[] notas = new float[5];
        float soma = 0;
        float menor = Float.MAX_VALUE;
        for(int i = 0; i < 5; i++) {
          notas[i] = MyIO.readFloat("Digite a " + (i+1) +"-a nota: ");
          soma += notas[i];
          menor = Math.min(menor, notas[i]);
        }

        float media = soma / 5;
        MyIO.println("Soma = " + soma);
        MyIO.println("Media = " + media);
        MyIO.println("Menor nota = " + menor);
    }    
}
