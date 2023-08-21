import mypackage.MyIO;

public class Exercicio7 {
    public static void main(String[] args) {
        float[] num = new float[2];
        for(int i=0; i<2; i++){
            num[i] = MyIO.readFloat("Digite o num " + (i + 1) + " ");
        }
        if(num[0] > num[1]){
            MyIO.println(Math.pow(num[1],1.0/3.0));
            MyIO.println(Math.log(num[1]) / Math.log(num[0]));
        }else{
            MyIO.println(Math.pow(num[0],1.0/3.0));
            MyIO.println(Math.log(num[0]) / Math.log(num[1]));
        }
    }
}