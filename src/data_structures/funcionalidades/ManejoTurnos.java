package funcionalidades;
class Turno {
    protected Integer numeroTurno;
    protected String usuario;

    protected String[] pedido = new String[100]; // Guarda los items del pedido
    protected int numeroItems = 0;

    // Constructor
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

class Nodo {
    protected Turno valor;
    protected Nodo siguiente;
    protected Nodo anterior;
    // Constructores
    // Constructor por defecto
    Nodo(Turno valor) {
        this.valor = valor;
        anterior = null;
        siguiente = null;
    }
    // Constructor indicando nodo siguiente
    Nodo(Turno valor, Nodo siguiente) {
        this.valor = valor;
        this.siguiente = siguiente;
    }

    // Encapsulamiento
    public Turno getValor() {
        return valor;
    }
    public Nodo getAnterior() {
        return anterior;
    }
    public Nodo getSiguiente() {
        return siguiente;
    }
    
    public void setValor(Turno valor) {
        this.valor = valor;
    }
    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}

public class ManejoTurnos {
    protected int tamanoCola = 0;
    protected Nodo frente, fondo;

    // Constructor
    ManejoTurnos() {
        this.frente = null;
        this.fondo = null;
    }

    // Métodos
    // Añadir un turno
    public void encolar(Turno valor) {
        Nodo nuevo = new Nodo(valor);

        // Si la cola está vacía, el nuevo Nodo será frente y fondo
        if (this.fondo == null) { 
            this.frente = nuevo;
            this.fondo = nuevo;

            tamanoCola++;
            return;
        }
        else { 
            // Añade el nuevo Nodo al final de la Cola
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
       Nodo anteriorFrente = this.frente;
       this.frente = this.frente.getSiguiente();

       // If the front becomes null, then change the back as null
       if (this.frente == null)
           this.fondo = null;

       tamanoCola--;
   }

    public void imprimirCola() {
        Nodo actual = frente;

        System.out.println("Tamaño de cola= " + tamanoCola);
        System.out.println("Frente: ");
        // Atraviesa la cola, imprimiendo el valor de cada nodo
        while (actual != null) {
            System.out.print("Turno " + actual.getValor().getNumeroTurno() + " | Usuario: " + actual.getValor().getUsuario());
            actual = actual.getSiguiente();
        }
    }
}