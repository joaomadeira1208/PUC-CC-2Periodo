import mypackage.Arq;
public class ExemploArq03Exercicio {
    public static void main(String[] args) {
        Arq.openRead("exemplo.txt");
        String str = "";
        while(Arq.hasNext() == true) {
            str += Arq.readLine();
            str += "\n";
        }
        Arq.close();

        Arq.openWrite("copia.txt");
        Arq.println(str);
        Arq.close();

    }


}
