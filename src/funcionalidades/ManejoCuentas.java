package funcionalidades;

import java.util.Scanner;
import data_structures.LinkedList_String;

class Usuario {
    // Datos Usuario
    protected String nombreDeUsuario;
    protected String contrasena;
    protected String email;

    // Datos Personales
    protected String nombrePersona;
    protected String nombreEstablecimiento;
    protected String direccion;

    // Datos de manejo
    protected ManejoTurnos turnos;
    protected ManejoDinero dinero;
    protected Productos productos;
    protected PilaCuentas cuentas;
    protected ListaCuentas listaDeCuentas;

    // Constructor
    public Usuario(String nombreDeUsuario, String contrasena, String email, String nombrePersona,
                   String nombreEstablecimiento, String direccion, int tipoOrden) {

        // Establece los datos
        this.nombreDeUsuario = nombreDeUsuario;
        this.contrasena = contrasena;
        this.email = email;
        this.nombrePersona = nombrePersona;
        this.nombreEstablecimiento = nombreEstablecimiento;
        this.direccion = direccion;
        
        // Inicializa los objetos de manejo
        turnos = new ManejoTurnos(tipoOrden);
        dinero = new ManejoDinero();
        productos = new Productos();
        cuentas = new PilaCuentas();
        listaDeCuentas = new ListaCuentas();
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

    public ManejoTurnos getTurnos() {
        return turnos;
    }
    public void setTurnos(ManejoTurnos turnos) {
        this.turnos = turnos;
    }

    public ManejoDinero getDinero() {
        return dinero;
    }
    public void setDinero(ManejoDinero dinero) {
        this.dinero = dinero;
    }

    public Productos getProductos() {
        return productos;
    }
    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public PilaCuentas getCuentas() {
        return cuentas;
    }
    public void setCuentas(PilaCuentas cuentas) {
        this.cuentas = cuentas;
    }

    public ListaCuentas getListaDeCuentas() {
        return listaDeCuentas;
    }
    public void setListaDeCuentas(ListaCuentas listaDeCuentas) {
        this.listaDeCuentas = listaDeCuentas;
    }
}


class ManejoCuentas {
    // LinkedList que almacenar?? los nombres de usuario creados
    LinkedList_String usernames = new LinkedList_String();

    // LinkedHash (nodo) de la HashTable, similar a una LinkedList
    class LinkedHash {
        String llave;
        Usuario valor;
        LinkedHash siguiente;
  
        // Constructor de LinkedHash
        LinkedHash(Usuario valor)
        {
            this.llave = valor.getNombreDeUsuario();
            this.valor = valor;
            this.siguiente = null;
        }
    }
  
    protected int tamanoTabla;
    protected int usuariosRegistrados;
    protected LinkedHash[] tabla;
  
    // Constructor de ManejoCuentas
    public ManejoCuentas(int tamanoTabla)
    {
        usuariosRegistrados = 0;
        this.tamanoTabla = tamanoTabla;
        tabla = new LinkedHash[tamanoTabla];
  
        // Inicializa la tabla con valores nulos
        for (int i = 0; i < tamanoTabla; i++)
            tabla[i] = null;
    }

    // M??todos
    // Obtiene el n??mero actual de pares valor-llave actuales en la tabla
    public int getusuariosRegistrados() {
        return usuariosRegistrados;
    }
  
    // Limpia (vac??a) la tabla
    public void vaciar()
    {
        for (int i = 0; i < tamanoTabla; i++)
            tabla[i] = null;
    }
  
    // Obtiene el valor de una llave
    public Usuario obtener(String llave)
    {   
        // Encuentra el valor de posici??n utilizando hash(llave) % tama??o de tabla
        int valor = (generarHash(llave) % tamanoTabla);
        // Si la posici??n est?? vac??a, retorna un valor fuera del rango de posiciones
        if (tabla[valor] == null)
            return null;
        // Sino, recorre las posiciones de la sublista en el nodo hasta encontrar el valor deseado
        else {
            LinkedHash actual = tabla[valor];
            while (actual != null && !actual.llave.equals(llave)) {
                actual = actual.siguiente;
            }
            // Si se encuentra el valor, actual no deber??a ser null
            if (actual == null) {
                return null;
            }
            else {
                return actual.valor;
            }
        }
    }
  
    // Inserta un valor en una HashTable
    public void insertar(Usuario valor)
    {
        int hash = (generarHash(valor.getNombreDeUsuario()) % tamanoTabla);
        // Si no encuentra una posici??n nula en la tabla, insertar?? all?? el valor como "primer nodo" de la sublista de hashes
        if (tabla[hash] == null) {
            tabla[hash] = new LinkedHash(valor);
        }
        // Si no, atraviesa los hashes existentes para verificar si existe un nodo con la llave dada
        else {
            LinkedHash entry = tabla[hash];
            while (entry.siguiente != null && !entry.llave.equals(valor.getNombreDeUsuario())) {
                entry = entry.siguiente;
            }
            // Si existe, actualiza su valor
            if (entry.llave.equals(valor.getNombreDeUsuario())) {
                entry.valor = valor;
            }
            // Sino, crea un nuevo nodo de LinkedHash
            else {
                entry.siguiente = new LinkedHash(valor);
            }
        }
        usuariosRegistrados++;
    }
  
    // Remover un valor con una llave espec??fica, realizando un lazy deletion (remover referencias del valor)
    public void remover(Usuario nodo)
    {
        int valor = (generarHash(nodo.getNombreDeUsuario()) % tamanoTabla);
        
        // Si la posici??n no est?? vac??a, busca el valor a eliminar en la sublista
        if (tabla[valor] != null) {
            LinkedHash prev = null;
            LinkedHash actual = tabla[valor];
            // Recorre hasta el final de la sublista
            while (actual.siguiente != null && !actual.llave.equals(nodo.getNombreDeUsuario())) {
                prev = actual;
                actual = actual.siguiente;
            }
            // Si encuentra el valor con la llave
            if (actual.llave.equals(nodo.getNombreDeUsuario())) {
                // Si es el primero en la sublista, el siguiente se convertir?? en primero
                if (prev == null) {
                    tabla[valor] = actual.siguiente;
                }
                // Sino, el anerior referenciar?? el siguiente del valor encontrado como SU siguiente
                else {
                    prev.siguiente = actual.siguiente;
                }
                usuariosRegistrados--;
            }
        }
    }
  
    // Da un valor hash a partir de un String especificado, utiliza el HashCode del objeto String de Java % tama??o de tabla
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
            LinkedHash actual = tabla[i];
            while (actual != null) {
                System.out.println(
                    "Lugar = " + actual.valor.getNombreEstablecimiento() + " " + "Usuario = " + actual.llave);
                actual = actual.siguiente;
            }
        }
        System.out.println();
    }
}