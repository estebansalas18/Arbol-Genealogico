    
package segundapractica.code;

  /**
 *
 * @author Sebastián Gutiérrez 
 */

import java.awt.*;
import java.util.*;
import javax.swing.*;


public class ArbolExpresionGrafico extends JPanel 
{
    private Arbol miArbol;
    private HashMap posicionNodos = null;
    private HashMap subtreeSizes = null;
    private boolean dirty = true;
    private int parent2child = 20, child2child = 80;
    private Dimension empty = new Dimension(0,0);
    private FontMetrics fm = null;
    
    
    /**
     * Constructor de la clase ArbolExpresionGrafico.
     * El constructor permite inicializar los atributos de la clase ArbolExpresionGrafico
     * y llama al método repaint(), que es el encargado de pintar el Arbol.
     */
    public ArbolExpresionGrafico(Arbol miArbol) 
    {
          this.miArbol = miArbol;
          this.setBackground(Color.WHITE);
          posicionNodos = new HashMap();
          subtreeSizes = new HashMap();
          dirty = true;
          repaint();      
    }


    /**
     * Calcula las posiciones de los respectivos subárboles y de cada nodo que 
     * forma parte de ese subárbol, para conocer en que posición van a ir dibujados
     * los rectángulos representativos del árbol de la expresión.
     */
    private void calcularPosiciones() 
    {
         posicionNodos.clear();
         subtreeSizes.clear();
         Nodo root = this.miArbol.getRaiz();
         if (root != null) 
         {
             calcularTamañoSubarbol(root);
             calcularPosicion(root, Integer.MAX_VALUE, Integer.MAX_VALUE, 155);
         }
    }
    
    /**
     * Calcula el tamaño de cada subárbol y lo agrega al objeto subtreeSizes de la clase
     * de tipo HashMap que va a contener la coleccion de todos los 
     * subárboles que contiene un arbol.
     * @param n:Objeto de la clase NodoB <T> que se utiliza como
     * referencia calcular el tamaño de cada subárbol.
     * @return Dimension con el tamaño de cada subárbol.
     */
    private Dimension calcularTamañoSubarbol(Nodo n) 
    {
          if (n == null) 
              return new Dimension(0,0);
 
          Dimension dad = calcularTamañoSubarbol(n.getPadre());
          Dimension mom = calcularTamañoSubarbol(n.getMadre());
          
          int h = fm.getHeight() + parent2child + Math.max(dad.height, mom.height);
          int w = dad.width + child2child + mom.width;
          
          Dimension d = new Dimension(w, h);
          subtreeSizes.put(n, d);
          
          return d;
    }
    
    
    /**
     * Calcula la ubicación de cada nodo de cada subárbol y agrega cada nodo con 
     * un objeto de tipo Rectangule que tiene la ubicación y la información específica de dónde 
     * va a ser dibujado.
     * @param n: Objeto de tipo NodoB <T> que se utiliza como
     * referencia para calcular la ubicación de cada nodo.
     * @param left: int con alineación y orientación a la izquierda.
     * @param right: int con alineación y orientación a la derecha.
     * @param top: int con el tope.
     */
    private void calcularPosicion(Nodo n, int left, int right, int top) 
    {
        if (n == null) 
            return;

        Dimension dad = (Dimension) subtreeSizes.get(n.getPadre());
        if (dad == null) 
            dad = empty;

        Dimension mom = (Dimension) subtreeSizes.get(n.getMadre());
        if (mom == null) 
            mom = empty;

        int center = 0;

        if (right != Integer.MAX_VALUE)
            center = right - mom.width - child2child/2;
        else if (left != Integer.MAX_VALUE)
            center = left + dad.width + child2child/2;
        int width = fm.stringWidth(n.getPersona().getFechaNac()+"Nac:    :");

        posicionNodos.put(n,new Rectangle(center - width/2 - 3, top*3, width + 6, fm.getHeight()*5));

        calcularPosicion(n.getPadre(), Integer.MAX_VALUE, center - child2child/2, top - fm.getHeight() - parent2child);
        calcularPosicion(n.getMadre(), center + child2child/2, Integer.MAX_VALUE, top - fm.getHeight() - parent2child);
    }
    
    /**
     * Dibuja el árbol teniendo en cuenta las ubicaciones de los nodos y los 
     * subárboles calculadas anteriormente.
     * @param g: Objeto de la clase Graphics2D que permite realizar el dibujo de las líneas, rectangulos y del String de la información que contiene el Nodo.
     * @param n: Objeto de la clase NodoB <T> que se utiliza como referencia para dibujar el árbol.
     * @param puntox: int con la posición en x desde donde se va a dibujar la línea hasta el siguiente hijo.
     * @param puntoy: int con la posición en y desde donde se va a dibujar la línea hasta el siguiente hijo.
     * @param yoffs: int con la altura del FontMetrics.
     */
    private void dibujarArbol(Graphics2D g, Nodo n, int puntox, int puntoy, int yoffs) 
    {
        if (n == null) 
            return;

        Rectangle r = (Rectangle) posicionNodos.get(n);
        g.draw(r);
        g.drawString(n.getPersona()+" ", r.x + 3, r.y + yoffs);
        g.drawString("Genero: " + n.getPersona().getGenero(), r.x + 3, r.y + yoffs*2);
        g.drawString("Edad: " + n.getPersona().getEdad(), r.x + 3, r.y + yoffs*3);
        g.drawString("Ciudad: " + n.getPersona().getCiudad(), r.x + 3, r.y + yoffs*4);
        g.drawString("Nac: " + n.getPersona().getFechaNac(), r.x + 3, r.y + yoffs*5);
        g.drawString("Fal: " + n.getPersona().getFechaFal(), r.x + 3, r.y + yoffs*6);

        if (puntox != Integer.MAX_VALUE)

        g.drawLine(puntox, puntoy, (int)(r.x + r.width/2), r.y + r.height);

        dibujarArbol(g, n.getPadre(), (int)(r.x + r.width/2), r.y, yoffs);
        dibujarArbol(g, n.getMadre(), (int)(r.x + r.width/2), r.y, yoffs);
     
   }
    

   /**
     * Sobreescribe el metodo paint y se encarga de pintar todo el árbol.
     * @param g: Objeto de la clase Graphics.
     */
    @Override
   public void paint(Graphics g) 
   {
        super.paint(g);
        fm = g.getFontMetrics();

        if (dirty) 
        {
          calcularPosiciones();
          dirty = false;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(getWidth() / 2, parent2child);
        dibujarArbol(g2d, this.miArbol.getRaiz(), Integer.MAX_VALUE, Integer.MAX_VALUE, 
                 fm.getLeading() + fm.getAscent());
        fm = null;
   }
   
 }




