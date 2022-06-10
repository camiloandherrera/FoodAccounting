package funcionalidades;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.management.openmbean.CompositeData;

class Producto {
    protected String nombre;
    protected String tipo;
    protected Integer valor;

    // Constructor
    Producto(String nombre, String tipo, Integer valor) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.valor = valor;
    }

    // Encapsulamiento
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getValor() {
        return valor;
    }
    public void setValor(Integer valor) {
        this.valor = valor;
    }  
}

class NodoProducto{
    protected Producto dato;

    protected NodoProducto padre;
    protected NodoProducto primerHijo;
    protected List <NodoProducto> hijos;

    // Constructor
    NodoProducto(Producto dato) {
        this.dato = dato;
        this.hijos = new LinkedList <NodoProducto>();
    }

    // Métodos
    static void LevelOrderTraversal(NodoProducto raiz)  {
        if (raiz == null)
            return;
        // Standard level order traversal code using queue
        Queue<NodoProducto> colaNodo = new LinkedList<>(); // Create a queue
        colaNodo.add(raiz); // Enqueue root
        while (!colaNodo.isEmpty())
        {
            int n = colaNodo.size();
            // If this node has children
            while (n > 0)
            {
                // Dequeue an item from queue and print it
                NodoProducto nodoTemp = colaNodo.peek();
                colaNodo.remove();
                System.out.print(nodoTemp.dato.getNombre() + " ");
                // Enqueue all children of the dequeued item
                for (int i = 0; i < nodoTemp.hijos.size(); i++)
                    colaNodo.add(nodoTemp.hijos.get(i));
                n--;
            }
            // Print new line between two levels
            System.out.println();
        }
    }

    static boolean contiene(Producto producto, NodoProducto raiz) {
        if (raiz == null)
            return false;
            
        Queue<NodoProducto> colaNodo = new LinkedList<>();
        
        return false;
    }
}

public class Productos_temp {
    public static void main(String[] args) {
        NodoProducto comida = new NodoProducto(new Producto("Comida", "Comida", 0));

        NodoProducto hamburguesa = new NodoProducto(new Producto("Hamburguesa", "Comida", 2000));
        NodoProducto perro = new NodoProducto(new Producto("Perro", "Comida", 1500));
        NodoProducto empanada = new NodoProducto(new Producto("Empanada", "Comida", 1000));
        comida.hijos.add(hamburguesa);
        comida.hijos.add(perro);
        comida.hijos.add(empanada);

        NodoProducto hamburgesaDoble = new NodoProducto(new Producto("Hamburguesa Doble", "Hamburguesa", 2350));
        hamburguesa.hijos.add(hamburgesaDoble);

        NodoProducto perroTocineta = new NodoProducto(new Producto("Perro Tocineta", "Perro", 1750));
        NodoProducto perroChilliDog = new NodoProducto(new Producto("Perro ChilliDog", "Perro", 1750));
        perro.hijos.add(perroTocineta);
        perro.hijos.add(perroChilliDog);

        NodoProducto empanadaQueso = new NodoProducto(new Producto("Empanada Queso", "Empanada", 1050));
        NodoProducto empanadaPepperoni = new NodoProducto(new Producto("Empanada Pepperoni", "Empanada", 1050));
        NodoProducto empanadaRanchera = new NodoProducto(new Producto("Empanada Ranchera", "Empanada", 1150));
        NodoProducto empanadaPolloChampi = new NodoProducto(new Producto("Empanada Pollo Champiñones", "Empanada", 1050));
        empanada.hijos.add(empanadaQueso);
        empanada.hijos.add(empanadaPepperoni);
        empanada.hijos.add(empanadaRanchera);
        empanada.hijos.add(empanadaPolloChampi);

        NodoProducto hamburguesaDobleTocineta = new NodoProducto(new Producto("Hamburguesa Doble Tocineta", "Hamburguesa Doble", 2550));
        hamburgesaDoble.hijos.add(hamburguesaDobleTocineta);


        NodoProducto.LevelOrderTraversal(comida);
    }
}
