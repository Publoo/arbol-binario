package arbolbinario;

import java.util.Scanner;

class nodo {
    int info;
    nodo ligader;
    nodo ligaizq;
}

class ArbolBinario {
    static nodo N = null;

    public static void insertar(nodo N1, int infor) {
        if (infor < N1.info) {
            if (N1.ligaizq == null) {
                nodo nuevo = new nodo();
                nuevo.info = infor;
                nuevo.ligaizq = null;
                nuevo.ligader = null;
                N1.ligaizq = nuevo;
            } else {
                insertar(N1.ligaizq, infor);
            }
        } else if (infor > N1.info) {
            if (N1.ligader == null) {
                nodo nuevo = new nodo();
                nuevo.info = infor;
                nuevo.ligaizq = null;
                nuevo.ligader = null;
                N1.ligader = nuevo;
            } else {
                insertar(N1.ligader, infor);
            }
        } else {
            System.out.println("El nodo ya se encuentra en el árbol.");
        }
    }

    public static void preorden(nodo N1) {
        if (N1 != null) {
            System.out.println(N1.info);
            preorden(N1.ligaizq);
            preorden(N1.ligader);
        }
    }

    public static void inorden(nodo N1) {
        if (N1 != null) {
            inorden(N1.ligaizq);
            System.out.println(N1.info);
            inorden(N1.ligader);
        }
    }

    public static void posorden(nodo N1) {
        if (N1 != null) {
            posorden(N1.ligaizq);
            posorden(N1.ligader);
            System.out.println(N1.info);
        }
    }

    public static void imprimirArbol() {
        if (N == null) {
            System.out.println("El árbol está vacío.");
        } else {
            imprimirArbol(N, 0);
        }
    }

    private static void imprimirArbol(nodo N, int nivel) {
        if (N != null) {
            for (int i = 0; i < nivel; i++) {
                System.out.print("   "); 
            }
            System.out.println(N.info); 

            imprimirArbol(N.ligaizq, nivel + 1); 
            imprimirArbol(N.ligader, nivel + 1); 
        }
    }

    public static void eliminar() {
        Scanner y = new Scanner(System.in);
        System.out.println("Dame el valor a eliminar: ");
        int valor = y.nextInt();
        N = eliminarNodo(N, valor);

        if (N == null) {
            System.out.println("El árbol está ahora vacío.");
        } else {
            System.out.println("Raíz actual del árbol: " + N.info);
        }
    }

    private static nodo eliminarNodo(nodo N1, int valor) {
        if (N1 == null) {
            System.out.println("El valor no se encuentra en el árbol.");
            return null;
        }

        if (valor < N1.info) {
            N1.ligaizq = eliminarNodo(N1.ligaizq, valor);
        } else if (valor > N1.info) {
            N1.ligader = eliminarNodo(N1.ligader, valor);
        } else {
            System.out.println("Eliminando nodo: " + N1.info);

           
            if (N1.ligaizq == null && N1.ligader == null) {
                return null;
            }

            
            if (N1.ligaizq == null) {
                return N1.ligader;
            } else if (N1.ligader == null) {
                return N1.ligaizq;
            }

           
            nodo sucesor = encontrarMinimo(N1.ligader);
            N1.info = sucesor.info;
            N1.ligader = eliminarNodo(N1.ligader, sucesor.info);
        }

        return N1;
    }

    private static nodo encontrarMinimo(nodo N1) {
        while (N1.ligaizq != null) {
            N1 = N1.ligaizq;
        }
        return N1;
    }

     public static boolean busqueda(nodo N1, int valor) {
        if (N1 == null) {
            return false; 
        }

        if (N1.info == valor) {
            return true; 
        } else if (valor < N1.info) {
            return busqueda(N1.ligaizq, valor); 
        } else {
            return busqueda(N1.ligader, valor); 
        }
    }
    
    
    public static void main(String[] args) {
        Scanner y = new Scanner(System.in);
        int opcion = 0;
        int dato;

        if (N == null) {
            N = new nodo();
            System.out.println("Dame dato raíz:");
            dato = y.nextInt();
            N.info = dato;
            N.ligaizq = null;
            N.ligader = null;
        }

        do {
            System.out.println("\nMenú");
            System.out.println("1. Insertar nodo");
            System.out.println("2. Recorrido inorden");
            System.out.println("3. Recorrido preorden");
            System.out.println("4. Recorrido posorden");
            System.out.println("5. Eliminar nodo");
            System.out.println("6. Imprimir árbol");
            System.out.println("7. BUscar un nodo");
            System.out.println("8. Salir.");
            System.out.println("Elige una opción:");
            opcion = y.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Dame dato a insertar:");
                    dato = y.nextInt();
                    insertar(N, dato);
                    break;
                case 2:
                    System.out.println("Recorrido inorden:");
                    inorden(N);
                    break;
                case 3:
                    System.out.println("Recorrido preorden:");
                    preorden(N);
                    break;
                case 4:
                    System.out.println("Recorrido posorden:");
                    posorden(N);
                    break;
                case 5:
                    eliminar();
                    break;
                case 6:
                    System.out.println("Árbol:");
                    imprimirArbol();
                    break;
                case 7:
                    System.out.println("Dame el valor a buscar:");
                    dato = y.nextInt();
                    if (busqueda(N, dato)) {
                        System.out.println("Si se encontro en el arbol.");
                    } else {
                        System.out.println("No se encontro el valor en el arbol");
                    }
                    break;
                   
                case 8:
                    System.out.println("Saliendo...");
                    break;
                
                default:
                    System.out.println("Esa opción no existe. Intenta de nuevo.");
                    break;
            }
        } while (opcion != 8);
    }
}

