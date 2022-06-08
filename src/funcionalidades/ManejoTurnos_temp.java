package funcionalidades;

import java.util.Comparator;
import java.util.PriorityQueue;

class Turno_temp {
    protected Integer numeroTurno;
    protected String usuario;
    protected String direccion;

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

class comparaTurno implements Comparator<Turno_temp>{
    protected Turno_temp turnoTemp;

    // Método para comparar
    @Override
    public int compare(Turno_temp actual, Turno_temp otro) {
        int tipoOrden = -1;
        switch(tipoOrden) {
            case 0: // Comparar turnos por su número (orden de llegada)
                return actual.getNumeroTurno() - otro.getNumeroTurno();
            case 1: // Comparar número de items en el pedido
                return actual.getNumeroTurno() - otro.getNumeroItems();
            default:
                return -1;
        }
    }
}

public class ManejoTurnos_temp  {
    protected int numeroTurnos = 0; // Número de turnos totales
    protected int tipoOrden = -1; // Para priorizar número de turnos vs tamaño del pedido

    protected PriorityQueue <Turno_temp> colaTurnos; // Cola de Prioridad que almacenará los turnos
    

    // Constructor
    ManejoTurnos_temp(int tipoOrden) {
        // Define el tipo de orden de la cola (si se ordenará por turnos o tamaño del pedido)
        switch(tipoOrden) {
            case 0: // Comparar turnos por su número (orden de llegada) (Más viejo sale primero)
                colaTurnos = new PriorityQueue<>(new comparaTurno());
                break;
            case 1: // Comparar número de items en el pedido (Más items sale primero)
                colaTurnos = new PriorityQueue<>(new comparaTurno().reversed());
                break;
            default:
                break;
        }
    }  

    // Métodos
}
