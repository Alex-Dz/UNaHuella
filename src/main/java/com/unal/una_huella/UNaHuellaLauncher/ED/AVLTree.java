package com.unal.una_huella.UNaHuellaLauncher.ED;

public class AVLTree<T> {
    private NodoTree<T> root;

    public AVLTree() {
        this.root = null;
    }

    public AVLTree(NodoTree<T> root) {
        this.root = root;
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
            }
        }
    }


    /**
     * Imprime en consola los valores de cada elemento en el arbol en forma inOrder
     *
     * @param root nodo que toma como raiz para empezar a recorrer
     */
    public void displayInOrder(NodoTree root) {
        if (root != null) {
            displayInOrder(root.getLeft());
            System.out.println(" " + root.getKey());
            displayInOrder(root.getRight());
        }
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

    /**
     * Permite buscar un elemento dado su valor.
     *
     * @param key parámetro que contiene el valor a buscar
     * @return el nodo si el elemento existe o null en caso contrario
     */
    public NodoTree<T> findByKey(T key, NodoTree parent) {
        if (parent != null) {
            if (key instanceof String) {
                if ((((String) key).compareTo(((String) parent.getKey()))) == 0) {
                    return parent;
                } else if ((((String) key).compareTo(((String) parent.getKey()))) < 0) {
                    if (parent.getLeft() != null) {
                        return findByKey(key, parent.getLeft());
                    } else {
                        return parent.getLeft();
                    }
                } else {
                    if (parent.getRight() != null) {
                        return findByKey(key, parent.getRight());
                    } else {
                        return parent.getRight();
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
    public T find(T key, NodoTree nodo) {
        if (nodo == null) {
            return null;
        } else {
            return (T) (findByKey(key, nodo).getKey());
        }

    }

    /**
     * inserta un elemento al arbol y balancea
     *
     * @param key elemento que se va a insertar
     */
    public void insertAVL(T key) {
        insert(key);
        NodoTree newNode = findByKey(key, root);
        rebalance(newNode);
    }

    /**
     * analiza los hijos de la raiz y rebalancea de ser necesario
     *
     * @param nodo nodo que toma como raiz del sub-arbol
     */
    public void rebalance(NodoTree nodo) {
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
    public void rebalanceRight(NodoTree nodo) {
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
    public void rebalanceLeft(NodoTree nodo) {
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
    public void rotateRight(NodoTree nodo) {
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
    public void rotateLeft(NodoTree nodo) {
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
    public void adjustHeight(NodoTree nodo) {
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
     * @param key el valor del elemento que se va a buscar
     * @return el valor del elemento que se eliminó
     */
    public T deleteAVL(T key) {
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