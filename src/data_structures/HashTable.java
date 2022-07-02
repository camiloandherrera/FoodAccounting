package data_structures;

// HashTable utilizando encadenamiento separado y cabezas de listas enlazadas
class HashTable {
    // LinkedHash (nodo) de la HashTable, similar a una LinkedList
    class LinkedHash {
        String llave;
        int valor;
        LinkedHash siguiente;
  
        // Constructor de LinkedHash
        LinkedHash(String llave, int valor)
        {
            this.llave = llave;
            this.valor = valor;
            this.siguiente = null;
        }
    }
  
    protected int tamanoTabla;
    protected int tamanoActual;
    protected LinkedHash[] tabla;

    // Constructor de HashTable
    public HashTable(int tamanoTabla)
    {
        tamanoActual = 0;
        this.tamanoTabla = tamanoTabla;
        tabla = new LinkedHash[tamanoTabla];
  
        // Inicializa la tabla con valores nulos
        for (int i = 0; i < tamanoTabla; i++)
            tabla[i] = null;
    }
  

    // Métodos
    // Limpia (vacía) la tabla
    public void vaciar()
    {
        for (int i = 0; i < tamanoTabla; i++)
            tabla[i] = null;
    }
  
    // Obtiene el valor de una llave
    public int obtener(String llave)
    {   
        // Encuentra el valor de posición utilizando hash(llave) % tamaño de tabla
        int valor = (generarHash(llave) % tamanoTabla);
        // Si la posición está vacía, retorna un valor fuera del rango de posiciones
        if (tabla[valor] == null)
            return -1;
        // Sino, recorre las posiciones de la sublista en el nodo hasta encontrar el valor deseado
        else {
            LinkedHash actual = tabla[valor];
            while (actual != null && !actual.llave.equals(llave)) {
                actual = actual.siguiente;
            }
            // Si se encuentra el valor, actual no debería ser null
            if (actual == null) {
                return -1;
            }
            else {
                return actual.valor;
            }
        }
    }
  
    // Inserta un valor en una HashTable
    public void insertar(String llave, int valor)
    {
        int hash = (generarHash(llave) % tamanoTabla);
        // Si no encuentra una posición nula en la tabla, insertará allí el valor como "primer nodo" de la sublista de hashes
        if (tabla[hash] == null) {
            tabla[hash] = new LinkedHash(llave, valor);
        }
        // Si no, atraviesa los hashes existentes para verificar si existe un nodo con la llave dada
        else {
            LinkedHash entry = tabla[hash];
            while (entry.siguiente != null && !entry.llave.equals(llave)) {
                entry = entry.siguiente;
            }
            // Si existe, actualiza su valor
            if (entry.llave.equals(llave)) {
                entry.valor = valor;
            }
            // Sino, crea un nuevo nodo de LinkedHash
            else {
                entry.siguiente = new LinkedHash(llave, valor);
            }
        }
        tamanoActual++;
    }
  
    // Remover un valor con una llave específica, realizando un lazy deletion (remover referencias del valor)
    public void remover(String llave)
    {
        int valor = (generarHash(llave) % tamanoTabla);
        
        // Si la posición no está vacía, busca el valor a eliminar en la sublista
        if (tabla[valor] != null) {
            LinkedHash prev = null;
            LinkedHash actual = tabla[valor];
            // Recorre hasta el final de la sublista
            while (actual.siguiente != null && !actual.llave.equals(llave)) {
                prev = actual;
                actual = actual.siguiente;
            }
            // Si encuentra el valor con la llave
            if (actual.llave.equals(llave)) {
                // Si es el primero en la sublista, el siguiente se convertirá en primero
                if (prev == null) {
                    tabla[valor] = actual.siguiente;
                }
                // Sino, el anerior referenciará el siguiente del valor encontrado como SU siguiente
                else {
                    prev.siguiente = actual.siguiente;
                }
                tamanoActual--;
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
            LinkedHash actual = tabla[i];
            while (actual != null) {
                System.out.println(
                    "Valor = " + actual.valor + " " + "Llave = " + actual.llave);
                actual = actual.siguiente;
            }
        }
        System.out.println();
    }

    // Encapsulamiento
    public int getTamanoTabla() {
        return tamanoTabla;
    }
    public void setTamanoTabla(int tamanoTabla) {
        this.tamanoTabla = tamanoTabla;
    }

    public int getTamanoActual() {
        return tamanoActual;
    }
    public void setTamanoActual(int tamanoActual) {
        this.tamanoActual = tamanoActual;
    }

    public LinkedHash[] getTabla() {
        return tabla;
    }
    public void setTabla(LinkedHash[] tabla) {
        this.tabla = tabla;
    }
}