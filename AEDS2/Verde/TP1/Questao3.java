public class Questao3 {
    
    public static boolean saoIguais(String string, String string_2) {
        if(string == string_2) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public static String ciframentoCesar(String entradaString) {
        char[] novaString = new char[1000];
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