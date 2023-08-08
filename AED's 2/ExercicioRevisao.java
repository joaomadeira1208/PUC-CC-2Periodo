public class ExercicioRevisao {
    public static boolean isContido(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        int[] intArray = {10, 20, 30, 40, 50};
        int target = 10;
        boolean contido = isContido(intArray, target);
        
        if (contido) {
            System.out.println("O valor está contido no array.");
        } else {
            System.out.println("O valor não está contido no array.");
        }
    }
}

