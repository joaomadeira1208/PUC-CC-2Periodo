import mypackage.MyIO;

public class Exercicio4 {
    public static void main(String[] args) {
        double Numero_1 = MyIO.readDouble("Digite o primeiro numero: ");
        double Numero_2 = MyIO.readDouble("Digite o segundo numero: ");

        if(Numero_1 > 45 || Numero_2 > 45) {
            System.out.println(Numero_1 + Numero_2);
        }
        else if(Numero_1 > 20 && Numero_2 > 20) {
            if(Numero_1 > Numero_2) {
                System.out.println(Numero_1 - Numero_2);
            }
            else {
                System.out.println(Numero_2 - Numero_1);
            }
        }
        else if ((Numero_1 < 10 && Numero_1 != 0 && Numero_2 != 0) || 
         (Numero_2 < 10 && Numero_1 != 0 && Numero_2 != 0)) {
             System.out.println(Numero_1 / Numero_2);
        }
        else {
            System.out.println("João Madeira");
        }
    }
}
