
public class Exercicio2 {
    public static void mostra3_rec(int num){
        if (num == 3){
            return; 
        }else{
            System.out.println(num);
            mostra3_rec(num + 1);
        }
    }
    public static void main(String[] args) {
        int num = 0;
        mostra3_rec(num);
    }
}