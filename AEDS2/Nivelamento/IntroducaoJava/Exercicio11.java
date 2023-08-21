import mypackage.MyIO;

public class Exercicio11 {
    public static void main(String[] args) {
        int num = MyIO.readInt("Digite o numero ");
        if(num % 2 == 0){
                num--;
            }
        while (num > 0){
            MyIO.println(num);
            num -= 2;
        }
    }
}