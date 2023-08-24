
public class Questao01 {
    public static boolean palindromo(String str) {
        int j = str.length() - 1;
        boolean isPalindromo = true;
        for(int i = 0; i < str.length()/2; i++) {
            if(str.charAt(i) != str.charAt(j)) {
                isPalindromo = false;
                i = str.length();
            }
            j--;
        }
        return isPalindromo;
    }

    public static void main(String[] args) {
        String str;
        do {
            str = MyIO.readLine();
            boolean isPalindromo = palindromo(str);
            if(isPalindromo) {
                MyIO.println("SIM");
            }
            else if (!str.equals("FIM")) {
                MyIO.println("NAO");
            }
        }while(!str.equals("FIM"));
    }

}