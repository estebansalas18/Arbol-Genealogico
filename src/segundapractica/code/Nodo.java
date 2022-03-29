package segundapractica.code;

import java.util.ArrayList;

public class Nodo {
    private Persona persona;
    private Nodo padre;
    private Nodo madre;
    private ArrayList<Nodo> hijos;

    public Nodo(Persona persona) {
        this.persona = persona;
        this.padre = null;
        this.madre = null;
        this.hijos = new ArrayList<>();
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public Nodo getMadre() {
        return madre;
    }

    public void setMadre(Nodo madre) {
        this.madre = madre;
    }

    public Nodo getHijo(int i) {
        return hijos.get(i);
    }

    public void setHijo(Nodo x) {
        this.hijos.add(x);
    }
    
    public int getHijos(){
        return hijos.size();
    }

}
