package funcionalidades.test_clases;

import java.util.List;
import java.util.Random;
import java.util.LinkedList;
import java.util.Scanner;

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
    protected NodoProducto siguiente;

    // Constructores
    // Por defecto
    public NodoProducto(Producto dato) {
        this.dato = dato;
    }

    // Indicando siguiente
    public NodoProducto(Producto dato, NodoProducto siguiente) {
        this.dato = dato;
        this.siguiente = siguiente;
    }

    // Encapsulamiento
    public Producto getDato() {
        return dato;
    }
    public void setDato(Producto dato) {
        this.dato = dato;
    }

    public NodoProducto getSiguiente() {
        return siguiente;
    }
    public void setSiguiente(NodoProducto siguiente) {
        this.siguiente = siguiente;
    }
}

class ListaProductos {
    protected int tamanoLista;
    protected NodoProducto cabeza; 
    protected NodoProducto cola;

    // Constructor, lista vacía por defecto
    public ListaProductos() {
        cabeza = null;
        cola = null;

        tamanoLista = 0;
    }
    
    // Métodos
    public void insertar(Producto valor) {
        NodoProducto nodoNuevo = new NodoProducto(valor);
        nodoNuevo.setSiguiente(null);

        // Si la lista está vacía, el nodo nuevo será la cabeza y cola
        if (cabeza == null) {
            cabeza = nodoNuevo;
            hacerCola(nodoNuevo);
        }
        // Sino, recorrerá hasta el final de la lista e insertará el nuevo nodo allí
        else {
            NodoProducto ultimo = cabeza;
            while(ultimo.getSiguiente() != null)
                ultimo = ultimo.getSiguiente();

            ultimo.setSiguiente(nodoNuevo);
            hacerCola(nodoNuevo);
        }

        tamanoLista++;
    }

    public boolean buscar(String valor) {
        NodoProducto actual = cabeza;

        while (actual != null) {
            if (actual.getDato().getNombre().equals(valor)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public void remover(Producto valor)
    {
        NodoProducto nodoActual = cabeza;
        NodoProducto anterior = null;

        // Si la cabeza contiene el valor a vorrar, cambiar cabeza
        if (nodoActual != null && nodoActual.getDato().getNombre().equals(valor.getNombre()))
            cabeza = nodoActual.getSiguiente();
        else {
            // Si el valor está en otro lado, buscarlo
            while (nodoActual != null && !nodoActual.getDato().getNombre().equals(valor.getNombre())) {
                anterior = nodoActual;
                nodoActual = nodoActual.getSiguiente();
            }
            // Si el valor está presente, debería encontrarse en nodoActual; no debería ser null
            if (nodoActual != null) 
                // Si el valor está en nodoActual, remover de la lista
                anterior.setSiguiente(nodoActual.getSiguiente());
            hacerCola(anterior); // Revisar si el valor está en la cola

            // Si el valor no está presente, nodoActual será null
            if (nodoActual == null) 
                System.out.println(valor.getNombre() + " no encontrado!");
                return;
             }
        tamanoLista--;
    }

    public boolean estaVacia() {
        return tamanoLista == 0; // Si tamanoLista == 0, retorna true
    }

    public void hacerCola(NodoProducto nodo) {
        if (nodo.getSiguiente() == null)
            cola = nodo;
    }

    public void imprimirLista() {
        NodoProducto actual = cabeza;

        System.out.println("Tamaño de Lista: " + tamanoLista);
        System.out.println("Lista: ");
        // Traverses the LinkedList, printing the getDato() at the current NodoProducto, then moving on
        while (actual != null) {
            System.out.println("- Nombre: " + actual.getDato().getNombre() +
            " | Tipo: " + actual.getDato().getTipo() + " | Valor: " + actual.getDato().getValor());
            actual = actual.getSiguiente();
        }
    }

    // Encapsulamiento
    public int getTamanoLista() {
        return tamanoLista;
    }
    public void setTamanoLista(int tamanoLista) {
        this.tamanoLista = tamanoLista;
    }

    public NodoProducto getCabeza() {
        return cabeza;
    }
    public void setCabeza(NodoProducto cabeza) {
        this.cabeza = cabeza;
    }

    public NodoProducto getCola() {
        return cola;
    }
    public void setCola(NodoProducto cola) {
        this.cola = cola;
    }
}

public class Productos_test {
    protected List <ListaProductos> productos = new LinkedList<ListaProductos>();
    protected int numeroSublistas = 0;

    public void anadirProducto(Producto producto) { // Crea una lista o inserta un producto en una existente, dependiendo del producto
        if (productos.isEmpty()){ // Inserta si la lista de sublistas está vacía
            ListaProductos listaProd = new ListaProductos();
            productos.add(listaProd); // Añade sublista creada a lista
            listaProd.insertar(producto); // Añade producto a sublista
            numeroSublistas++;
        }
        else if (!productos.isEmpty()) {
            boolean existe = false; // Si no está vacía, busca si el tipo de valor existe en alguna sublista
            int dondeExiste = Integer.MIN_VALUE;
            for (int i = 0; i < productos.size(); i++) {
                if (productos.get(i).buscar(producto.getTipo()) == true) {
                    existe = true;
                    dondeExiste = i;
                    break;
                }
            }
            if (existe == true) {
                productos.get(dondeExiste).insertar(producto);
            }
            else { // Si no existe, crea una nueva sublista
                ListaProductos nuevaListaProductos = new ListaProductos();
                nuevaListaProductos.insertar(producto);
                productos.add(nuevaListaProductos);
                numeroSublistas++;
            }
        }
    }

    public void removerProducto(Producto producto){
        if (productos.isEmpty()) {
            System.out.println("No hay productos para quitar");
            return;
        }
        else if (!productos.isEmpty()) {
            boolean existe = false; // Si no está vacía, busca si el tipo de valor existe en alguna sublista
            int dondeExiste = Integer.MIN_VALUE;
            for (int i = 0; i < productos.size(); i++) {
                if (productos.get(i).buscar(producto.getNombre()) == true) {
                    existe = true;
                    dondeExiste = i;
                    break;
                }
            }

            if (existe == true) {
                productos.get(dondeExiste).remover(producto);
            }
            else {
                System.out.println("No existe el producto a quitar");
                return;
            }

            if (productos.get(dondeExiste).estaVacia())
                productos.remove(dondeExiste); // Si la lista donde estaba el producto queda vacía, se elimina
        }
    }

    public void vaciarListas() {
        productos = new LinkedList<ListaProductos>();
        System.out.println("Listas eliminadas");
    }

    public void imprimirListas() {
        Scanner scPrnt = new Scanner(System.in);
        int seleccion;

        System.out.println("¿Cómo desea imprimir la lista?");
        System.out.print("1- Completa\n2- Resumida\nOtro- Salir\nSelección: ");
        seleccion = scPrnt.nextInt();

        switch (seleccion) {
            case 1:
                for (int i = 0; i < productos.size(); i++){
                    System.out.println("\nNúmero de sublistas: " + numeroSublistas);
                    System.out.println("Sublista " + (i+1) + " : ");
                    productos.get(i).imprimirLista();
                }
            break;
            case 2:
                for (int i = 0; i < productos.size(); i++){
                    System.out.println("\nNúmero de sublistas: " + numeroSublistas);
                    System.out.println("Sublista " + (i+1) + ", Primer elemento: " + productos.get(i).getCabeza().getDato().getNombre());
                    scPrnt.close();
                }
            break;
            default:
                scPrnt.close();
                return;
        }
    }

    static final int LONGITUD_STRING_SALIDA = 5;
    // Main debug
    public static void main(String[] args) {

        // Caracteres utilizados para la generación de strings aleatorios
        String strCaracteresPermitidos = "ABCXYZ";

        // Inicializar Random
        Random random = new Random();

        // Datos a analizar en las pruebas
        long DATOS_A_ANALIZAR = 1000000;

        // Medir tiempo
        long comienzoT, finalT;
        comienzoT = System.currentTimeMillis();

        Productos_test manejoProductos = new Productos_test(); // Objeto para realizar el manejo de productos
        for (long i = 0; i < DATOS_A_ANALIZAR; i++) {
            String inputNombre = getStringAleatorio(strCaracteresPermitidos, random);
            String inputTipo = getStringAleatorio(strCaracteresPermitidos, random);
            int inputValor = (int)(Math.random() * (5000 - 1000) + 1000);

            manejoProductos.anadirProducto(new Producto(inputNombre, inputTipo, inputValor));
        }
        finalT = System.currentTimeMillis();

        System.out.println("La inserción de los datos tomó " + (finalT - comienzoT) + " ms");
    }

    private static String getStringAleatorio(String strCaracteresPermitidos, Random random) {
        StringBuilder sbStringAleatorio = new StringBuilder();
        
        for(int i = 0 ; i < LONGITUD_STRING_SALIDA; i++){
            // Obtener número aleatorio entre 0 y la longitud del String
            int intAleatorio = random.nextInt(strCaracteresPermitidos.length());
            // Obtener un caracter aleatorio en el String y juntarlo en sbStringAleatorio
            sbStringAleatorio.append( strCaracteresPermitidos.charAt(intAleatorio) );
        }
        return sbStringAleatorio.toString();
    }
}
