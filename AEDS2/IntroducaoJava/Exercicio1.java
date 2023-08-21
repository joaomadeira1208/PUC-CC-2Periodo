import mypackage.MyIO;

public class Exercicio1 {
    public static float leituraLado() {
        float Lado;
        Lado = MyIO.readFloat("Digite o valor do lado: ");
        return Lado;
    }
    
    public static void main(String[] args) {
        float Lado_A, Lado_B, Lado_C;
        Lado_A = leituraLado();
        Lado_B = leituraLado();
        Lado_C = leituraLado();

        if(Lado_A == Lado_B && Lado_B == Lado_C) {
            System.out.println("Triângulo Equilatero");
        }
        else if(Lado_A == Lado_B || Lado_A == Lado_C || Lado_B == Lado_C) {
            System.out.println("Triângulo Isosceles");
        }
        else {
            System.out.println("Triângulo Escaleno");
        }
        
    }
}
