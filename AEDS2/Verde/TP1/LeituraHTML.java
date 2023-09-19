import java.io.*;
import java.net.*;
public class LeituraHTML {

    // Método recebe um endereço como parâmetro, faz uma requisição HTTP para esse endereço, lê o conteúdo HTML da página e o retorna como uma string.
    public static String getHtml(String endereco) {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String resp = "", line;

        try {
            url = new URL(endereco);
            is = url.openStream(); // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                resp += line + "\n";
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            is.close();
        } catch (IOException ioe) {
            // nothing to see here

        }

        return resp;
    }

    // Método para comparar duas strings e verificar se são iguais.
    public static boolean equalStrings(String str_1, String str_2) {
        if (str_1.length() != str_2.length()) {
            return false;
        }
        for (int i = 0; i < str_1.length(); i++) {
            if (str_1.charAt(i) != str_2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // Main
    public static void main(String[] args) {
        String endereco, paginaHtml, nomePagina;
        MyIO.setCharset("UTF-8");
        do {
            nomePagina = MyIO.readLine();
            if (!equalStrings(nomePagina, "FIM")) {
                endereco = MyIO.readLine();
                paginaHtml = getHtml(endereco);
                int x1 = 0, x2 = 0, x3 = 0, x4 = 0, x5 = 0, x6 = 0, x7 = 0, x8 = 0, x9 = 0, x10 = 0, x11 = 0, x12 = 0,
                        x13 = 0,
                        x14 = 0, x15 = 0, x16 = 0, x17 = 0, x18 = 0, x19 = 0, x20 = 0, x21 = 0, x22 = 0, x23 = 0,
                        x24 = 0,
                        x25 = 0;
                for (int i = 0; i < paginaHtml.length(); i++) {
                    if (paginaHtml.charAt(i) == 'a') {
                        x1++;
                    } else if (paginaHtml.charAt(i) == 'e') {
                        x2++;
                    } else if (paginaHtml.charAt(i) == 'i') {
                        x3++;
                    } else if (paginaHtml.charAt(i) == 'o') {
                        x4++;
                    } else if (paginaHtml.charAt(i) == 'u') {
                        x5++;
                    } else if (paginaHtml.charAt(i) == '\u00E1') { // á
                        x6++;
                    } else if (paginaHtml.charAt(i) == '\u00E9') { // é
                        x7++;
                    } else if (paginaHtml.charAt(i) == '\u00ED') { // í
                        x8++;
                    } else if (paginaHtml.charAt(i) == '\u00F3') { // ó
                        x9++;
                    } else if (paginaHtml.charAt(i) == '\u00FA') { // ú
                        x10++;
                    } else if (paginaHtml.charAt(i) == '\u00E0') { // à
                        x11++;
                    } else if (paginaHtml.charAt(i) == '\u00E8') { // è
                        x12++;
                    } else if (paginaHtml.charAt(i) == '\u00EC') { // ì
                        x13++;
                    } else if (paginaHtml.charAt(i) == '\u00F2') { // ò
                        x14++;
                    } else if (paginaHtml.charAt(i) == '\u00F9') { // ù
                        x15++;
                    } else if (paginaHtml.charAt(i) == '\u00E3') { // ã
                        x16++;
                    } else if (paginaHtml.charAt(i) == '\u00F5') { // õ
                        x17++;
                    } else if (paginaHtml.charAt(i) == '\u00E2') { // â
                        x18++;
                    } else if (paginaHtml.charAt(i) == '\u00EA') { // ê
                        x19++;
                    } else if (paginaHtml.charAt(i) == '\u00EE') { // î
                        x20++;
                    } else if (paginaHtml.charAt(i) == '\u00F4') { // ô
                        x21++;
                    } else if (paginaHtml.charAt(i) == '\u00FB') { // û
                        x22++;
                    } else if (paginaHtml.charAt(i) >= 'a' && paginaHtml.charAt(i) <= 'z') {
                        x23++;
                    } else if (paginaHtml.charAt(i) == '<' && paginaHtml.charAt(i + 1) == 'b'
                            && paginaHtml.charAt(i + 2) == 'r'
                            && paginaHtml.charAt(i + 3) == '>') {
                        x24++;
                        x23 -= 2;
                    } else if (paginaHtml.charAt(i) == '<' && paginaHtml.charAt(i + 1) == 't'
                            && paginaHtml.charAt(i + 2) == 'a'
                            && paginaHtml.charAt(i + 3) == 'b' && paginaHtml.charAt(i + 4) == 'l'
                            && paginaHtml.charAt(i + 5) == 'e' && paginaHtml.charAt(i + 6) == '>') {
                        x25++;
                        x23 -= 3;
                        x1--;
                        x2--;
                    }

                }

                MyIO.print("a(" + x1 + ") ");
                MyIO.print("e(" + x2 + ") ");
                MyIO.print("i(" + x3 + ") ");
                MyIO.print("o(" + x4 + ") ");
                MyIO.print("u(" + x5 + ") ");
                MyIO.print("\u00E1(" + x6 + ") ");
                MyIO.print("\u00E9(" + x7 + ") ");
                MyIO.print("\u00ED(" + x8 + ") ");
                MyIO.print("\u00F3(" + x9 + ") ");
                MyIO.print("\u00FA(" + x10 + ") ");
                MyIO.print("\u00E0(" + x11 + ") ");
                MyIO.print("\u00E8(" + x12 + ") ");
                MyIO.print("\u00EC(" + x13 + ") ");
                MyIO.print("\u00F2(" + x14 + ") ");
                MyIO.print("\u00F9(" + x15 + ") ");
                MyIO.print("\u00E3(" + x16 + ") ");
                MyIO.print("\u00F5(" + x17 + ") ");
                MyIO.print("\u00E2(" + x18 + ") ");
                MyIO.print("\u00EA(" + x19 + ") ");
                MyIO.print("\u00EE(" + x20 + ") ");
                MyIO.print("\u00F4(" + x21 + ") ");
                MyIO.print("\u00FB(" + x22 + ") ");
                MyIO.print("consoante(" + x23 + ") ");
                MyIO.print("<br>(" + x24 + ") ");
                MyIO.print("<table>(" + x25 + ") ");
                MyIO.println(nomePagina);
            }
        } while (!equalStrings(nomePagina, "FIM"));
    }
}