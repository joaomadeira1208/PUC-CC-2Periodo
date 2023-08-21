import mypackage.MyIO;

public class Exercicio41 {
    public static void main(String[] args) {
        String str = MyIO.readString("Digite a string: ");
        int numero = 0;
        int aux;

        for(int i = 0; i < str.length(); i++) {
            aux = (int) (str.charAt(i) - 48);
            aux *= (int) Math.pow(10, (str.length() - i - 1));
            numero += aux;
        }

        MyIO.println(numero);
    }
}
