public class Questao3 {
    
    public static boolean saoIguais(String string, String string_2) {
        if(string.length() != string_2.length()) {
            return false;
        }
        for(int i = 0; i < string.length(); i++) {
            if(string.charAt(i) != string_2.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    
    public static String ciframentoCesar(String entradaString) {
        char[] novaString = new char[entradaString.length()];
        for(int i = 0; i < entradaString.length(); i++) {
            novaString[i] = (char) (entradaString.charAt(i) + 3);                
        }
        return new String(novaString); 
    }
    
    
    public static void main(String[] args) {
        String string;
        do {
            string = MyIO.readLine();
            String stringCifrada = ciframentoCesar(string);
            if(!saoIguais(string, "FIM")) {
                MyIO.println(stringCifrada);
            }
        }while(!saoIguais(string, "FIM"));

    }
}