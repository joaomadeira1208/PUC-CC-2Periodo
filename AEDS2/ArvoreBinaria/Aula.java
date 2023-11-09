
class Arvore {

    public boolean pesquisar(String nome, No no) {
        boolean resp = false;
        if (no == null) {

        } else if (nome.charAt(0) == no.elemento) {
            resp = pesquisar(String nome, no.outro);
        } else if (nome.charAt(0) < no.elemento) {
            resp = pesquisar(nome, no.esq);
        } else if (nome.charAt(0) > no.elemento) {
            resp = pesquisar(nome, no.dir);
        }

        return resp;
    }

    public boolean pesquisar(String nome, No2 no2) {
        boolean resp = false;
        boolean cmpElemento = nome.compareTo(no2.elemento);
        if(cmpElemento == 0) {
            return true;
        }
        else if(cmpElemento < 0) {
            return pesquisar(nome, no2.esq);
        }
        else(cmpElemento > 0) {
            return pesquisar(nome, no2.dir);
        } 
    }

    public boolean inserir(String nome) {
        
    }
}