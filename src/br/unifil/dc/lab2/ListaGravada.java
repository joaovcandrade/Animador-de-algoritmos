package br.unifil.dc.lab2;

import javax.swing.JPanel;
import java.awt.*;
import java.util.List;

/**
 * Write a description of class ListaGravada here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ListaGravada implements Transparencia
{
    /**
     * Constructor for objects of class ListaGravada
     */
    public ListaGravada(List<Integer> lista, List<Color> coresIndices, String nome) {
        this.lista = lista;
        this.nome = nome;
        this.coresIndices = coresIndices;
    }
    
    
    public void pintar(Graphics2D pincel, JPanel contexto) {

        Dimension dim = contexto.getSize();

        escreveNome(pincel, dim);

        final int listaSize = lista.size();
        final int maior = maiorElem(lista);
        final int maiorRet = 300;
        final int tamLinha = 3;
        int deslocVert = Math.round((500/600.0f) * dim.height);
        int tamRet = Math.round((560/listaSize)/800.0f * dim.width);
        int espacamento = Math.round((250/listaSize)/800.0f * dim.width);
        int deslocHoriz = Math.round((100/listaSize)/800.0f * dim.width);

        pincel.setStroke(new BasicStroke(tamLinha));

        for(int i = 0; i < lista.size(); i ++){
            int h = (lista.get(i)*maiorRet)/(maior);

            //desenha retangulo e preenche.
            pincel.setColor((coresIndices.get(i) != null) ? coresIndices.get(i) : Color.BLUE);
            pincel.fillRect(deslocHoriz, deslocVert-h, tamRet, h);
            pincel.setColor(Color.black);
            pincel.drawRect(deslocHoriz, deslocVert-h, tamRet, h);


            //escreve valor
            String vlrStr = lista.get(i).toString();
            FontMetrics fmt = pincel.getFontMetrics();
            int sizFont = - fmt.stringWidth(vlrStr)*-1;
            int x = ((deslocHoriz+(tamRet/2)) - sizFont);
            pincel.drawString(vlrStr, x , deslocVert+18);

            if(i != listaSize-1){
                //adiciona a distancia percorrida
                deslocHoriz += espacamento + tamRet;
            }


        }

        pontilhaLimites(pincel, dim, deslocVert, maiorRet, 3);

    }

    /**
     * Escreve o nome da gravação na parte superior direita da tela
     */
    public void escreveNome(Graphics2D pincel, Dimension dim){
        FontMetrics fmts = pincel.getFontMetrics();
        final int sizFonts = - fmts.stringWidth(nome)*-1;
        pincel.drawString(nome, (dim.width - sizFonts), 18);
    }


    /**
     * Retorna o maior elemento de uma lista de inteiros.
     * @param lista lista de inteiros
     * @return o maior inteiro da lista.
     */
    public int maiorElem(List<Integer> lista){

        int maior = lista.get(0);
        for(int n : lista){
            if(n > maior) maior = n;
        }
        return maior;

    }

    /**
     * Desenha duas linhas pontinhada horizontalemente na largura, cada uma nas margens de um retangulo.
     * @param pincel Graphics2D.
     * @param dim dimensões da tela.
     * @param maxH altura do ponto mais baixo do retangulo até a margem da tela..
     * @param maxCol tamanho maximo do retangulo.
     * @param dist distancia adicional entre a linha e o retangulo.
     */
    public void pontilhaLimites(Graphics2D pincel, Dimension dim,  int maxH, int maxCol, int dist ){

        float [] dash = {1f, 1f, 1f}; //intervalo de entre traços

        BasicStroke strk = new BasicStroke (1, BasicStroke.CAP_BUTT,
        BasicStroke.JOIN_ROUND, 1.0f, dash, 2f);

        pincel.setStroke(strk);

        pincel.drawLine (0, (maxH-maxCol) - dist, dim.width, (maxH-maxCol) - dist);
        pincel.drawLine (0, maxH + dist, dim.width, maxH + dist);

        pincel.dispose();


    }

    
    
    private List<Integer> lista;
    private List<Color> coresIndices;
    private String nome;
}
