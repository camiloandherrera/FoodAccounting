package funcionalidades_pt2;

import java.util.Scanner;

class Cuenta {
    protected Integer valor;
    protected String descripcion;

    // Constructor
    public Cuenta(Integer valor, String descripcion) {
        this.valor = valor;
        this.descripcion = descripcion;
    }

    // Encapsulamiento
    public Integer getValor() {
        return valor;
    }
    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

class PilaCuentas { // Una pila será utilizada para llevar cuentas de dinero neto (ganancia o adeudado)
    protected NodoPila raiz;
    protected Integer valorNeto = 0;

    static class NodoPila {
        protected Integer dato;
        protected NodoPila siguiente;
 
        NodoPila(int dato) {
            this.dato = dato;
        }

        // Encapsulamiento
        public Integer getDato() {
            return dato;
        }
        public void setDato(Integer dato) {
            this.dato = dato;
        }

        public NodoPila getSiguiente() {
            return siguiente;
        }
        public void setSiguiente(NodoPila siguiente) {
            this.siguiente = siguiente;
        }
    }
 
    public boolean estaVacia() {
        if (raiz == null)
            return true;
        else
            return false;
    }
 
    public void apilar(int dato) {
        NodoPila nodoNuevo = new NodoPila(dato);
 
        if (raiz == null) {
            raiz = nodoNuevo;
        }
        else {
            NodoPila temp = raiz;
            raiz = nodoNuevo;
            nodoNuevo.siguiente = temp;
        }
        valorNeto = valorNeto + nodoNuevo.dato;
        System.out.println(dato + " apilado al stack");
    }
 
    public int desapilar() {
        int popped = Integer.MIN_VALUE;
        if (raiz == null) {
            System.out.println("Stack vacía");
        }
        else {
            popped = raiz.dato;
            raiz = raiz.siguiente;
        }
        valorNeto = valorNeto - popped; 
        return popped;
    }
 
    public int frente() {
        if (raiz == null) {
            System.out.println("Stack vacía");
            return Integer.MIN_VALUE;
        }
        else {
            return raiz.dato;
        }
    }

    // Encapsulamiento
    public NodoPila getRaiz() {
        return raiz;
    }
    public void setRaiz(NodoPila raiz) {
        this.raiz = raiz;
    }

    public Integer getValorNeto() {
        return valorNeto;
    }
    public void setValorNeto(Integer valorNeto) {
        this.valorNeto = valorNeto;
    }
}

class ListaCuentas { // Una lista enlazada será utilizada para manejar las cuentas
    protected int tamanoLista;
    protected NodoLista cabeza; 
    protected NodoLista cola;

    static class NodoLista {
        protected Cuenta valor; 
        protected NodoLista siguiente;
        // Constructores
        // Por defecto
        NodoLista(Cuenta valor) {
            this.valor = valor;
            siguiente = null;
        }
        // Indicando siguiente
        NodoLista(Cuenta valor, NodoLista siguiente) {
            this.valor = valor;
            this.siguiente = siguiente;
        }
    
        // Encapsulamiento
        public Cuenta getValor() {
            return valor;
        }
        public void setValor(Cuenta valor) {
            this.valor = valor;
        }

        public NodoLista getSiguiente() {
            return siguiente;
        }
        public void setSiguiente(NodoLista siguiente) {
            this.siguiente = siguiente;
        }
    }

    // Constructor, lista vacía por defecto
    public ListaCuentas() {
        cabeza = null;
        cola = null;

        tamanoLista = 0;
    }

    // Métodos
    public void insertar(Cuenta valor) {
        NodoLista nodoNuevo = new NodoLista(valor);
        nodoNuevo.setSiguiente(null);

        // Si la lista es vacía, el nodo insertado será cabeza y cola
        if (cabeza == null) {
            cabeza = nodoNuevo;
            hacerCola(nodoNuevo);
        }
        // Sino, se buscará el final e insertará el nodo allí
        else {
            NodoLista last = cabeza;
            while(last.getSiguiente() != null)
                last = last.getSiguiente();

            last.setSiguiente(nodoNuevo);
            hacerCola(nodoNuevo);
        }
        tamanoLista++;
    }

    public void removerCola() {
        NodoLista nodoAnterior = cola;
        nodoAnterior.setSiguiente(null);

        // No hacer nada si la lista está vacía
        if (cabeza == null) {
            System.out.println("Lista vacía\n");
            return;
        }
        // Sino, recorrer hasta encontrar el nodo final, y removerlo; hacer el anterior  a este la nueva cola
        else {
            NodoLista last = cabeza;
            while(last.getSiguiente() != cola)
                last = last.getSiguiente();

            last.setSiguiente(null); // Inserts the New NodoLista at the end
            hacerCola(nodoAnterior);
        }
        tamanoLista--;
    }

    public void imprimirLista() {
        NodoLista actual = cabeza;

        System.out.println("Tamaño de lista: " + tamanoLista);
        System.out.println("Lista de movimientos: ");
        while (actual != null) {
            System.out.println("Valor: " + actual.getValor().getValor() + " | Descripción: " + actual.getValor().getDescripcion());
            System.out.println("-----------------------------------------------------------------------------------------");
            actual = actual.getSiguiente();
        }
    }

    public void hacerCola(NodoLista nodo) { // Si el nodo siguiente es null, el nodo actual será la cola
        if (nodo.getSiguiente() == null)
            cola = nodo;
    }

    public boolean estaVacia() {
        return tamanoLista == 0; // SI tamanoLista == 0, retorna true
    }

    // Encapsulamiento
    public int getTamanoLista() {
        return tamanoLista;
    }
    public void setTamanoLista(int tamanoLista) {
        this.tamanoLista = tamanoLista;
    }

    public NodoLista getCabeza() {
        return cabeza;
    }
    public void setCabeza(NodoLista cabeza) {
        this.cabeza = cabeza;
    }

    public NodoLista getCola() {
        return cola;
    }
    public void setCola(NodoLista cola) {
        this.cola = cola;
    }
}

public class ManejoDinero {

    public static void vaciarPila(PilaCuentas pila){
        while (pila.getRaiz() != null && !pila.estaVacia()) {
            pila.desapilar();
        }
    }

    public static ListaCuentas vaciarLista(ListaCuentas lista) {
        if (!lista.estaVacia())
            lista = new ListaCuentas();

        return lista;
    }

    // Main debug
    public static void main(String args[]) {
        Scanner scTxT = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);

        PilaCuentas cuentas = new PilaCuentas();
        ListaCuentas listaDeCuentas = new ListaCuentas();

        while (true){
            System.out.println("\nCuenta Neta: " + cuentas.getValorNeto());
            System.out.println("¿Qué desea hacer?");
            System.out.println("1- Hacer una cuenta\n2- Deshacer última cuenta\n3- Imprimir cuentas hechas\n4- Vaciar cuentas\n5- Salir");
            System.out.print("Selección: ");
            int input = sc.nextInt();

            switch(input)
            {
                case 1:
                System.out.print("Escriba la cuenta a sumar (o restar): ");
                int inputNum = sc.nextInt();
                System.out.print("Escriba la descripción de la cuenta: ");
                String inputDesc = scTxT.nextLine();
                
                
                cuentas.apilar(inputNum);
                listaDeCuentas.insertar(new Cuenta(inputNum, inputDesc));
                break;

                case 2:
                cuentas.desapilar();
                listaDeCuentas.removerCola();
                break;

                case 3:
                listaDeCuentas.imprimirLista();
                break;

                case 4:
                    vaciarPila(cuentas);
                    listaDeCuentas = vaciarLista(listaDeCuentas);
                break;

                case 5:
                sc.close();
                scTxT.close();
                return;

                default:
                break;
            }
        }
    }
    
}


