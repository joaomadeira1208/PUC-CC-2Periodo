import mypackage.MyIO;


public class Exercicio2 {
    public static int MaiorInt(int control, int variable) {
        if(variable > control) {
            return variable;
        }
        else {
            return control;
        }
    }

    public static int MenorInt(int control, int variable) {
        if(variable < control) {
            return variable;
        }
        else {
            return control;
        }
    }
    
    
    public static void main(String[] args) {
        int Inteiro_1 = MyIO.readInt("Type the interger value: ");
        int Inteiro_2 = MyIO.readInt("Type the second interger value: ");
        int Inteiro_3 = MyIO.readInt("Type the third interger value: ");
        int Aux = MaiorInt(Inteiro_1, Inteiro_2);
        int maior = MaiorInt(Aux, Inteiro_3);
        int Aux_2 = MenorInt(Inteiro_1, Inteiro_2);
        int menor = MenorInt(Aux_2, Inteiro_3);
        System.out.println("The interger with the lowest value is: " + menor);
        System.out.println("The interger with the highest value is: " + maior );
    }


}

/*
 import mypackage.MyIO;

public class Exercicio2 {
    public static int findMax(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    public static int findMin(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    public static void main(String[] args) {
        int num1 = MyIO.readInt("Type the first integer value: ");
        int num2 = MyIO.readInt("Type the second integer value: ");
        int num3 = MyIO.readInt("Type the third integer value: ");
        
        int max = findMax(num1, num2, num3);
        int min = findMin(num1, num2, num3);
        
        System.out.println("The integer with the lowest value is: " + min);
        System.out.println("The integer with the highest value is: " + max);
    }
}

 */
