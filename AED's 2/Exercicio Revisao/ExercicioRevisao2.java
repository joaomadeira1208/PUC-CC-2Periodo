

public class ExercicioRevisao2 {
    
    public static boolean isContained(int[] selectedArray, int target) {
        int left = 0;
        int right = selectedArray.length - 1;
        while(left <= right) {
            int middle = left + (right - left) / 2;
            if(selectedArray[middle] == target) {
                return true;
            }
            else if(selectedArray[middle] < target) {
                left = middle + 1;
            }
            else {
                right = middle - 1;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        int[] selectedArray = {100, 200, 300, 400, 500, 600};
        int target = 250;
        boolean contained = isContained(selectedArray, target);
        if(contained) {
            System.out.println("The target is contained in the selected array");
        }
        else {
            System.out.println("The target isn't contained in the selected array");
        }
    }
}
