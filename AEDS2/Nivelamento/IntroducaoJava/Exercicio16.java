import mypackage.MyIO;

public class Exercicio16 {
    public static void main(String[] args) {
        float[] num = new float[3];
        for(int i = 0; i<3; i++){
        num[i] = MyIO.readFloat();
        }
        float media = 0;
        for(int i=0; i<3; i++){
        media += num[i];
        }
        media = media/3;
        for(int i=0; i<3; i++){
            if(num[i] > media){
                MyIO.println(num[i]);
            }
    }
    }
}