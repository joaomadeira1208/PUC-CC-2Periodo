public static void countingsort(ArrayList<Jogador> listaJogadores) {
        int n = listaJogadores.size();

        int maxAltura = getMaior(listaJogadores).getAltura();

        int[] count = new int[maxAltura + 1];
        Jogador[] ordenado = new Jogador[n];

        
        for(int i = 0; i < count.length; count[i] = 0, i++);

        for(int i = 0; i < n; count[listaJogadores.get(i).getAltura()]++, i++);

        for(int i = 1; i < count.length; count[i] += count[i - 1], i++);

        for(int i = n - 1; i >= 0; i--) {
            Jogador jogadorAtual = listaJogadores.get(i);
            int pos = count[jogadorAtual.getAltura()] - 1;

            
            ordenado[pos] = jogadorAtual;
            count[jogadorAtual.getAltura()]--;
        }

        for(int i = 0; i < n; i++) {
            listaJogadores.set(i, ordenado[i]);
        }


    }

    public static Jogador getMaior(ArrayList<Jogador> listaJogadores) {
        Jogador maior = listaJogadores.get(0);
        for(int i = 0; i < listaJogadores.size(); i++) {
            if(maior.getAltura() < listaJogadores.get(i).getAltura()) {
                maior = listaJogadores.get(i);
            }
            else if(maior.getAltura() == listaJogadores.get(i).getAltura()) {
                String nome1 = maior.getNome();
                String nome2 = listaJogadores.get(i).getNome();
                if(nome1.compareTo(nome2) > 0) {
                    maior = listaJogadores.get(i);
                }
            }
        }
        return maior;
    }