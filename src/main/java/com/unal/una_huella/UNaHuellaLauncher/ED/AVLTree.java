package com.unal.una_huella.UNaHuellaLauncher.ED;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Mascota;
import com.unal.una_huella.UNaHuellaLauncher.Entities.Usuario;

import java.util.ArrayList;

public class AVLTree<T> {
    private NodoTree<T> root;
    public static final int ID = 1;
    public static final int PRIMER_NOMBRE = 2;
    public static final int PRIMER_APELLIDO = 3;
    public static final int N_MASCOTAS = 4;
    public static final int ESTRATO = 5;
    public static final int NIVEL_ACCESO = 6;
    public static final int EXP = 7;
    public static final int ID_DUEÑO = 1;
    public static final int NOMBRE_MASCOTA = 2;
    public static final int EDAD_MASCOTA = 3;
    private int order;

    public AVLTree(int parametroOrdenamiento) {
        this.root = null;
        this.order = parametroOrdenamiento;
    }

    public AVLTree(int parametroOrdenamiento, NodoTree<T> root) {
        this.root = root;
        this.order = parametroOrdenamiento;
    }

    public void emptyTree() {
        root = null;
    }

    /**
     * Agrega un elemento al arbol según el valor dado
     *
     * @param key contiene el valor del elemento que se va a insertar
     */
    private void insert(T key) {
        NodoTree newNode = new NodoTree<T>(key);
        if (this.root == null) {
            this.root = newNode;
            return;
        }

        NodoTree currentNode = root;
        NodoTree parentNode = null;
        while (true) {
            parentNode = currentNode;
            if (key instanceof Integer) {
                if ((Integer) key < (int) currentNode.getKey()) {
                    currentNode = currentNode.getLeft();
                    if (currentNode == null) {
                        parentNode.setLeft(newNode);
                        newNode.setParent(parentNode);
                        return;
                    }
                } else {
                    currentNode = currentNode.getRight();
                    if (currentNode == null) {
                        parentNode.setRight(newNode);
                        newNode.setParent(parentNode);
                        return;
                    }
                }
            } else if (key instanceof String) {
                if ((((String) key).compareTo(((String) currentNode.getKey()))) < 0) {
                    currentNode = currentNode.getLeft();
                    if (currentNode == null) {
                        parentNode.setLeft(newNode);
                        newNode.setParent(parentNode);
                        return;
                    }
                } else {
                    currentNode = currentNode.getRight();
                    if (currentNode == null) {
                        parentNode.setRight(newNode);
                        newNode.setParent(parentNode);
                        return;
                    }
                }
            } else if (key instanceof Usuario) {
                switch (order) {
                    case 1: {        //  id
                        long id = Long.parseLong(((Usuario) key).getId_usuario());
                        if (id < Long.parseLong(((Usuario) currentNode.getKey()).getId_usuario())) {
                            currentNode = currentNode.getLeft();
                            if (currentNode == null) {
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        } else {
                            currentNode = currentNode.getRight();
                            if (currentNode == null) {
                                parentNode.setRight(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        }
                        break;
                    }
                    case 2: {        //  primer nombre
                        String primerNombre = ((Usuario) key).getA_primer_nombre();

                        if (primerNombre.compareTo(((Usuario) currentNode.getKey()).getA_primer_nombre()) < 0) {
                            currentNode = currentNode.getLeft();
                            if (currentNode == null) {
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        } else if (primerNombre.compareTo(((Usuario) currentNode.getKey()).getA_primer_nombre()) == 0) {
                            currentNode = currentNode.getLeft();
                            if (currentNode != null) {
                                currentNode.setParent(newNode);
                                newNode.setLeft(currentNode);
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            } else {
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        } else {
                            currentNode = currentNode.getRight();
                            if (currentNode == null) {
                                parentNode.setRight(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        }
                        break;
                    }
                    case 3: {        //  primer apellido
                        String primerApellido = ((Usuario) key).getB_primer_apellido();

                        if (primerApellido.compareTo(((Usuario) currentNode.getKey()).getB_primer_apellido()) < 0) {
                            currentNode = currentNode.getLeft();
                            if (currentNode == null) {
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        } else if (primerApellido.compareTo(((Usuario) currentNode.getKey()).getB_primer_apellido()) == 0) {
                            currentNode = currentNode.getLeft();
                            if (currentNode != null) {
                                currentNode.setParent(newNode);
                                newNode.setLeft(currentNode);
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            } else {
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        } else {
                            currentNode = currentNode.getRight();
                            if (currentNode == null) {
                                parentNode.setRight(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        }
                        break;
                    }
                    case 4: {        //  #mascotas
                        int id = ((Usuario) key).getH_cantidad_mascotas();
                        int nodo = ((Usuario) currentNode.getKey()).getH_cantidad_mascotas();
                        if (id < nodo) {
                            currentNode = currentNode.getLeft();
                            if (currentNode == null) {
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        } else if (id == nodo) {
                            currentNode = currentNode.getLeft();
                            if (currentNode != null) {
                                currentNode.setParent(newNode);
                                newNode.setLeft(currentNode);
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            } else {
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        } else {
                            currentNode = currentNode.getRight();
                            if (currentNode == null) {
                                parentNode.setRight(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        }
                        break;
                    }
                    case 5: {        //  estrato
                        String estrato = ((Usuario) key).getI_estrato();

                        if (estrato.compareTo(((Usuario) currentNode.getKey()).getI_estrato()) < 0) {
                            currentNode = currentNode.getLeft();
                            if (currentNode == null) {
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        } else if (estrato.compareTo(((Usuario) currentNode.getKey()).getI_estrato()) == 0) {
                            currentNode = currentNode.getLeft();
                            if (currentNode != null) {
                                currentNode.setParent(newNode);
                                newNode.setLeft(currentNode);
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            } else {
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        } else {
                            currentNode = currentNode.getRight();
                            if (currentNode == null) {
                                parentNode.setRight(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        }
                        break;
                    }
                    case 6: {        //  accessLvl
                        String access = ((Usuario) key).getK_nivel_acceso();

                        if (access.compareTo(((Usuario) currentNode.getKey()).getK_nivel_acceso()) < 0) {
                            currentNode = currentNode.getLeft();
                            if (currentNode == null) {
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        } else if (access.compareTo(((Usuario) currentNode.getKey()).getK_nivel_acceso()) == 0) {
                            currentNode = currentNode.getLeft();
                            if (currentNode != null) {
                                currentNode.setParent(newNode);
                                newNode.setLeft(currentNode);
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            } else {
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        } else {
                            currentNode = currentNode.getRight();
                            if (currentNode == null) {
                                parentNode.setRight(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        }
                        break;
                    }
                    case 7: {        //  exp
                        int exp = ((Usuario) key).getN_anos_experiencia();
                        if (exp < ((Usuario) currentNode.getKey()).getN_anos_experiencia()) {
                            currentNode = currentNode.getLeft();
                            if (currentNode == null) {
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        } else if (exp == ((Usuario) currentNode.getKey()).getN_anos_experiencia()) {
                            currentNode = currentNode.getLeft();
                            if (currentNode != null) {
                                currentNode.setParent(newNode);
                                newNode.setLeft(currentNode);
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            } else {
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        } else {
                            currentNode = currentNode.getRight();
                            if (currentNode == null) {
                                parentNode.setRight(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        }
                        break;
                    }
                    default: {
                        break;
                    }
                }
            } else if (key instanceof Mascota) {
                switch (order) {
                    case 1: {   //  id_mascota
                        long id_mascota = Long.parseLong(((Mascota) key).getI_id_usuario().getId_usuario());
                        long id_nodo = Long.parseLong(((Mascota) currentNode.getKey()).getI_id_usuario().getId_usuario());
                        if (id_mascota < id_nodo) {
                            currentNode = currentNode.getLeft();
                            if (currentNode == null) {
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        } else if (id_mascota == id_nodo) {
                            currentNode = currentNode.getLeft();
                            if (currentNode != null) {
                                currentNode.setParent(newNode);
                                newNode.setLeft(currentNode);
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            } else {
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        } else {
                            currentNode = currentNode.getRight();
                            if (currentNode == null) {
                                parentNode.setRight(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        }
                        break;
                    }

                    case 2: {   //  nombre_mascota
                        String nombre_mascota = (((Mascota) key).getB_nombre_mascota());
                        String nombre_nodo = ((Mascota) currentNode.getKey()).getB_nombre_mascota();
                        if (nombre_mascota.compareTo(nombre_nodo) < 0) {
                            currentNode = currentNode.getLeft();
                            if (currentNode == null) {
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        } else if (nombre_mascota.compareTo(nombre_nodo) == 0) {
                            currentNode = currentNode.getLeft();
                            if (currentNode != null) {
                                currentNode.setParent(newNode);
                                newNode.setLeft(currentNode);
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            } else {
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        } else {
                            currentNode = currentNode.getRight();
                            if (currentNode == null) {
                                parentNode.setRight(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        }
                        break;
                    }

                    case 3: {   //edad_mascota
                        int edad_mascota = (((Mascota) key).getE_edad_mascota());
                        int edad_nodo = ((Mascota) currentNode.getKey()).getE_edad_mascota();
                        if (edad_mascota < edad_nodo) {
                            currentNode = currentNode.getLeft();
                            if (currentNode == null) {
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        } else if (edad_mascota == edad_nodo) {
                            currentNode = currentNode.getLeft();
                            if (currentNode != null) {
                                currentNode.setParent(newNode);
                                newNode.setLeft(currentNode);
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            } else {
                                parentNode.setLeft(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        } else {
                            currentNode = currentNode.getRight();
                            if (currentNode == null) {
                                parentNode.setRight(newNode);
                                newNode.setParent(parentNode);
                                return;
                            }
                        }
                        break;
                    }
                }
            } else {
                break;
            }
        }
    }

    /**
     * inserta un elemento al arbol y balancea
     *
     * @param key elemento que se va a insertar
     */
    public void insertAVL(T key) throws Exception {
        insert(key);
        NodoTree newNode = findByKey(key, root);
        rebalance(newNode);
    }

    /**
     * Permite buscar un elemento dado su valor.
     *
     * @param key parámetro que contiene el valor a buscar
     * @return el nodo si el elemento existe o null en caso contrario
     */
    public NodoTree<T> findByKey(T key, NodoTree nodo) throws Exception {
        if (nodo != null) {
            if (key instanceof String) {
                if ((((String) key).compareTo(((String) nodo.getKey()))) == 0) {
                    return nodo;
                } else if ((((String) key).compareTo(((String) nodo.getKey()))) < 0) {
                    if (nodo.getLeft() != null) {
                        return findByKey(key, nodo.getLeft());
                    } else {
                        return nodo.getLeft();
                    }
                } else {
                    if (nodo.getRight() != null) {
                        return findByKey(key, nodo.getRight());
                    } else {
                        return nodo.getRight();
                    }
                }
            } else if (key instanceof Usuario) {
                switch (order) {
                    case 1: {        //  id
                        long id = Long.parseLong(((Usuario) key).getId_usuario());
                        if (id == Long.parseLong(((Usuario) nodo.getKey()).getId_usuario())) {
                            return nodo;
                        } else if (id < Long.parseLong(((Usuario) nodo.getKey()).getId_usuario())) {
                            if (nodo.getLeft() != null) {
                                return findByKey(key, nodo.getLeft());
                            } else {
                                return nodo.getLeft();
                            }
                        } else {
                            if (nodo.getRight() != null) {
                                return findByKey(key, nodo.getRight());
                            } else {
                                return nodo.getRight();
                            }
                        }
                    }
                    case 2: {        //  primer nombre
                        String nombre = ((Usuario) key).getA_primer_nombre();
                        if (nombre.compareTo(((Usuario) nodo.getKey()).getA_primer_nombre()) == 0) {
                            return nodo;
                        } else if (nombre.compareTo(((Usuario) nodo.getKey()).getA_primer_nombre()) < 0) {
                            if (nodo.getLeft() != null) {
                                return findByKey(key, nodo.getLeft());
                            } else {
                                return nodo.getLeft();
                            }
                        } else {
                            if (nodo.getRight() != null) {
                                return findByKey(key, nodo.getRight());
                            } else {
                                return nodo.getRight();
                            }
                        }
                    }
                    case 3: {        //  primer apellido
                        String apellido = ((Usuario) key).getB_primer_apellido();
                        if (apellido.compareTo(((Usuario) nodo.getKey()).getB_primer_apellido()) == 0) {
                            return nodo;
                        } else if (apellido.compareTo(((Usuario) nodo.getKey()).getB_primer_apellido()) < 0) {
                            if (nodo.getLeft() != null) {
                                return findByKey(key, nodo.getLeft());
                            } else {
                                return nodo.getLeft();
                            }
                        } else {
                            if (nodo.getRight() != null) {
                                return findByKey(key, nodo.getRight());
                            } else {
                                return nodo.getRight();
                            }
                        }
                    }
                    case 4: {        //  #mascotas
                        int mascotas = ((Usuario) key).getH_cantidad_mascotas();
                        if (mascotas == ((Usuario) nodo.getKey()).getH_cantidad_mascotas()) {
                            return nodo;
                        } else if (mascotas < ((Usuario) nodo.getKey()).getH_cantidad_mascotas()) {
                            if (nodo.getLeft() != null) {
                                return findByKey(key, nodo.getLeft());
                            } else {
                                return nodo.getLeft();
                            }
                        } else {
                            if (nodo.getRight() != null) {
                                return findByKey(key, nodo.getRight());
                            } else {
                                return nodo.getRight();
                            }
                        }
                    }
                    case 5: {        //  estrato
                        String estrato = ((Usuario) key).getI_estrato();
                        if (estrato.compareTo(((Usuario) nodo.getKey()).getI_estrato()) == 0) {
                            return nodo;
                        } else if (estrato.compareTo(((Usuario) nodo.getKey()).getI_estrato()) < 0) {
                            if (nodo.getLeft() != null) {
                                return findByKey(key, nodo.getLeft());
                            } else {
                                return nodo.getLeft();
                            }
                        } else {
                            if (nodo.getRight() != null) {
                                return findByKey(key, nodo.getRight());
                            } else {
                                return nodo.getRight();
                            }
                        }
                    }
                    case 6: {        //  access
                        String access = ((Usuario) key).getK_nivel_acceso();
                        if (access.compareTo(((Usuario) nodo.getKey()).getK_nivel_acceso()) == 0) {
                            return nodo;
                        } else if (access.compareTo(((Usuario) nodo.getKey()).getK_nivel_acceso()) < 0) {
                            if (nodo.getLeft() != null) {
                                return findByKey(key, nodo.getLeft());
                            } else {
                                return nodo.getLeft();
                            }
                        } else {
                            if (nodo.getRight() != null) {
                                return findByKey(key, nodo.getRight());
                            } else {
                                return nodo.getRight();
                            }
                        }
                    }
                    case 7: {        //  exp
                        int exp = ((Usuario) key).getN_anos_experiencia();
                        if (exp == ((Usuario) nodo.getKey()).getN_anos_experiencia()) {
                            return nodo;
                        } else if (exp < ((Usuario) nodo.getKey()).getN_anos_experiencia()) {
                            if (nodo.getLeft() != null) {
                                return findByKey(key, nodo.getLeft());
                            } else {
                                return nodo.getLeft();
                            }
                        } else {
                            if (nodo.getRight() != null) {
                                return findByKey(key, nodo.getRight());
                            } else {
                                return nodo.getRight();
                            }
                        }
                    }
                    default: {
                        throw new Exception("parametro de orden invalido");
                    }
                }
            } else if (key instanceof Mascota) {
                switch (order) {
                    case 1: {    //id_dueño
                        long id_mascota = Long.parseLong(((Mascota) key).getI_id_usuario().getId_usuario());
                        long id_nodo = Long.parseLong(((Mascota) nodo.getKey()).getI_id_usuario().getId_usuario());
                        if (id_mascota == id_nodo) {
                            return nodo;
                        } else if (id_mascota < id_nodo) {
                            if (nodo.getLeft() != null) {
                                return findByKey(key, nodo.getLeft());
                            } else {
                                return nodo.getLeft();
                            }
                        } else {
                            if (nodo.getRight() != null) {
                                return findByKey(key, nodo.getRight());
                            } else {
                                return nodo.getRight();
                            }
                        }
                    }

                    case 2: {    //  nombre mascota
                        String nombre_mascota = (((Mascota) key).getB_nombre_mascota());
                        String nombre_nodo = ((Mascota) nodo.getKey()).getB_nombre_mascota();
                        if (nombre_mascota.compareTo(nombre_nodo) == 0) {
                            return nodo;
                        } else if (nombre_mascota.compareTo(nombre_nodo) < 0) {
                            if (nodo.getLeft() != null) {
                                return findByKey(key, nodo.getLeft());
                            } else {
                                return nodo.getLeft();
                            }
                        } else {
                            if (nodo.getRight() != null) {
                                return findByKey(key, nodo.getRight());
                            } else {
                                return nodo.getRight();
                            }
                        }
                    }

                    case 3: {    //  edad mascota
                        int edad_mascota = (((Mascota) key).getE_edad_mascota());
                        int edad_nodo = ((Mascota) nodo.getKey()).getE_edad_mascota();
                        if (edad_mascota == edad_nodo) {
                            return nodo;
                        } else if (edad_mascota < edad_nodo) {
                            if (nodo.getLeft() != null) {
                                return findByKey(key, nodo.getLeft());
                            } else {
                                return nodo.getLeft();
                            }
                        } else {
                            if (nodo.getRight() != null) {
                                return findByKey(key, nodo.getRight());
                            } else {
                                return nodo.getRight();
                            }
                        }
                    }
                    default: {
                        throw new Exception("parametro de orden invalido");
                    }
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * busca un elemento en el arbol
     *
     * @param key  elemento que se va a buscar
     * @param nodo nodo que toma como raiz para empezar a buscar
     * @return valor key del noto si se encuentra, null en caso contrario
     */
    public T find(T key, NodoTree nodo) throws Exception {
        if (nodo == null) {
            return null;
        } else {
            NodoTree temp = findByKey(key, nodo);
            if (temp == null) {
                return null;
            } else {
                return (T) temp.getKey();
            }
        }

    }

    private NodoTree delete(NodoTree nodo) {
        NodoTree parent = nodo.getParent();
        NodoTree X = next(nodo);
        NodoTree subparent = X.getParent();

        if (subparent.getLeft() == X) {
            if (X.getRight() != null) {
                X.getRight().setParent(subparent);
            }
            subparent.setLeft(X.getRight());
        } else {
            if (X.getLeft() != null) {
                X.getLeft().setParent(subparent);
            }
            subparent.setRight(X.getLeft());
        }

        if (nodo.getLeft() != X && nodo.getLeft() != null) {
            X.setLeft(nodo.getLeft());
        } else {
            X.setLeft(null);
        }
        if (nodo.getRight() != X && nodo.getRight() != null) {
            X.setRight(nodo.getRight());
        } else {
            X.setRight(null);
        }
        X.setParent(parent);
        if (nodo.getLeft() != null) {
            nodo.getLeft().setParent(X);
        }
        if (nodo.getRight() != null) {
            nodo.getRight().setParent(X);
        }
        if (parent != null) {
            if (parent.getLeft() == nodo) {
                parent.setLeft(X);
            } else {
                parent.setRight(X);
            }
        } else {
            setRoot(X);
        }


        nodo.setParent(null);
        nodo.setRight(null);
        nodo.setLeft(null);

        return nodo;
    }

    /**
     * elimina un elemento del arbol y retorna su valor
     *
     * @param key el valor del elemento que se va a buscar
     * @return el valor del elemento que se eliminó
     */
    public T deleteAVL(T key) throws Exception {
        NodoTree deletedNode;
        NodoTree nodo = findByKey(key, root);
        if (nodo != null) {
            NodoTree parent = nodo.getParent();
            if (nodo.getRight() == null && nodo.getLeft() == null) {
                if (parent.getLeft() == nodo) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
                rebalance(parent);
                nodo.setParent(null);
                deletedNode = nodo;
            } else {
                NodoTree X = next(nodo);
                NodoTree subparent = X.getParent();
                if (subparent != nodo) {
                    deletedNode = delete(nodo);
                    rebalance(subparent);
                    return (T) deletedNode.getKey();
                } else {
                    deletedNode = delete(nodo);
                    rebalance(X);
                }
            }
            return (T) deletedNode.getKey();
        }
        return null;
    }

    /**
     * analiza los hijos de la raiz y rebalancea de ser necesario
     *
     * @param nodo nodo que toma como raiz del sub-arbol
     */
    private void rebalance(NodoTree nodo) {
        NodoTree parent = nodo.getParent();
        int l = 0;
        int r = 0;

        if (nodo.getLeft() == null) {
            l = 0;
        } else {
            l = nodo.getLeft().getHeight();
        }

        if (nodo.getRight() == null) {
            r = 0;
        } else {
            r = nodo.getRight().getHeight();
        }
        if (l > r + 1) {
            rebalanceRight(nodo);
        }
        if (r > l + 1) {
            rebalanceLeft(nodo);
        }
        adjustHeight(nodo);

        if (nodo.getParent() != null) {
            rebalance(nodo.getParent());
        }
    }

    /**
     * rebalancea hacia la derecha
     * hace doble rotación de ser necesario
     *
     * @param nodo nodo afectado que está desbalanceado a la izquierda
     */
    private void rebalanceRight(NodoTree nodo) {
        NodoTree tempNodo = nodo.getLeft();
        int l = 0;
        int r = 0;

        if (tempNodo.getLeft() == null) {
            l = 0;
        } else {
            l = tempNodo.getLeft().getHeight();
        }

        if (tempNodo.getRight() == null) {
            r = 0;
        } else {
            r = tempNodo.getRight().getHeight();
        }

        if (r > l) {
            rotateLeft(tempNodo);
            adjustHeight(tempNodo);
        }
        rotateRight(nodo);
        adjustHeight(nodo);
    }

    /**
     * rebalancea hacia la izquierda
     * hace doble rotación de ser necesario
     *
     * @param nodo nodo afectado que está desbalanceado a la derecha
     */
    private void rebalanceLeft(NodoTree nodo) {
        NodoTree tempNodo = nodo.getRight();
        int l = 0;
        int r = 0;

        if (tempNodo.getLeft() == null) {
            l = 0;
        } else {
            l = tempNodo.getLeft().getHeight();
        }

        if (tempNodo.getRight() == null) {
            r = 0;
        } else {
            r = tempNodo.getRight().getHeight();
        }
        if (l > r) {
            rotateRight(tempNodo);
            adjustHeight(tempNodo);
        }
        rotateLeft(nodo);
        adjustHeight(nodo);
    }

    /**
     * hace una rotación hacia la derecha del nodo
     *
     * @param nodo nodo afectado por la rotación
     */
    private void rotateRight(NodoTree nodo) {
        NodoTree temp = nodo.getLeft();
        NodoTree parent = nodo.getParent();
        nodo.setParent(temp);
        nodo.setLeft(temp.getRight());
        if (nodo.getLeft() != null) {
            nodo.getLeft().setParent(nodo);
        }
        temp.setRight(nodo);
        temp.setParent(parent);
        if (parent != null) {
            if (parent.getLeft() == nodo) {
                parent.setLeft(temp);
            } else {
                parent.setRight(temp);
            }
        } else {
            setRoot(temp);
        }
    }

    /**
     * hace una rotación hacia la izquierda del nodo
     *
     * @param nodo nodo afectado por la rotación
     */
    private void rotateLeft(NodoTree nodo) {
        NodoTree temp = nodo.getRight();
        NodoTree parent = nodo.getParent();
        nodo.setParent(temp);
        nodo.setRight(temp.getLeft());
        if (nodo.getRight() != null) {
            nodo.getRight().setParent(nodo);
        }
        temp.setLeft(nodo);
        temp.setParent(parent);
        if (parent != null) {
            if (parent.getLeft() == nodo) {
                parent.setLeft(temp);
            } else {
                parent.setRight(temp);
            }
        } else {
            setRoot(temp);
        }
    }

    /**
     * ajusta el parametro height de cada nodo de acuerdo a la altura de sus hijos
     *
     * @param nodo elemento al que se le ajusta su altura
     */
    private void adjustHeight(NodoTree nodo) {
        int l = 0;
        int r = 0;
        int max;

        if (nodo.getLeft() == null) {
            l = 0;
        } else {
            l = nodo.getLeft().getHeight();
        }

        if (nodo.getRight() == null) {
            r = 0;
        } else {
            r = nodo.getRight().getHeight();
        }

        if (l > r) {
            max = l;
        } else {
            max = r;
        }
        nodo.setHeight(1 + max);
    }

    /**
     * busca el antecesor izquierdo o el sucesor derecho del nodo
     *
     * @param nodo elemento al que se le busca antecesor o sucesor
     * @return el nodo que cumple ser siguiente o anterior al nodo
     */
    private NodoTree next(NodoTree nodo) {
        if (nodo.getRight() != null) {
            return leftDescendant(nodo.getRight());
        } else if (nodo.getLeft() != null) {
            return rightAncestor(nodo.getLeft());
        } else {
            return null;
        }
    }

    private NodoTree leftDescendant(NodoTree nodo) {
        if (nodo.getLeft() == null) {
            return nodo;
        } else {
            return leftDescendant(nodo.getLeft());
        }
    }

    private NodoTree rightAncestor(NodoTree nodo) {      //  implementar demás instancias
        if (nodo.getRight() == null) {
            return nodo;
        } else {
            return rightAncestor(nodo.getRight());
        }

        /*if (nodo.getKey() instanceof String) {
            if (((String) nodo.getKey()).compareTo(((String) nodo.getParent().getKey())) < 0) {
                return nodo.getParent();
            } else {
                return rightAncestor(nodo.getParent());
            }
        } else {
            return null;
        }*/
    }

    /**
     * Imprime en consola los valores de cada elemento en el arbol en forma inOrder
     *
     * @param root nodo que toma como raiz para empezar a recorrer
     */
    public void displayInOrder(NodoTree root) {
        if (root != null) {
            if (root.getKey() instanceof Usuario) {
                displayInOrder(root.getLeft());
                System.out.println("\t" + ((Usuario) root.getKey()).getId_usuario() + "\t" + ((Usuario) root.getKey()).getA_primer_nombre() + "\t" + ((Usuario) root.getKey()).getB_primer_apellido());
                displayInOrder(root.getRight());
            }
        }
    }

    private java.util.List<T> fillList(NodoTree root, java.util.List<T> list) {
        if (root != null) {
            if (root.getKey() instanceof Usuario) {
                fillList(root.getLeft(), list);
                list.add((T) root.getKey());
                fillList(root.getRight(), list);
            }
            return list;
        }
        return null;
    }


    public java.util.List<T> getList() {
        java.util.List<T> list = new ArrayList<T>();
        list = fillList(root, list);
        return list;
    }

    /**
     * cuenta numero de elementos de un arbol empezando desde el nodo dado, contando dicho nodo
     *
     * @param root nodo que toma como raiz para empezar a recorrer
     * @return numero de elementos del arbol, incluyendo el nodo root
     */
    public int countInOrder(NodoTree root) {
        int count = 0;
        if (root != null) {
            count += countInOrder(root.getLeft());
            count++;
            count += countInOrder(root.getRight());
        }
        return count;
    }


    public void displayPostOrder(NodoTree root) {
        if (root != null) {
            displayPostOrder(root.getLeft());
            displayPostOrder(root.getRight());
            System.out.println(" " + root.getKey());
        }
    }

    public void displayPreOrder(NodoTree root) {
        if (root != null) {
            System.out.println(" " + root.getKey());
            displayPreOrder(root.getLeft());
            displayPreOrder(root.getRight());
        }
    }

    public NodoTree<T> getRoot() {
        return root;
    }

    public void setRoot(NodoTree<T> root) {
        this.root = root;
    }

    public NodoTree<T> findMin(NodoTree nodo) {
        if (nodo.getLeft() == null) {
            return nodo;
        } else {
            return findMin(nodo.getLeft());
        }
    }

    public NodoTree<T> findMax(NodoTree nodo) {
        if (nodo.getRight() == null) {
            return nodo;
        } else {
            return findMax(nodo.getRight());
        }
    }

}