import mypackage.MyIO;

public class Exercicio42 {
    public static void main(String[] args) {
        String str = MyIO.readString("Digite a string: ");
        boolean palindromo = true;
        for(int i = 0; i < str.length()/2; i++) {
            if(str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                palindromo = false;
                i = str.length();
            }
        }
        if(palindromo) {
            MyIO.println("Palindromo!");
        }
        else {
            MyIO.println("Nao e um palindromo");
        }
    }
}
