package funcionalidades;
import java.util.Scanner;


public class Main {
    static Scanner scMain = new Scanner(System.in);
    static Scanner scMenuSesion = new Scanner(System.in);
    static Scanner scMenuProductos = new Scanner(System.in);
    static Scanner scMenuDinero = new Scanner(System.in);
    static Scanner scMenuTurnos = new Scanner(System.in);

    public static void menuSesion(ManejoCuentas cuentas) {
        

        boolean usuarioValido = false;
        Usuario usuarioActual = null;

        while (!usuarioValido) {
            scMenuSesion.nextLine();
            System.out.println("INICIAR SESIÓN");
            System.out.print("Ingrese nombre de usuario: ");
            String nombreDeUsuario = scMenuSesion.nextLine();
            System.out.print("Ingrese contraseña: ");
            String contrasena = scMenuSesion.nextLine();

            usuarioActual = cuentas.obtener(nombreDeUsuario);

            if (usuarioActual == null)
                System.out.println("¡Usuario no encontrado! Intentar de nuevo.");
            else {
                usuarioValido = true;
                System.out.println("Bienvenido/a, " + nombreDeUsuario);
            }
        }

        while (true) {
            int seleccionSesion = 0;
            System.out.println("¿Qué desea hacer?");
            System.out.println("1- Manejo de productos");
            System.out.println("2- Manejo de turnos");
            System.out.println("3- Realizar cuentas de dinero");
            System.out.println("4- Salir");
            System.out.print("Selección: ");

            seleccionSesion = scMenuSesion.nextInt();

            switch(seleccionSesion) {
                case 1:
                    menuProductos(usuarioActual, cuentas);
                    break;

                case 2:
                    menuTurnos(usuarioActual, cuentas);
                    break;

                case 3:
                    menuDinero(usuarioActual, cuentas);
                    break;

                case 4:
                    return;
                
                default:
                    break;
            }
        }
    }

    public static void menuProductos(Usuario usuarioActual, ManejoCuentas cuentas) {
        Productos manejoProductos = usuarioActual.productos; // Objeto para realizar el manejo de productos

        while (true) {
            System.out.println("\n¿Qué desea hacer?");
            System.out.println("1- Añadir un producto\n2- Borrar un producto\n3- Imprimir listas\n4- Vaciar listas\n5- Salir");
            System.out.print("Selección: ");
            
            int input = scMenuProductos.nextInt();

            String inputNombre;
            String inputTipo;
            int inputValor;

            switch (input) {
                case 1:
                    scMenuProductos.nextLine();
                    System.out.print("Nombre del producto: ");
                    inputNombre = scMenuProductos.nextLine();

                    System.out.print("Tipo de producto: ");
                    inputTipo = scMenuProductos.nextLine();

                    System.out.print("Valor del producto: ");
                    inputValor = scMenuProductos.nextInt();

                    manejoProductos.anadirProducto(new Producto(inputNombre, inputTipo, inputValor));

                    cuentas.obtener(usuarioActual.nombreDeUsuario).productos = manejoProductos;
                break;

                case 2:
                    scMenuProductos.nextLine();
                    System.out.print("Nombre del producto: ");
                    inputNombre = scMenuProductos.nextLine();

                    System.out.print("Tipo de producto: ");
                    inputTipo = scMenuProductos.nextLine();

                    System.out.print("Valor del producto: ");
                    inputValor = scMenuProductos.nextInt();

                    manejoProductos.removerProducto(new Producto(inputNombre, inputTipo, inputValor));
                    System.out.print("Producto " + inputNombre + " eliminado");

                    cuentas.obtener(usuarioActual.nombreDeUsuario).productos = manejoProductos;
                break;

                case 3:
                    manejoProductos.imprimirListas();
                    cuentas.obtener(usuarioActual.nombreDeUsuario).productos = manejoProductos;
                break;
                
                case 4:
                    manejoProductos.vaciarListas();
                    cuentas.obtener(usuarioActual.nombreDeUsuario).productos = manejoProductos;
                break;

                case 5:
                    return;

                default:
                break;
            }
        }
    }

    public static void menuDinero(Usuario usuarioActual, ManejoCuentas cuentas) {
        PilaCuentas manejoPilaCuentas = usuarioActual.getCuentas();
        ListaCuentas manejoListaCuentas = usuarioActual.getListaDeCuentas();

        while (true) {
            System.out.println("\nCuenta Neta: " + usuarioActual.getCuentas().getValorNeto());
            System.out.println("¿Qué desea hacer?");
            System.out.println("1- Hacer una cuenta\n2- Deshacer última cuenta\n3- Imprimir cuentas hechas\n4- Vaciar cuentas\n5- Salir");
            System.out.print("Selección: ");
            int input = scMenuDinero.nextInt();

            switch(input)
            {
                case 1:
                System.out.print("Escriba la cuenta a sumar (o restar): ");
                int inputNum = scMenuDinero.nextInt();
                scMenuDinero.nextLine();
                System.out.print("Escriba la descripción de la cuenta: ");
                String inputDesc = scMenuDinero.nextLine();
                
                usuarioActual.getCuentas().apilar(inputNum);
                usuarioActual.getListaDeCuentas().insertar(new Cuenta(inputNum, inputDesc));
                break;

                case 2:
                usuarioActual.getCuentas().desapilar();
                usuarioActual.getListaDeCuentas().removerCola();
                break;

                case 3:
                usuarioActual.getListaDeCuentas().imprimirLista();
                break;

                case 4:
                    usuarioActual.getDinero().vaciarPila(usuarioActual.cuentas);
                    usuarioActual.listaDeCuentas = usuarioActual.getDinero().vaciarLista(usuarioActual.listaDeCuentas);
                break;

                case 5:
                return;

                default:
                break;
            }

            cuentas.obtener(usuarioActual.nombreDeUsuario).cuentas = manejoPilaCuentas;
            cuentas.obtener(usuarioActual.nombreDeUsuario).listaDeCuentas = manejoListaCuentas;
        }
    }

    public static void menuTurnos(Usuario usuarioActual, ManejoCuentas cuentas) {
        ManejoTurnos turnos = usuarioActual.getTurnos();

        while (true) {
            int seleccionTurnos = 0;
            System.out.println("¿Qué desea hacer?");
            System.out.println("1- Registrar un turno");
            System.out.println("2- Eliminar el turno de mayor prioridad");
            System.out.println("3- Imprimir turnos actuales");
            System.out.println("4- Salir");
            System.out.print("Selección: ");
            seleccionTurnos = scMenuTurnos.nextInt();

            switch(seleccionTurnos) {
                case 1:
                    scMenuTurnos.nextLine();
                    int numeroTurno = turnos.getNumeroTurnos() + 1;
                    System.out.print("Ingrese nombre del usuario: ");
                    String usuario = scMenuTurnos.nextLine();
                    System.out.print("Ingrese dirección del usuario: ");
                    String direccion = scMenuTurnos.nextLine();

                    System.out.print("Ingrese el número de items del pedido: ");
                    int numeroItems = scMenuTurnos.nextInt();
                    scMenuTurnos.nextLine();
                    System.out.println("Ingrese los items del pedido: ");
                    String[] pedido = new String[numeroItems];
                    for (int i = 0; i < numeroItems; i++) {
                        System.out.print(i + "- ");
                        pedido[i] = scMenuTurnos.nextLine();
                    }
                    turnos.anadir(new Turno(numeroTurno, usuario, direccion, pedido, numeroItems));
                    System.out.println("¡Turno añadido correctamente!");

                    break;

                case 2:
                    turnos = usuarioActual.getTurnos();
                    turnos.remover();
                    System.out.println("¡Turno removido correctamente!");
                    break;

                case 3:
                    turnos = usuarioActual.getTurnos();
                    turnos.imprimirColaTurnos();
                    break;

                case 4:
                    return;

                default:
                    break;
            }
            cuentas.obtener(usuarioActual.nombreDeUsuario).turnos = turnos;
        }
    }

    public static void main(String args[]) {
        ManejoCuentas cuentas = new ManejoCuentas(100);
        

        while (true) {
            int seleccionHacer = 0;
            System.out.println("¿Qué desea hacer?");
            System.out.println("0- Iniciar Sesión");
            System.out.println("1- Registrar un usuario");
            System.out.println("2- Buscar un usuario");
            System.out.println("3- Salir");

            System.out.print("Selección: ");
            seleccionHacer = scMain.nextInt();

            switch (seleccionHacer) {
                case 0:
                    menuSesion(cuentas);
                    break;
                
                case 1:
                    System.out.println("REGISTRAR UN USUARIO");
                    scMain.nextLine();
                    System.out.print("Ingrese su correo electrónico: ");
                    String email = scMain.nextLine();

                    String nombreDeUsuario = null;
                    boolean userValido = false;
                    while (!userValido) {
                        System.out.print("Ingrese su nombre de usuario: ");
                        String username = scMain.nextLine();
                        if (cuentas.usernames.search(username) == true)
                            System.out.println("¡Usuario ya existe! Intente de nuevo.");
                        else {
                            cuentas.usernames.insert(username);
                            nombreDeUsuario = username;
                            userValido = true;
                        }
                    }

                    System.out.print("Ingrese su contraseña: ");
                    String contrasena = scMain.nextLine();
            
                    System.out.println("REGISTRO EXITOSO! COMPLETE SUS DATOS");
                    System.out.print("Ingrese su nombre: ");
                    String nombrePersona = scMain.nextLine();
                    System.out.print("Ingrese el nombre del establecimiento: ");
                    String nombreEstablecimiento = scMain.nextLine();
                    System.out.print("Ingrese la dirección del lugar: ");
                    String direccion = scMain.nextLine();
                    System.out.print("Elija el tipo de prioridad para pedidos\n(0- Orden de llegada, 1- Número de artículos): ");
                    int tipoOrden = scMain.nextInt();
            
                    Usuario usuarioNuevo = new Usuario(nombreDeUsuario, contrasena, email, nombrePersona, nombreEstablecimiento, direccion, tipoOrden);
                    cuentas.insertar(usuarioNuevo);
                    break;

                case 2:
                    System.out.println("BUSCAR UN USUARIO (POR USERNAME)");
                    scMain.nextLine();
                    System.out.print("Ingrese el usuario a consultar: ");
                    String userConsulta = scMain.nextLine();

                    Usuario userObtenido = cuentas.obtener(userConsulta);

                    if (userObtenido == null) {
                        System.out.println("Usuario no encontrado.");
                    }
                    else {
                        System.out.println("Usuario " + userObtenido.getNombreDeUsuario() + " encontrado.");
                        System.out.println("Maneja el establecimiento " + userObtenido.getNombreEstablecimiento());
                        System.out.println("Orden de manejo de turnos: " + userObtenido.getTurnos().getTipoOrden());
                    }
                    break;

                case 3:
                    scMain.close();
                    scMenuSesion.close();
                    scMenuProductos.close();
                    scMenuDinero.close();
                    scMenuTurnos.close();
                    return;

                default:
                    break;
            }
        }
    }
}
