package funcionalidades.test_clases;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

// Clase que contiene los elementos de un pedido (turno)
class Turno_temp {
    protected Integer numeroTurno;
    protected String usuario;
    protected String direccion;

    protected String[] pedido = new String[100]; // Guarda los items del pedido
    protected int numeroItems = 0;

    // Constructor
    Turno_temp(Integer numeroTurno, String usuario, String direccion, String producto) {
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
class comparaTurno implements Comparator<Turno_temp>{
    protected Turno_temp turnoTemp;
    int tipoOrden;

    // Constructor
    comparaTurno(int tipoOrden) {
        this.tipoOrden = tipoOrden;
    }

    // Método para comparar
    @Override
    public int compare(Turno_temp actual, Turno_temp otro) {
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

public class ManejoTurnos_test {
    protected int numeroTurnos = 0; // Número de turnos totales
    protected int tipoOrden; // Para priorizar número de turnos vs tamaño del pedido

    protected PriorityQueue <Turno_temp> colaTurnos; // Cola de Prioridad que almacenará los turnos
    

    // Constructor
    ManejoTurnos_test(int tipoOrden) {
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
    public void anadir(Turno_temp turno) {
        colaTurnos.add(turno);
        numeroTurnos++;
    }

    public void remover() {
        colaTurnos.poll();

        Turno_temp arr[] = null;
        colaTurnos.toArray(arr);

        PriorityQueue <Turno_temp> colaNueva = new PriorityQueue<Turno_temp>();
        colaNueva.addAll(colaTurnos);
        colaTurnos = colaNueva;
        numeroTurnos--;
    }

    public void imprimirColaTurnos() {
        System.out.println("Número de turnos: " + numeroTurnos);
        System.out.println("Turno raiz: " + colaTurnos.peek().getNumeroTurno());

        // Atraviesa la cola de prioridad, imprimiendo el valor de cada nodo
        /*for (Turno_temp nodo : colaTurnos) {
            System.out.println("Turno " + nodo.getNumeroTurno() + " | Usuario: " + nodo.getUsuario() + " | Número de productos: " + nodo.getNumeroItems());
        }*/

        PriorityQueue <Turno_temp> colaTemp = colaTurnos;
        List <Turno_temp> listaCola = new LinkedList<Turno_temp>();

        for (Turno_temp turnosTmp : colaTemp) {
            listaCola.add(turnosTmp);
        }

        for (Turno_temp turnosTmp : listaCola) {
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

    public PriorityQueue<Turno_temp> getColaTurnos() {
        return colaTurnos;
    }
    public void setColaTurnos(PriorityQueue<Turno_temp> colaTurnos) {
        this.colaTurnos = colaTurnos;
    }

    static final int LONGITUD_STRING_SALIDA = 10;
    // Main (debug)
    public static void main(String[] args) {
        
        // Caracteres utilizados para la generación de strings aleatorios
        String strCaracteresPermitidos = "ABCXYZ";

        // Inicializar Random
        Random random = new Random();

        // Datos a analizar en las pruebas
        long DATOS_A_ANALIZAR = 10;

        // Medir tipo 0
        long comienzo0, final0;
        comienzo0 = System.currentTimeMillis();
        // Manejo tipo orden de llegada
        ManejoTurnos_test testA = new ManejoTurnos_test(0);
        for (int i = 0; i < DATOS_A_ANALIZAR; i++) {
            Turno_temp turnoTemp = new Turno_temp(testA.numeroTurnos,  "Usuario " + getStringAleatorio(strCaracteresPermitidos, random),
            "Calle" + (Math.random() * (100 - 0) + 0), "Producto" + getStringAleatorio(strCaracteresPermitidos, random));
            for (int j = 0; j < (Math.random() * (10 - 0) + 0); j++) {
                turnoTemp.anadirItem("Producto" + getStringAleatorio(strCaracteresPermitidos, random));
            }
            testA.anadir(turnoTemp);
        }
        final0 = System.currentTimeMillis();
        System.out.println("La inserción de datos en la cola 0 tomó "+ (final0 - comienzo0) + " ms");

        // Medir tipo 1
        long comienzo1, final1;
        comienzo1 = System.currentTimeMillis();
        // Manejo tipo prioridad de pedido grande
        ManejoTurnos_test testB = new ManejoTurnos_test(1);
        for (int i = 0; i < DATOS_A_ANALIZAR; i++) {
            Turno_temp turnoTemp = new Turno_temp(testA.numeroTurnos,  "Usuario " + getStringAleatorio(strCaracteresPermitidos, random),
            "Calle " + (Math.random() * (100 - 0) + 0), "Producto" + getStringAleatorio(strCaracteresPermitidos, random));
            for (int j = 0; j < (Math.random() * (10 - 0) + 0); j++) {
                turnoTemp.anadirItem("Producto" + getStringAleatorio(strCaracteresPermitidos, random));
            }
            testB.anadir(turnoTemp);
        }
        final1 = System.currentTimeMillis();
        System.out.println("La inserción de datos en la cola 1 tomó "+ (final1 - comienzo1) + " ms");
        
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
