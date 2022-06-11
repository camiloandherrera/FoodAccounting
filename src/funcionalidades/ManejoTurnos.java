package funcionalidades;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

// Clase que contiene los elementos de un pedido (turno)
class Turno {
    protected Integer numeroTurno;
    protected String usuario;
    protected String direccion;

    protected String[] pedido = new String[100]; // Guarda los items del pedido
    protected int numeroItems = 0;

    // Constructor
    Turno(Integer numeroTurno, String usuario, String direccion, String producto) {
        this.numeroTurno = numeroTurno;
        this.usuario = usuario;
        this.direccion = direccion;
        this.pedido[0] = producto; // Todo turno debe crearse primero on un producto inicial

        if (this.pedido[0] != null)
            numeroItems++;
    }

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

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

// Clase para crear un Comparador personalizado para los Turnos
class comparaTurno implements Comparator<Turno>{
    protected Turno turnoTemp;
    int tipoOrden;

    // Constructor
    comparaTurno(int tipoOrden) {
        this.tipoOrden = tipoOrden;
    }

    // Método para comparar
    @Override
    public int compare(Turno actual, Turno otro) {
        switch(tipoOrden) {
            case 0: // Comparar turnos por su número (orden de llegada)
                return actual.getNumeroTurno() - otro.getNumeroTurno();
            case 1: // Comparar número de items en el pedido
                return actual.getNumeroItems() - otro.getNumeroItems();
            default: // Por defecto comparará orden de llegada
                return actual.getNumeroTurno() - otro.getNumeroTurno();
        }
    }
}

public class ManejoTurnos { // Manejado PROVISIONALMENTE por la estructura de la librería
    protected int numeroTurnos = 0; // Número de turnos totales
    protected int tipoOrden; // Para priorizar número de turnos vs tamaño del pedido

    protected PriorityQueue <Turno> colaTurnos; // Cola de Prioridad que almacenará los turnos
    

    // Constructor
    ManejoTurnos(int tipoOrden) {
        // Define el tipo de orden de la cola (si se ordenará por turnos o tamaño del pedido)
        this.tipoOrden = tipoOrden;
        switch(tipoOrden) {
            case 0: // Comparar turnos por su número (orden de llegada) (Más viejo sale primero)
                colaTurnos = new PriorityQueue<>(new comparaTurno(tipoOrden));
                break;
            case 1: // Comparar número de items en el pedido (Más items sale primero)
                colaTurnos = new PriorityQueue<>(new comparaTurno(tipoOrden).reversed());
                break;
            default: // Por defecto comparará orden de llegada
                colaTurnos = new PriorityQueue<>(new comparaTurno(tipoOrden));
                break;
        }
    }  

    // Métodos
    public void anadir(Turno turno) {
        colaTurnos.add(turno);
        numeroTurnos++;
    }

    public void remover() {
        colaTurnos.poll();

        Turno arr[] = null;
        colaTurnos.toArray(arr);

        PriorityQueue <Turno> colaNueva = new PriorityQueue<Turno>();
        colaNueva.addAll(colaTurnos);
        colaTurnos = colaNueva;
        numeroTurnos--;
    }

    public void imprimirColaTurnos() {
        System.out.println("Número de turnos: " + numeroTurnos);
        System.out.println("Turno raiz: " + colaTurnos.peek().getNumeroTurno());

        // Atraviesa la cola de prioridad, imprimiendo el valor de cada nodo
        /*for (Turno nodo : colaTurnos) {
            System.out.println("Turno " + nodo.getNumeroTurno() + " | Usuario: " + nodo.getUsuario() + " | Número de productos: " + nodo.getNumeroItems());
        }*/

        PriorityQueue <Turno> colaTemp = colaTurnos;
        List <Turno> listaCola = new LinkedList<Turno>();

        for (Turno turnosTmp : colaTemp) {
            listaCola.add(turnosTmp);
        }

        for (Turno turnosTmp : listaCola) {
            System.out.println("Turno : " + turnosTmp.getNumeroTurno() + " | Usuario: " + turnosTmp.getUsuario() + " | Número de productos: " + turnosTmp.getNumeroItems());
        }
    }

    // Encapsulamiento
    public int getNumeroTurnos() {
        return numeroTurnos;
    }
    public void setNumeroTurnos(int numeroTurnos) {
        this.numeroTurnos = numeroTurnos;
    }

    public int getTipoOrden() {
        return tipoOrden;
    }
    public void setTipoOrden(int tipoOrden) {
        this.tipoOrden = tipoOrden;
    }

    public PriorityQueue<Turno> getColaTurnos() {
        return colaTurnos;
    }
    public void setColaTurnos(PriorityQueue<Turno> colaTurnos) {
        this.colaTurnos = colaTurnos;
    }

    // Main (debug)
    public static void main(String[] args) {
        
       ManejoTurnos tipoA = new ManejoTurnos(0);
       ManejoTurnos tipoB = new ManejoTurnos(1);
       ManejoTurnos tipoC = new ManejoTurnos(2);

       for (Integer i = 0; i < 10; i++) {
           Turno turnoTemp = new Turno(tipoA.numeroTurnos, "Usuario "+i.toString(), "Calle falsa "+i.toString(), "Comida"+i.toString());
           for (Integer j = 0; j < (Math.random() * (6 - 0) + 0); j++) {
               turnoTemp.anadirItem(j.toString());
           }
           tipoA.anadir(turnoTemp);
           tipoB.anadir(turnoTemp);
           tipoC.anadir(turnoTemp);
       }

       System.out.println("Tipos de colas de turnos: 0 = orden de llegada, 1 = numero de items.");
       System.out.println("----------------------------");
       System.out.println("Cola a, tipo " + tipoA.getTipoOrden() + ":");
       tipoA.imprimirColaTurnos();

       System.out.println("----------------------------");
       System.out.println("Cola b, tipo " + tipoB.getTipoOrden() + ":");
       tipoB.imprimirColaTurnos();

       System.out.println("----------------------------");
       System.out.println("Cola c, tipo " + tipoC.getTipoOrden() + ":");
       tipoC.imprimirColaTurnos();

       System.out.println("----------------------------\n----------------------------");
       System.out.println("Cola b, tipo " + tipoB.getTipoOrden() + ", removiendo un turno completado:");
       tipoB.getColaTurnos().poll();
       tipoB.imprimirColaTurnos();

    }
}
