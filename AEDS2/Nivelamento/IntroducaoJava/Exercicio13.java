import mypackage.MyIO;

public class Exercicio13 {
    public static void main(String[] args) {
        int nota_max = MyIO.readInt("Digite a nota max ");
        float[] nota = new float[20];
        for(int i = 0; i<20; i++){
            nota[i] = MyIO.readFloat();
        }
        float media = 0;
        for(int i=0; i<20; i++){
            media += nota[i];
        }
        media = media/20;
        MyIO.println(media);
        int cont_menor = 0;
        int cont_A = 0;
        for(int i=0; i<20; i++){
            if(nota[i] < media){
                cont_menor++;
            }
            if(nota[i] > nota_max*0.9){
                cont_A++;
            }
        }
        MyIO.println(cont_menor);
        MyIO.println(cont_A);
    }
}