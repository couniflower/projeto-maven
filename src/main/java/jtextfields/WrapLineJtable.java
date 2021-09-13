package jtextfields;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WrapLineJtable {
    public StringBuilder wrapLine(String texto, int largura) {
        if(texto != null) {
            if (texto.contains("\n")) {
                StringBuilder str = new StringBuilder();
                str.append("<html>");
                str.append(texto.replaceAll("\n", "<br>"));
                str.append("</html>");

                return str;
            }

            if (texto.length() < largura) {
                int tamanho = 0, indexInicial = 0;
                StringBuilder substring = new StringBuilder();
                List<String> frasesMaiores = new ArrayList<>();
                List<String> frasesMenores = new ArrayList<>();

                while (tamanho < texto.length()) {
                    if (substring.length() <= largura) {
                        if (texto.charAt(tamanho) == ' ' || texto.charAt(tamanho) == '.') {
                            substring.append(texto, indexInicial, tamanho + 1);
                            indexInicial = tamanho + 1;
                            if (texto.charAt(tamanho) == '.') {
                                frasesMaiores.add(substring.toString());
                                substring = new StringBuilder();
                            }
                        }
                    } else {
                        frasesMaiores.add(substring.toString());
                        substring = new StringBuilder();
                    }
                    tamanho++;
                }

                List<String> frase;
                StringBuilder resto = new StringBuilder();
                tamanho = 0;
                while (tamanho < frasesMaiores.size()) {
                    substring = new StringBuilder(resto.toString());
                    resto = new StringBuilder();
                    substring.append(frasesMaiores.get(tamanho));
                    frase = Arrays.asList(substring.toString().split(" "));
                    substring = new StringBuilder();
                    for (String s : frase) {
                        if (substring.length() <= largura) {
                            if ((s.length()) < ((largura + 1) - substring.length())) {
                                substring.append(s).append(" ");
                            } else {
                                resto.append(s).append(" ");
                            }
                        }
                    }
                    frasesMenores.add(substring.toString());
                    if ((tamanho == (frasesMaiores.size() - 1)) && (resto.length() != 0)) {
                        frasesMenores.add(resto.toString());
                    }
                    tamanho++;
                }

                StringBuilder retorno = new StringBuilder();
                for (String f : frasesMenores) {
                    if (frasesMenores.indexOf(f) != (frasesMenores.size() - 1)) {
                        retorno.append(f).append("\n");
                    } else {
                        retorno.append(f);
                    }
                }

                StringBuilder str = new StringBuilder();
                str.append("<html>");
                str.append(retorno.toString().replaceAll("\n", "<br>"));
                str.append("</html>");

                return str;
            }

            StringBuilder str = new StringBuilder();
            str.append("<html>");
            str.append(texto.replaceAll("\n", "<br>"));
            str.append("</html>");

            return str;
        }
        return new StringBuilder(" ");
    }
}
