public class ArvoreTrie {
    public No raiz;

    public ArvoreTrie() {
        raiz = new No();
    }

    public void inserir(String s) throws Exception {
        inserir(s, raiz, 0);
    }

    public void inserir(String s, No no, int i) throws Exception {
        if(no.prox[s.charAt(i)] == null) {
            no.prox[s.charAt(i)] = new No(s.charAt(i));

            if(i == s.length() - 1) {
                no.prox[s.charAt(i)].folha = true;
            }
            else {
                inserir(s, no.prox[s.charAt(i)], i+1);
            }
        }
        else if(no.prox[s.charAt(i)].folha == false && i < s.length() - 1) {
            inserir(s, no.prox[s.charAt(i)], i+1);
        }
        else {
            throw new Exception("Erro");
        }
    }

    public boolean pesquisar(String s) throws Exception{
        return pesquisar(s, raiz, 0);
    }
    
    public boolean pesquisar(String s, No no, int i) throws Exception {
        boolean resp;
        if(no.prox[s.charAt(i)] == null) {
            resp = false;
        }
        else if(i == s.length() - 1) {
            resp = (no.prox[s.charAt(i)].folha == true);
        }
        else if(i < s.length() - 1) {
            resp = pesquisar(s, no.prox[s.charAt(i)], i+1);
        }
        else {
            throw new Exception();
        }
        return resp;
    }

    public void mostrar() {
        mostrar("", raiz);
    }

    public void mostrar(String s, No no) {
        if(no.folha == true) {
            System.out.println(s + no.elemento);
        }
        else {
            for(int i = 0; i < no.prox.length; i++) {
                if(no.prox[i] != null) {
                    mostrar(s + no.elemento, no.prox[i]);
                }
            }
        }
    }
}
