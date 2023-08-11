public class ExercicioRevisao3 {
    
    public static int maiorElemento(int[] arranjoInt) {
        int maior = arranjoInt[0];
        for(int i = 1; i < arranjoInt.length; i++) {
            if(arranjoInt[i] > maior) {
                maior = arranjoInt[i];
            }
        }
        return maior;
    }

    public static int menorElemento(int[] arranjoInt) {
        int menor = arranjoInt[0];
        for(int i = 1; i < arranjoInt.length; i++) {
            if(arranjoInt[i] < menor) {
                menor = arranjoInt[i];
            }
        }
        return menor;
    }
    
    public static void main(String[] args) {
        int[] arranjoInt = {-189, 176, 200, 300, -1000, 1000, 543};
        int maior = maiorElemento(arranjoInt);
        int menor = menorElemento(arranjoInt);
        System.out.println("O maior elemento do arranjo é " + maior);
        System.out.println("O menor elemento do arranjo é " + menor);
    }
}
