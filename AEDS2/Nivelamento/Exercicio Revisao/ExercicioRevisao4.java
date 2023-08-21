public class ExercicioRevisao4 {
    public static void maxMinElementos(int[] arranjoInt) {
        int max, min;
        if(arranjoInt[0] < arranjoInt[1]) {
            min = arranjoInt[0];
            max = arranjoInt[1];
        }
        else {
            max = arranjoInt[1];
            min = arranjoInt[0];
        }

        for(int i = 2; i < arranjoInt.length; i++) {
            if(arranjoInt[i] < min) {
                min = arranjoInt[i];
            }
            else if(arranjoInt[i] > max) {
                max = arranjoInt[i];
            }
        }

        System.out.println("O maior elemento do arranjo é " + max);
        System.out.println("O menor elemento do arranjo é " + min);

        
    }
    
    public static void main(String[] args) {
        int[] arranjoInt = {0, 1, 2, 3};
        maxMinElementos(arranjoInt);
    }
}
