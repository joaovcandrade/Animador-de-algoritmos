package br.unifil.dc.lab2;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.*;
import java.util.ListIterator;

/**
 * Write a description of class Tocador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tocador extends JPanel {

    public enum Posicao {
        centro, meio;

    }

    public Tocador(ListIterator<Transparencia> quadrosFilme) {
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        carregarFilme(quadrosFilme);
    }
    public Tocador() {
        this(null);
    }
    
    public void carregarFilme(ListIterator<Transparencia> quadrosFilme) {
        this.quadrosFilme = quadrosFilme;
        this.quadroAtual = null;
        numQuadro = 0;
    }
    
    public void avancarFilme() {
        if (quadrosFilme.hasNext()) {
            quadroAtual = quadrosFilme.next();
            numQuadro++;
        }
    }
    
    public void voltarFilme() {
        if (quadrosFilme.hasPrevious()) {
            quadroAtual = quadrosFilme.previous();
            numQuadro--;
        }
    }
    
    public void rebobinarFilme() {
        while (quadrosFilme.hasPrevious()) {
            quadroAtual = quadrosFilme.previous();
            numQuadro--;
        }
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D pincel = (Graphics2D) g;

        // ESCREVER NO CANTO INFERIOR DIREITO DA TELA "Quadro 'numQuadro'"
        pincel.setFont(new Font("Consolas Sans", Font.PLAIN, 15));
        String msg = "Quadro "+ numQuadro;
        FontMetrics fmt = pincel.getFontMetrics();
        int sizFont = - fmt.stringWidth(msg)*-1;
        pincel.drawString(msg, getWidth() - sizFont, getHeight() - 5);
        //throw new UnsupportedOperationException("O aluno ainda não implementou essa funcionalidade.");
        
        if (quadroAtual != null) {
            quadroAtual.pintar(pincel, this);
        } else {
            // ESCREVE NO MEIO DA TELA "O Filme ainda não iniciou."
            pincel.setFont(new Font("Consolas Sans", Font.PLAIN, 30));
            msg = "O Filme ainda não iniciou.";
            fmt = pincel.getFontMetrics();
            sizFont = - fmt.stringWidth(msg)*-1;
            System.out.println(sizFont);
            pincel.drawString("O Filme ainda não iniciou.", (getWidth()/2) - (sizFont/2), getHeight()/2);
            //throw new UnsupportedOperationException("O aluno ainda não implementou essa funcionalidade.");
        }

    }


    
    private int numQuadro = 0;
    private Transparencia quadroAtual = null;
    private ListIterator<Transparencia> quadrosFilme = null;
}
