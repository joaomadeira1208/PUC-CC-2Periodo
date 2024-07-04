
public class AB {
    No raiz;

    public AB() {
        raiz = new No();
    }

    public void inserir(String s) throws Exception {
        inserir(s, raiz, 0);
    }

    public void inserir(String s, No no, int i) throws Exception {
        No filho = no.pesquisar(s.charAt(i));

        if(filho == null) {
            filho = no.inserir(s.charAt(i));

            if(i == s.length() - 1) {
                no.setFilho(s.charAt(i));
            }
            else {
                inserir(s, filho, i+1);
            }
        }
        else if(filho.folha == false && i < s.length()) {
            inserir(s, filho, i+1);
        }
        else {
            throw new Exception("Erro");
        }
    }

    public boolean pesquisar(String s) throws Exception {
        return pesquisar(s, raiz, 0);
    }

    public boolean pesquisar(String s, No no, int i) throws Exception {
        No filho = no.pesquisar(s.charAt(i));
        boolean resp;
        if(filho == null) {
            resp = false;
        }
        else if(i == s.length() - 1) {
            resp = (filho.folha == true);
        }
        else if(i < s.length() - 1) {
            pesquisar(s, filho, i+1);
        }
        else {
            throw new Exception("Erro");
        }

        return resp;
    }

    public void mostrar() {
        mostrar("",raiz);
    }

    public void mostrar(String s, No no) {
        if(no.folha == true) {
            System.out.println(s + no.elemento);
        }
        else {
            No[] filho = no.getFilho();
            for(int i = 0; i < filho.length; i++) {
                mostrar(s + no.elemento, filho[i]);
            }
        }
    }
}
