import mypackage.MyIO;

public class Exercicio40 {
    public static void main(String[] args) {
        String Str = MyIO.readString("Digite a string: ");
        boolean Apenas_Digitos = true;

        for(int i = 0; i < Str.length(); i++) {
            if((Str.charAt(i) >= '0' && Str.charAt(i) <= '9') == false) {
                Apenas_Digitos = false;
                i = Str.length();
            }
        }

        if(Apenas_Digitos) {
            MyIO.println("Apenas numeros");

        }
        else {
            MyIO.println("Nao e apenas numeros");
        }

    }
}
