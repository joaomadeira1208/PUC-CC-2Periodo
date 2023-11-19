public class Questao01 {
    private static  No raiz;

    public static int contarPalavras(char primeiro, char ultimo) {
        return contarPalavras(raiz, primeiro, ultimo);
    }

    public static int contarPalavras(No i, char primeiro, char ultimo) {
        int resp = 0;
        if(i != null) {
            if(i.letra == primeiro) {
                resp = caminhar(i.raiz, ultimo, resp);
            }
            else if(i.letra < primeiro) {
                resp = contarPalavras(i.esq, primeiro, ultimo);
            }
            else {
                resp = contarPalavras(i.dir, primeiro, ultimo);
            }
        }
        return resp;
    }

    public static int caminhar(No2 i, char ultimo, int resp) {
        if(i != null) {
            resp = caminhar(i.esq, ultimo, resp);
            if(i.palavra.charAt(i.palavra.length() - 1) == ultimo) {
                resp++;
            }
            resp = caminhar(i.dir, ultimo, resp);
        }
        return resp;
    }

    public static void main(String[] args) {
        raiz = new No('A');
        raiz.esq = new No('E');
        raiz.esq.raiz = new No2("ETA");
        raiz.esq.raiz.esq = new No2("EAA");
        raiz.esq.raiz.dir = new No2("ETAA");
        raiz.raiz = new No2("AE");
        raiz.raiz.esq = new No2("ABE");
        raiz.raiz.dir = new No2("AOUE");
        int n = contarPalavras('E', 'A');
        System.out.println(n);

    }
}