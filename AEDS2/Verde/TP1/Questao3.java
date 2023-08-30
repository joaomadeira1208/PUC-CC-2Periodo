import mypackage.MyIO;

public class Questao3 {
    
    public static String ciframentoCesar(String entradaString) {
        char[] novaString = new char[1000];
        for(int i = 0; i < entradaString.length(); i++) {
            if((entradaString.charAt(i) >= 'A' && entradaString.charAt(i) <= 'Z') || (entradaString.charAt(i) >= 'a' && entradaString.charAt(i) <= 'z')) {
                novaString[i] = (char) (entradaString.charAt(i) + 3);
            }
        }
        return new String(novaString); 
    }
    
    
    public static void main(String[] args) {
        String string;
        do {
            string = MyIO.readLine();
            String stringCifrada = ciframentoCesar(string);
            if(string != "FIM") {
                MyIO.println(stringCifrada);
            }
        }while(string != "FIM");

    }
}