package br.unifil.dc.lab2;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.awt.Color;

/**
 * Write a description of class Gravador here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Gravador
{
    public Gravador() {
        this.seqGravacoes = new ArrayList<Transparencia>();
    }

    /**
     * Adiciona na sequência de gravação uma gravacao em que todas as cores da lista são null
     *
     * @param lista lista de valores.
     * @param nome String com nome da gravação.
     */
    public void gravarLista(List<Integer> lista, String nome) {
        List<Integer> copia = new ArrayList<Integer>(lista);
        List<Color> cores = novaListaColors(lista.size());
        ListaGravada gravacao = new ListaGravada(copia, cores, nome);
        seqGravacoes.add(gravacao);
    }

    /**
     * Adiciona na sequência de gravação uma gravacao em que a lista de cores é null, exceto em i, onde é Amarela.
     *
     * @param lista lista de valores.
     * @param i indice
     * @param nome String com nome da gravação.
     */
    public void gravarIndiceDestacado(List<Integer> lista, int i, String nome) {
        List<Integer> copia = new ArrayList<Integer>(lista);
        List<Color> cores = novaListaColors(lista.size());
        cores.set(i, Color.YELLOW);
        ListaGravada gravacao = new ListaGravada(copia, cores, nome);
        seqGravacoes.add(gravacao);
    }

    /**
     * Adiciona na sequência de gravação uma gravacao em que a lista de cores é null, exceto em i e j, onde é Ciza.
     *
     * @param lista lista de valores
     * @param i indice i
     * @param j indice j
     */
    public void gravarComparacaoSimples(List<Integer> lista, int i, int j) {
        List<Integer> copia = new ArrayList<Integer>(lista);
        List<Color> cores = novaListaColors(lista.size());
        cores.set(i, Color.GRAY);
        cores.set(j, Color.GRAY);
        ListaGravada gravacao = new ListaGravada(copia, cores, "Comparação");
        seqGravacoes.add(gravacao);
    }

    /**
     * Adiciona na sequência de gravação uma gravacao em que a lista de cores é null, exceto em i e j, onde é Amarelo.
     *
     * @param lista lista de valores
     * @param i indice i
     * @param j indice j
     */
    public void gravarPosTrocas(List<Integer> lista, int i, int j) {
        List<Integer> copia = new ArrayList<Integer>(lista);
        List<Color> cores = novaListaColors(lista.size());
        cores.set(i, Color.YELLOW);
        cores.set(j, Color.YELLOW);
        ListaGravada gravacao = new ListaGravada(copia, cores, "Pós-troca");
        seqGravacoes.add(gravacao);
    }

    /**
     * Adiciona na sequência de gravação uma gravacao em que a lista de cores é null, exceto em j, onde é Amarelo e i Vermelho.
     *
     * @param lista lista de valores
     * @param i indice i
     * @param j indice j
     */
    public void gravarPosTrocasPivo(List<Integer> lista, int i, int j) {
        List<Integer> copia = new ArrayList<Integer>(lista);
        List<Color> cores = novaListaColors(lista.size());
        cores.set(i, Color.RED);
        cores.set(j, Color.YELLOW);
        ListaGravada gravacao = new ListaGravada(copia, cores, "Pós-troca Pivô");
        seqGravacoes.add(gravacao);
    }

    /**
     * Adiciona na sequência de gravação uma gravacao em que a lista de cores é null, exceto em i, j e c,
     * onde há cores distintas.
     *
     * @param lista lista de valores
     * @param a indice a
     * @param b indice b
     * @param c indice c
     */
    public void gravarIndicesDestacados(List<Integer> lista, int a, int b, int c) { //doc
        List<Integer> copia = new ArrayList<Integer>(lista);
        List<Color> cores = novaListaColors(lista.size());
        cores.set(a, Color.RED);
        cores.set(b, Color.green);
        cores.set(c, Color.CYAN);
        ListaGravada gravacao = new ListaGravada(copia, cores, "Comparação");
        seqGravacoes.add(gravacao);
    }

    /**
     * Adiciona na sequência de gravação uma gravacao em que metade da lista de cores é verde, e a outra magenta.
     * Caso exista apenas um elemento, a coloração será vermelha.
     *
     * @param lista lista de valores
     * @param nome indice nome d gravação
     */
    public void destacarMetades(List<Integer> lista, String nome) { //doc
        gravarLista(lista, nome);

        List<Integer> copia = new ArrayList<Integer>(lista);
        List<Color> cores = novaListaColors(lista.size());

        if(lista.size() > 1){
            for(int i = 0; i <lista.size(); i++){
                cores.set(i, (i < lista.size()/2)? Color.green : Color.magenta);
            }
        }else{
             cores.set(0, Color.red);
        }


        ListaGravada gravacao = new ListaGravada(copia, cores, "Comparação");
        seqGravacoes.add(gravacao);
    }


    /**Inverte ordem do gravador.
     */
    public void inverterOrdem(){
        Collections.reverse(seqGravacoes);
    }

    public ListIterator<Transparencia> getFilme() {
        return seqGravacoes.listIterator();
    }

    private static List<Color> novaListaColors(int numElems) {
        ArrayList<Color> lista = new ArrayList<>(numElems);
        for (; numElems > 0; numElems--) lista.add(null);
        return lista;
    }

    private List<Transparencia> seqGravacoes;
}
