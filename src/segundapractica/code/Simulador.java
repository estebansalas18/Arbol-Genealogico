package segundapractica.code;

import javax.swing.JPanel;

public class Simulador {
    Arbol miArbol;

    public Simulador(Nodo p) {
        miArbol = new Arbol(p);
    }
    
    public void insertar(int i, Nodo x, Nodo y){
        this.miArbol.asignar(i, x, y);
    }

    public JPanel getDibujo() {
        return this.miArbol.getdibujo();
    }
}
