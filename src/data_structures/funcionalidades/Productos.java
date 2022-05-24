package funcionalidades;

class NodoLista {
    private Turno valor; // Valor para guardar (un turno/cliente)
    private NodoLista siguiente; // Apuntador al Nodo siguiente
    private NodoLista anterior; // Apuntador al Nodo anterior
    // Constructores
    // Constructor por defecto del Nodo
    NodoLista(Turno valor) {
        this.valor = valor;
        anterior = null;
        siguiente = null;
    }
    // Constructor indicando el Nodo siguiente y anterior
    NodoLista(Turno valor, NodoLista siguiente, NodoLista anterior) {
        this.valor = valor;
        this.siguiente = siguiente;
        this.anterior = anterior;
    }

    // Encapsulamiento
    public Turno getValor() {
        return valor;
    }
    public NodoLista getAnterior() {
        return anterior;
    }
    public NodoLista getSiguiente() {
        return siguiente;
    }

    public void setValor(Turno valor) {
        this.valor = valor;
    }
    public void setanterior(NodoLista anterior) {
        this.anterior = anterior;
    }
    public void setSiguiente(NodoLista siguiente) {
        this.siguiente = siguiente;
    }
}


public class Productos {
    protected int tamanoLista;
    protected NodoLista cabeza; 
    protected NodoLista cola; //* cola de la LinkedList

    // Constructor por defecto; crea una lista vacía
    public Productos() {
        cabeza = null;
        cola = null;

        tamanoLista = 0;
    }

    // Métodos
    // Inserta un nuevo nodo al final (cola) de la lista
    public void insertar(Turno valor) {
        NodoLista nodoNuevo = new NodoLista(valor);
        nodoNuevo.setSiguiente(null);

        // Si la lista está vacía, hacer que el Nodo nuevo sea la cabeza
        if (cabeza == null) {
            cabeza = nodoNuevo;
            makeTail(nodoNuevo);
        }
        // Sino, atravesar la lista hasta encontrar el final; insertar el Nodo nuevo ahí
        else {
            NodoLista last = cabeza;
            while(last.getSiguiente() != null)
                last = last.getSiguiente();

            last.setSiguiente(nodoNuevo);
            makeTail(nodoNuevo);
        }

        tamanoLista++;
    }

    // Remueve un Nodo de la lista dado un valor
    public void remover(Turno valor)
    {
        // Guarda el Ndodo cabeza
        NodoLista nodoActual = cabeza;
        NodoLista anterior = null;

        // Si la cabeza contiene el valor para remover
        if (nodoActual != null && nodoActual.getValor() == valor)
            cabeza = nodoActual.getSiguiente(); // Cambia la cabeza
        else {
            // Si el valor está en otro lado, buscar el valor correcto
            while (nodoActual != null && nodoActual.getValor() != valor) {
                // Si el Nodo actual no contiene el valor, buscar en el siguiente
                anterior = nodoActual;
                nodoActual = nodoActual.getSiguiente();
            }
            // Si el valor fue hallado, debería encontrarse en el Nodo actual, por lo que no debería ser null
            if (nodoActual != null) 
                // Si el valor está en el Nodo actual, remover de la lista
                anterior.setSiguiente(nodoActual.getSiguiente());
            makeTail(anterior); // Revisa si puede ser la cola también

            // Si el valor no fue hallado, el Nodo actual debería ser null
            if (nodoActual == null) 
                System.out.println(valor + " not found");
             }

        tamanoLista--;
    }
    
    // Remueve un Nodo de la lista dado un índice (posición)
    public void removerEn(Integer indice) {
        // Guarda el Nodo cabeza
        NodoLista nodoActual = cabeza;
        NodoLista anterior = null;
 
        // Si el índice es 0, el Nodo cabeza será removido
        if (indice == 0 && nodoActual != null) {
            cabeza = nodoActual.getSiguiente();
            cola = null;
        }

        else {
            // Si el índice es mayor a 0 pero menor que el tamaño de la lista
            int contador = 0;
            // Cuenta hasta hallar el Nodo a remover; mantiene una referencia al Nodo anterior
            while (nodoActual != null) {
                if (contador == indice) {
                    // El Nodo actual equivale al índice, por tanto es removido
                    makeTail(nodoActual.getSiguiente()); // Revisa si puede ser la cola también
                    anterior.setSiguiente(nodoActual.getSiguiente());
                    break;
                }
                else {
                    // Si la posición actual no es la requerida, buscar la siguiente
                    anterior = nodoActual;
                    nodoActual = nodoActual.getSiguiente();
                    contador++;
                }
            }
            // Si el valor fue hallado, debería encontrarse en el Nodo actual, por lo que no debería ser null
            
            // Si el índice es mayor al tamaño de la lista, el Nodo actual es null
            if (nodoActual == null) 
                System.out.println("Posición " + indice + " no encontrada");
            
            tamanoLista--;
        }  
    }
    
    // Encuentra un Nodo dado su valor
    public boolean buscar(Turno valor) {
        NodoLista actual = cabeza;
        int posicion = 0;

        while (actual != null) {
            if (actual.getValor() == valor) {
                System.out.println("Nodo " + valor.getNumeroTurno() + " encontrado en la posición " + posicion);
                return true;
            }
            actual = actual.getSiguiente();
            posicion++;
        }

        System.out.println("Nodo " + valor.getNumeroTurno() + " no encontrado");
        return false;
    }

    // Obtener un Nodo dado un índice
    public Turno obtenerKesimo(Integer indice) {
        NodoLista actual = cabeza;
        int posicion = 0;

        while (actual != null) {
            if (posicion == indice) { // Busca la posición hasta que el conteo sea igual al índice dado

                System.out.println("La posición " + indice + " contiene un nodo con valor" + actual.getValor().getNumeroTurno());
                return actual.getValor();
            }
            posicion++;
            actual = actual.getSiguiente();
        }

        System.out.println("Nodo en posición " + indice + " no encontrado; no existe");
        return null; // Si un valor inexistente es dado, retorna null
    }
    
    // Imprime la lista de turnos
    public void imprimirLista() {
        NodoLista actual = cabeza;

        System.out.println("Tamaño de la lista = " + tamanoLista);
        System.out.println("Lista de turnos: ");
        // Recorre la lista, imprimiendo los valores de cada Nodo
        while (actual != null) {
            System.out.println("N°: " + actual.getValor().getNumeroTurno()
                            + "Usuario: " + actual.getValor().getUsuario()
                            + "Pedido: " + actual.getValor().getPedido());
            actual = actual.getSiguiente();
        }
    }

    // Si un apuntador Siguiente es null, hace que el Nodo dado sea la cola
    public void makeTail(NodoLista nodo) {
        if (nodo.getSiguiente() == null)
            cola = nodo;
    }

    // Encapsulamiento
    public int getTamanoLista() { // Retorna el número de elementos presentes en la lista
        return tamanoLista;
    }
    public NodoLista getCabeza() {
        return cabeza;
    }
    public NodoLista getCola() {
        return cola;
    }
    
    public void setTamanoLista(int tamanoLista) {
        this.tamanoLista = tamanoLista;
    }
    public void setCabeza(NodoLista cabeza) {
        this.cabeza = cabeza;
    }
    public void setCola(NodoLista cola) {
        this.cola = cola;
    }

    public static void main(String[] args) {
       
    }
}