package funcionalidades.test_clases;

import java.util.Random;
import java.util.Scanner;
import funcionalidades.*;
import data_structures.LinkedList_String;

class Usuario_test {
    // Datos Usuario_test
    protected String nombreDeUsuario;
    protected String contrasena;
    protected String email;

    // Datos Personales
    protected String nombrePersona;
    protected String nombreEstablecimiento;
    protected String direccion;

    // Datos de manejo
    protected ManejoTurnos_test turnos;
    protected ManejoDinero_test dinero;
    protected Productos_test productos;
    protected PilaCuentas_test cuentas;
    protected ListaCuentas_test listaDeCuentas;

    // Constructor
    public Usuario_test(String nombreDeUsuario, String contrasena, String email, String nombrePersona,
                   String nombreEstablecimiento, String direccion, int tipoOrden) {

        // Establece los datos
        this.nombreDeUsuario = nombreDeUsuario;
        this.contrasena = contrasena;
        this.email = email;
        this.nombrePersona = nombrePersona;
        this.nombreEstablecimiento = nombreEstablecimiento;
        this.direccion = direccion;
        
        // Inicializa los objetos de manejo
        turnos = new ManejoTurnos_test(tipoOrden);
        dinero = new ManejoDinero_test();
        productos = new Productos_test();
        cuentas = new PilaCuentas_test();
        listaDeCuentas = new ListaCuentas_test();
    }

    // Encapsulamiento
    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }
    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }
    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getNombreEstablecimiento() {
        return nombreEstablecimiento;
    }
    public void setNombreEstablecimiento(String nombreEstablecimiento) {
        this.nombreEstablecimiento = nombreEstablecimiento;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ManejoTurnos_test getTurnos() {
        return turnos;
    }
    public void setTurnos(ManejoTurnos_test turnos) {
        this.turnos = turnos;
    }

    public ManejoDinero_test getDinero() {
        return dinero;
    }
    public void setDinero(ManejoDinero_test dinero) {
        this.dinero = dinero;
    }

    public Productos_test getProductos() {
        return productos;
    }
    public void setProductos(Productos_test productos) {
        this.productos = productos;
    }

    public PilaCuentas_test getCuentas() {
        return cuentas;
    }
    public void setCuentas(PilaCuentas_test cuentas) {
        this.cuentas = cuentas;
    }

    public ListaCuentas_test getListaDeCuentas() {
        return listaDeCuentas;
    }
    public void setListaDeCuentas(ListaCuentas_test listaDeCuentas) {
        this.listaDeCuentas = listaDeCuentas;
    }
}


class ManejoCuentas_test {
    // LinkedList que almacenará los nombres de usuario creados
    LinkedList_String usernames = new LinkedList_String();

    // LinkedHash_test (nodo) de la HashTable, similar a una LinkedList
    class LinkedHash_test {
        String llave;
        Usuario_test valor;
        LinkedHash_test siguiente;
  
        // Constructor de LinkedHash_test
        LinkedHash_test(Usuario_test valor)
        {
            this.llave = valor.getNombreDeUsuario();
            this.valor = valor;
            this.siguiente = null;
        }
    }
  
    protected int tamanoTabla;
    protected int usuariosRegistrados;
    protected LinkedHash_test[] tabla;
  
    // Constructor de ManejoCuentas_test
    public ManejoCuentas_test(int tamanoTabla)
    {
        usuariosRegistrados = 0;
        this.tamanoTabla = tamanoTabla;
        tabla = new LinkedHash_test[tamanoTabla];
  
        // Inicializa la tabla con valores nulos
        for (int i = 0; i < tamanoTabla; i++)
            tabla[i] = null;
    }

    // Métodos
    // Obtiene el número actual de pares valor-llave actuales en la tabla
    public int getusuariosRegistrados() {
        return usuariosRegistrados;
    }
  
    // Limpia (vacía) la tabla
    public void vaciar()
    {
        for (int i = 0; i < tamanoTabla; i++)
            tabla[i] = null;
    }
  
    // Obtiene el valor de una llave
    public Usuario_test obtener(String llave)
    {   
        // Encuentra el valor de posición utilizando hash(llave) % tamaño de tabla
        int valor = (generarHash(llave) % tamanoTabla);
        // Si la posición está vacía, retorna un valor fuera del rango de posiciones
        if (tabla[valor] == null)
            return null;
        // Sino, recorre las posiciones de la sublista en el nodo hasta encontrar el valor deseado
        else {
            LinkedHash_test actual = tabla[valor];
            while (actual != null && !actual.llave.equals(llave)) {
                actual = actual.siguiente;
            }
            // Si se encuentra el valor, actual no debería ser null
            if (actual == null) {
                return null;
            }
            else {
                return actual.valor;
            }
        }
    }
  
    // Inserta un valor en una HashTable
    public void insertar(Usuario_test valor)
    {
        int hash = (generarHash(valor.getNombreDeUsuario()) % tamanoTabla);
        // Si no encuentra una posición nula en la tabla, insertará allí el valor como "primer nodo" de la sublista de hashes
        if (tabla[hash] == null) {
            tabla[hash] = new LinkedHash_test(valor);
        }
        // Si no, atraviesa los hashes existentes para verificar si existe un nodo con la llave dada
        else {
            LinkedHash_test entry = tabla[hash];
            while (entry.siguiente != null && !entry.llave.equals(valor.getNombreDeUsuario())) {
                entry = entry.siguiente;
            }
            // Si existe, actualiza su valor
            if (entry.llave.equals(valor.getNombreDeUsuario())) {
                entry.valor = valor;
            }
            // Sino, crea un nuevo nodo de LinkedHash_test
            else {
                entry.siguiente = new LinkedHash_test(valor);
            }
        }
        usuariosRegistrados++;
    }
  
    // Remover un valor con una llave específica, realizando un lazy deletion (remover referencias del valor)
    public void remover(Usuario_test nodo)
    {
        int valor = (generarHash(nodo.getNombreDeUsuario()) % tamanoTabla);
        
        // Si la posición no está vacía, busca el valor a eliminar en la sublista
        if (tabla[valor] != null) {
            LinkedHash_test prev = null;
            LinkedHash_test actual = tabla[valor];
            // Recorre hasta el final de la sublista
            while (actual.siguiente != null && !actual.llave.equals(nodo.getNombreDeUsuario())) {
                prev = actual;
                actual = actual.siguiente;
            }
            // Si encuentra el valor con la llave
            if (actual.llave.equals(nodo.getNombreDeUsuario())) {
                // Si es el primero en la sublista, el siguiente se convertirá en primero
                if (prev == null) {
                    tabla[valor] = actual.siguiente;
                }
                // Sino, el anerior referenciará el siguiente del valor encontrado como SU siguiente
                else {
                    prev.siguiente = actual.siguiente;
                }
                usuariosRegistrados--;
            }
        }
    }
  
    // Da un valor hash a partir de un String especificado, utiliza el HashCode del objeto String de Java % tamaño de tabla
    private int generarHash(String stringEntrada)
    {
        int valor = stringEntrada.hashCode();
        valor %= tamanoTabla;
        // Si el valor da < 0 (fuera de rango de tabla), le da un valor positivo al hash
        if (valor < 0) {
            valor = valor + tamanoTabla;
        }
        return valor;
    }
  
    // Imprime la HashTable
    public void imprimirTabla()
    {
        for (int i = 0; i < tamanoTabla; i++) {
            LinkedHash_test actual = tabla[i];
            while (actual != null) {
                System.out.println(
                    "Lugar = " + actual.valor.getNombreEstablecimiento() + " " + "Usuario_test = " + actual.llave);
                actual = actual.siguiente;
            }
        }
        System.out.println();
    }

    static final int LONGITUD_STRING_SALIDA = 5;
    // Main debug
    public static void main(String args[]) {
        // Caracteres utilizados para la generación de strings aleatorios
        String strCaracteresPermitidos = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // Inicializar Random
        Random random = new Random();

        // Datos a analizar en las pruebas
        int DATOS_A_ANALIZAR = 100;

        // Medir tiempo
        long comienzoT, finalT;
        comienzoT = System.currentTimeMillis();
        ManejoCuentas_test users = new ManejoCuentas_test(DATOS_A_ANALIZAR);
        for (int i = 0; i < DATOS_A_ANALIZAR; i++) {
            String nombreDeUsuario = getStringAleatorio(strCaracteresPermitidos, random);
            String contrasena = getStringAleatorio(strCaracteresPermitidos, random);
            String nombrePersona = getStringAleatorio(strCaracteresPermitidos, random);
            String email = getStringAleatorio(strCaracteresPermitidos, random);
            String nombreEstablecimiento = getStringAleatorio(strCaracteresPermitidos, random);
            String direccion = getStringAleatorio(strCaracteresPermitidos, random);

            int tipoOrden = random.nextInt(2);
            users.insertar(new Usuario_test(nombreDeUsuario, contrasena, email, nombrePersona, nombreEstablecimiento, direccion, tipoOrden));
        }
        finalT = System.currentTimeMillis();
        System.out.println("La inserción de los datos tomó " + (finalT - comienzoT) + " ms");

        long comienzoT2, finalT2;
        comienzoT2 = System.currentTimeMillis();
        for (int i = 0; i < DATOS_A_ANALIZAR; i++) {
            users.obtener(getStringAleatorio(strCaracteresPermitidos, random));
        }
        finalT2 = System.currentTimeMillis();
        System.out.println("La búsqueda de los datos tomó " + (finalT2 - comienzoT2) + " ms");
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