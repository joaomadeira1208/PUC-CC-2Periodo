import mypackage.MyIO;

public class Exercicio5 {
    public static void main(String[] args) {
        int Gols_Mandante = MyIO.readInt("Quantos gols o time mandante marcou: ");
        int Gols_Visitante = MyIO.readInt("Quantos gols o time visitante marcou: ");

        if(Gols_Mandante > Gols_Visitante) {
            MyIO.println("O time mandante ganhou a partida.");
        }
        else if(Gols_Mandante < Gols_Visitante) {
            MyIO.println("O time visitante ganhou a partida.");
        }
        else {
            MyIO.println("O jogo resultou em um empate.");
        }
    }
}
