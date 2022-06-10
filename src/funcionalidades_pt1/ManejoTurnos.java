package funcionalidades_pt1;
class Turno {
    protected Integer numeroTurno;
    protected String usuario;

    protected String[] pedido = new String[100]; // Guarda los items del pedido
    protected int numeroItems = 0;

    // Métodos
    public void anadirItem(String item) {
        this.pedido[this.numeroItems] = item;
        this.numeroItems++;
    }

    // Encapsulamiento
    public int getNumeroTurno() {
        return numeroTurno;
    }
    public void setNumeroTurno(Integer numeroTurno) {
        this.numeroTurno = numeroTurno;
    }

    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String[] getPedido() {
        return pedido;
    }
    public void setPedido(String pedido[]) {
        this.pedido = pedido;
    }

    public int getNumeroItems() {
        return numeroItems;
    }
    public void setNumeroitems(Integer numeroItems) {
        this.numeroItems = numeroItems;
    }
}

class NodoFila {
    protected Turno valor;
    protected NodoFila siguiente;
    protected NodoFila anterior;
    // Constructores
    // Constructor por defecto
    NodoFila(Turno valor) {
        this.valor = valor;
        anterior = null;
        siguiente = null;
    }
    // Constructor indicando nodo siguiente
    NodoFila(Turno valor, NodoFila siguiente) {
        this.valor = valor;
        this.siguiente = siguiente;
    }

    // Encapsulamiento
    public Turno getValor() {
        return valor;
    }
    public NodoFila getAnterior() {
        return anterior;
    }
    public NodoFila getSiguiente() {
        return siguiente;
    }
    
    public void setValor(Turno valor) {
        this.valor = valor;
    }
    public void setAnterior(NodoFila anterior) {
        this.anterior = anterior;
    }
    public void setSiguiente(NodoFila siguiente) {
        this.siguiente = siguiente;
    }
}

public class ManejoTurnos {
    protected int tamanoCola = 0;
    protected NodoFila frente, fondo;

    // Constructor
    ManejoTurnos() {
        this.frente = null;
        this.fondo = null;
    }

    // Métodos
    // Añadir un turno
    public void encolar(Turno valor) {
        NodoFila nuevo = new NodoFila(valor);

        // Si la cola está vacía, el nuevo NodoFila será frente y fondo
        if (this.fondo == null) { 
            this.frente = nuevo;
            this.fondo = nuevo;

            tamanoCola++;
            return;
        }
        else { 
            // Añade el nuevo NodoFila al final de la Cola
            this.fondo.setSiguiente(nuevo);
            this.fondo = nuevo;
            tamanoCola++;
        }
    }

    // Remover un turno
    public void desencolar() {
        // Si la cola está vacía retorna null
        if (this.frente == null)
           return;

       // Mueve el frente un nodo adelante
       NodoFila anteriorFrente = this.frente;
       this.frente = this.frente.getSiguiente();

       // If the front becomes null, then change the back as null
       if (this.frente == null)
           this.fondo = null;

       tamanoCola--;
   }

    public void imprimirCola() {
        NodoFila actual = frente;

        System.out.println("Tamaño de cola= " + tamanoCola);
        System.out.println("Frente: " + frente.getValor().getNumeroTurno());
        // Atraviesa la cola, imprimiendo el valor de cada nodo
        while (actual != null) {
            System.out.println("Turno " + actual.getValor().getNumeroTurno() + " | Usuario: " + actual.getValor().getUsuario());
            actual = actual.getSiguiente();
        }
    }

    // Encapsulamiento
    public int getTamanoCola() { // Retorna el número de elementos presentes en la lista
        return tamanoCola;
    }
    public NodoFila getFrente() {
        return frente;
    }
    public NodoFila getFondo() {
        return fondo;
    }
    
    public void setTamanoCola(int tamanoCola) {
        this.tamanoCola = tamanoCola;
    }
    public void setFrente(NodoFila frente) {
        this.frente = frente;
    }
    public void setFondo(NodoFila fondo) {
        this.fondo = fondo;
    }
}