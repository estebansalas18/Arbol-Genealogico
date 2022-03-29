package segundapractica.code;

import java.util.ArrayList;
import javax.swing.JPanel;

public class Arbol {
    private Nodo raiz;
    
    private Nodo nodoAux;
    private int intAux;
    private ArrayList<Nodo> fallecidos = new ArrayList<>();
    private ArrayList<Nodo> antepasados = new ArrayList<>();
    private ArrayList<Nodo> arbol = new ArrayList<>();

    public Arbol(Nodo raiz) {
        this.raiz = raiz;
    }
    
    public Nodo getRaiz(){
        return this.raiz;
    }
    
    public void setRaiz(Nodo x){
        this.raiz = x;
    }
    
    public void asignar(int i, Nodo r, Nodo x){
        if(r != null){
            switch(i){
                case 1 -> {
                    r.setPadre(x);
                    x.setHijo(r);
                }
                case 2 -> {
                    r.setMadre(x);
                    x.setHijo(r);
                }
                case 3 -> {
                    r.setHijo(x);
                    if(x.getPersona().getGenero() == 'M'){
                        x.setPadre(r);
                        if(r.getHijos() >= 1){
                            for(int j = 0; j < r.getHijos(); j++){
                                if(r.getHijo(j) != x){
                                    x.setMadre(r.getHijo(j).getMadre());
                                    break;
                                }
                            }
                        }
                    }
                    if(x.getPersona().getGenero() == 'F'){
                        x.setMadre(r);
                        if(r.getHijos() >= 1){
                            for(int j = 0; j < r.getHijos(); j++){
                                if(r.getHijo(j) != x){
                                    x.setPadre(r.getHijo(j).getPadre());
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public ArrayList<Nodo> recorrer(Nodo actual, Nodo anterior){
        if(actual != null){
            if(!arbol.contains(actual)){
                arbol.add(actual);
            }else{
                if(anterior != null){
                    if(actual.getPadre() != anterior){
                        recorrer(actual.getPadre(), actual);
                    }
                    if(actual.getMadre() != anterior){
                        recorrer(actual.getMadre(), actual);
                    }
                    for(int i = 0; i < actual.getHijos(); i++){
                        if(actual.getHijo(i) != anterior){
                            recorrer(actual.getHijo(i), actual);
                        }
                    }
                }
                else{
                    recorrer(actual.getPadre(), actual);
                    recorrer(actual.getMadre(), actual);
                    for(int i = 0; i < actual.getHijos(); i++){
                        recorrer(actual.getHijo(i), actual);
                    }
                }  
            }     
        }
        return arbol;
    }

    public Nodo buscar(Persona buscado, Nodo actual, Nodo anterior){
        if(actual != null){
            if(actual.getPersona() == buscado){
                nodoAux = actual;
            }
            else{
                if(anterior != null){
                    if(actual.getPadre() != anterior){
                        buscar(buscado, actual.getPadre(), actual);
                    }
                    if(actual.getMadre() != anterior){
                        buscar(buscado, actual.getMadre(), actual);
                    }
                    for(int i = 0; i < actual.getHijos(); i++){
                        if(actual.getHijo(i) != anterior){
                            buscar(buscado, actual.getHijo(i), actual);
                        }
                    }
                }
                else{
                    buscar(buscado, actual.getPadre(), actual);
                    buscar(buscado, actual.getMadre(), actual);
                    for(int i = 0; i < actual.getHijos(); i++){
                        buscar(buscado, actual.getHijo(i), actual);
                    }
                }
            }
        }
        return nodoAux;
    }
    
    public Nodo buscarNodo(Persona buscado){
        if(buscado == raiz.getPersona()){
            return raiz;
        }else{
            nodoAux = null;
            return buscar(buscado, raiz, null);
        }
    }
    
    public void reiniciarAux(){
        this.intAux = 0;
        this.nodoAux = null;
        this.fallecidos = new ArrayList<>();
        this.antepasados = new ArrayList<>();
    }
    
    public int contarPersonas(Nodo actual, Nodo anterior){
        if(actual != null){
            intAux += 1;
            if(anterior != null){
                if(actual.getPadre() != anterior){
                    contarPersonas(actual.getPadre(), actual);
                }
                if(actual.getMadre() != anterior){
                    contarPersonas(actual.getMadre(), actual);
                }
                for(int i = 0; i < actual.getHijos(); i++){
                    if(actual.getHijo(i) != anterior){
                        contarPersonas(actual.getHijo(i), actual);
                    }
                }
            }
            else{
                contarPersonas(actual.getPadre(), actual);
                contarPersonas(actual.getMadre(), actual);
                for(int i = 0; i < actual.getHijos(); i++){
                    contarPersonas(actual.getHijo(i), actual);
                }
            }
        }
        return intAux;
    }
    
    public int contarGenero(char genero, Nodo actual, Nodo anterior){
        if(actual != null){
            if(actual.getPersona().getGenero() == genero){
                intAux += 1;
            }
            if(anterior != null){
                if(actual.getPadre() != anterior){
                    contarGenero(genero, actual.getPadre(), actual);
                }
                if(actual.getMadre() != anterior){
                    contarGenero(genero, actual.getMadre(), actual);
                }
                for(int i = 0; i < actual.getHijos(); i++){
                    if(actual.getHijo(i) != anterior){
                        contarGenero(genero, actual.getHijo(i), actual);
                    }
                }
            }
            else{
                contarGenero(genero, actual.getPadre(), actual);
                contarGenero(genero, actual.getMadre(), actual);
                for(int i = 0; i < actual.getHijos(); i++){
                    contarGenero(genero, actual.getHijo(i), actual);
                }
            }
        }
        return intAux;
    }
    
    public ArrayList<Nodo> contarFallecidos(Nodo actual, Nodo anterior){
        if(actual != null){
            if(actual.getPersona().getFechaFal() != null){
                fallecidos.add(actual);
            }
            if(anterior != null){
                if(actual.getPadre() != anterior){
                    contarFallecidos(actual.getPadre(), actual);
                }
                if(actual.getMadre() != anterior){
                    contarFallecidos(actual.getMadre(), actual);
                }
                for(int i = 0; i < actual.getHijos(); i++){
                    if(actual.getHijo(i) != anterior){
                        contarFallecidos(actual.getHijo(i), actual);
                    }
                }
            }
            else{
                contarFallecidos(actual.getPadre(), actual);
                contarFallecidos(actual.getMadre(), actual);
                for(int i = 0; i < actual.getHijos(); i++){
                    contarFallecidos(actual.getHijo(i), actual);
                }
            }
        }
        return fallecidos;
    }
    
    public ArrayList<Nodo> contarAntepasados(Nodo actual, Nodo anterior){
        if(actual != null){
            antepasados.add(actual);
            if(anterior != null){
                if(actual.getPadre() != anterior){
                    contarAntepasados(actual.getPadre(), actual);
                }
                if(actual.getMadre() != anterior){
                    contarAntepasados(actual.getMadre(), actual);
                }
            }
            else{
                contarAntepasados(actual.getPadre(), actual);
                contarAntepasados(actual.getMadre(), actual);
            }
        }else{
            antepasados.add(null);
        }
        return antepasados;
    }
    
    public JPanel getdibujo() {
        return new ArbolExpresionGrafico(this);
    }
}
