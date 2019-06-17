package br.unifil.dc.lab2;

import java.util.*;

/**
 * Write a description of class AlgoritmosAnimados here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AlgoritmosAnimados {
    public static Gravador listaEstatica(List<Integer> valores) {
        Gravador anim = new Gravador();
        anim.gravarLista(valores, "Valores da lista imutável");
        return anim;
    }

    public static Gravador buscaSequencial(List<Integer> valores, int chave) {
        System.out.println(chave);
        //Instancia da classe Gravador
        Gravador anim = new Gravador();
        //Metodo instancia a classe Gravador  com lista de cores null (null = Color.Blue).
        anim.gravarLista(valores, "Inicio de busca sequencial");


        // indice 0
        int i = 0;
        //Realiza um laço para percorrer todos os valores != de chave.
        while (i < valores.size() && valores.get(i) != chave) {

            System.out.println(i + "/" + valores.get(i));


            //aciona o gravador com lista de cores com null (Azul), exceto na posicao i que é Amarelo.
            anim.gravarIndiceDestacado(valores, i, "Busca sequencial");

            //indice i+1.
            i++;
        }

        //aciona que se a saida o while foi por valor == chave ou por ter percorrido todos os valores
        if (i < valores.size()) {
            //destaca a posicao i na cor amarelo
            anim.gravarIndiceDestacado(valores, i, "Chave encontrada");
        } else {
            //torna toda as cores Azul.
            anim.gravarLista(valores, "Chave não encontrada");
        }
        //retorna o gravador instanciado.
        return anim;
    }


    public static Gravador ordenarPorBolha(List<Integer> valores) {//doc
        Gravador anim = new Gravador();
        anim.gravarLista(valores, "Disposição inicial");

        boolean houvePermuta;

        do {

            houvePermuta = false;

            for (int i = 1; i < valores.size(); i++) {

                final Integer r = valores.get(i);
                final Integer l = valores.get(i - 1);
                anim.gravarComparacaoSimples(valores, i, i - 1);

                if (l.compareTo(r) > 0) {

                    permutar(anim, valores, i - 1, i);
                    houvePermuta = true;

                }
            }

        } while (houvePermuta);
        anim.gravarLista(valores, "Disposição final");

        return anim;


    }

    public static Gravador ordenarPorSelecao(List<Integer> valores) {//doc

        Gravador anim = new Gravador();
        anim.gravarLista(valores, "Disposição inicial");

        for (int i = 0; i < valores.size() - 1; i++) {
            int idxMenor = getMenorIdx(anim, valores, i);
            permutar(anim, valores, i, idxMenor);

        }

        anim.gravarLista(valores, "Disposição final");


        return anim;
    }

    public static void permutar(Gravador anim, List<Integer> valores, int i, int j) {
        permutar(anim, valores,i ,j ,false);
    }

    public static void permutar(Gravador anim, List<Integer> valores, int i, int j, boolean tipo) {//doc
        Integer held = valores.get(i);
        valores.set(i, valores.get(j));
        valores.set(j, held);
        if(tipo == false){
            anim.gravarPosTrocas(valores, i, j);
        }else{
            anim.gravarPosTrocasPivo(valores, i, j);
        }

    }

    public static int getMenorIdx(Gravador anim, List<Integer> valores, int i) {//doc
        int menor = i;
        for (int j = i + 1; j < valores.size(); j++) {
            final Integer atual = valores.get(menor);
            final Integer proximo = valores.get(j);
            anim.gravarComparacaoSimples(valores, menor, j);
            if (atual.compareTo(proximo) > 0) menor = j;

        }

        return menor;
    }

    public static Gravador buscaBinaria(List<Integer> valores, int chave) { //documentar e verificar no GitHub (TRATAR N ORDENADO)

        Gravador anim = new Gravador();
        anim.gravarLista(valores, "Disposição inicial");

        int l = 0, r = valores.size();
        while (l < r) {
            final int meio = (l + r) / 2;
            final int comparados = valores.get(meio).compareTo(chave);
            anim.gravarIndicesDestacados(valores, l, meio, r - 1);
            if (comparados < 0) {
                l = meio + 1;
            } else if (comparados > 0) {
                r = meio;
            } else {
                anim.gravarIndiceDestacado(valores, meio, "Chave encontrada");
                return anim;
            }

        }
        anim.gravarLista(valores, "chave não encontrada");
        return anim;
    }

    public static Gravador ordenarInsercao(List<Integer> valores) { //doc

        Gravador anim = new Gravador();
        anim.gravarLista(valores, "Disposição inicial");

        for (int i = 1; i < valores.size(); i++) {

            final Integer comprada = valores.get(i);

            for (int j = i - 1; j >= 0; j--) {
                final Integer topoMao = valores.get(j);
                anim.gravarComparacaoSimples(valores, j + 1, j);
                if (topoMao.compareTo(comprada) > 0) {
                    permutar(anim, valores, j + 1, j);
                } else {
                    break;
                }
            }
        }
        anim.gravarLista(valores, "Disposição final");

        return anim;
    }


    public static Gravador ordenarMergeSort(List<Integer> valores) {

        Gravador anim = new Gravador();
        //Gravando o estado inicial da lista

        anim.destacarMetades(valores, "Disposição inicial");

        ordenarMergeSort(valores, 0, valores.size() - 1, anim);

        // anim.inverterOrdem();
        anim.gravarLista(valores, "Disposição final");


        return anim;
    }

    public static void ordenarMergeSort(List<Integer> valores, int low, int high, Gravador anim) {

        if ((high - low >= 1)) {
            int middle1 = (low + high) / 2;
            int middle2 = middle1 + 1;


            anim.destacarMetades(valores.subList(low, middle2), "Dividido");
            ordenarMergeSort(valores, low, middle1, anim);

            anim.destacarMetades(valores.subList(middle2, high + 1), "Dividido");
            ordenarMergeSort(valores, middle2, high, anim);


            merge(valores, low, middle1, middle2, high, anim);


        }


    }

    private static void merge(List<Integer> valores, int left, int middle1, int middle2, int right, Gravador anim) {

        int leftIndex = left;
        int rightIndex = middle2;
        int combinedIndex = 0;
        List<Integer> intercalado = new ArrayList<>();


        while (leftIndex <= middle1 && rightIndex <= right) {
            List<Integer> comp = List.of(valores.get(leftIndex), valores.get(rightIndex));
            anim.gravarComparacaoSimples(comp, 0, 1);

            if (valores.get(leftIndex).compareTo(valores.get(rightIndex)) <= 0) {

                intercalado.add(combinedIndex++, valores.get(leftIndex++));


            } else {

                intercalado.add(combinedIndex++, valores.get(rightIndex++));

            }

            anim.gravarIndiceDestacado(intercalado, intercalado.size() - 1, "Posicao intercalado");

        }


        if (leftIndex == middle2) {
            while (rightIndex <= right) {
                intercalado.add(combinedIndex++, valores.get(rightIndex++));
                anim.gravarIndiceDestacado(intercalado, intercalado.size() - 1, "Posicao intercalado");
            }
        } else {

            while (leftIndex <= middle1) {
                intercalado.add(combinedIndex++, valores.get(leftIndex++));
                anim.gravarIndiceDestacado(intercalado, intercalado.size() - 1, "Posicao intercalado");
            }
            ;

        }


        for (int i = left; i <= right; i++) {
            valores.set(i, intercalado.get(i - left));
        }


    }

    public static Gravador ordenarQuickSort(List<Integer> valores) {

        Gravador anim = new Gravador();


        anim.gravarLista(valores, "Disposição inicial");

        ordenarquickSort(valores, 0, valores.size() - 1, anim);


        anim.gravarLista(valores, "Disposição final");


        return anim;
    }

    private static void ordenarquickSort(List<Integer> valores, int low, int high, Gravador anim) {
        int i = low;
        int j = high;

        int idxPivo = low + (high - low) / 2;
        int pivo = valores.get(idxPivo);
        anim.destacarMetades(valores.subList(low, high+1), "Metades");
        anim.gravarIndiceDestacado(valores, idxPivo, "pivo");

        while (i <= j) {

            while (valores.get(i) < pivo) {
                anim.gravarComparacaoSimples(valores, i, idxPivo);
                i++;
            }
            while (valores.get(j) > pivo) {
                anim.gravarComparacaoSimples(valores, j, idxPivo);
                j--;
            }
            if (i <= j) {
                anim.gravarComparacaoSimples(valores, i, j);
                permutar(anim, valores, i, j, (i==idxPivo || j == idxPivo)? true : false);

                i++;
                j--;
            }
        }

        if (low < j)
            ordenarquickSort(valores, low, j, anim);

        if (i < high)
            ordenarquickSort(valores, i, high, anim);
    }


}