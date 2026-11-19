import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinaryTree<T> extends BinaryTreeComun<T> implements ITreeIterator<T> {

    private class InorderIterator implements Iterator<T> {
        private final LinkedListStack<NodoArbolBinario<T>> pilaNodos = new LinkedListStack<>();

        public InorderIterator() {
            pushSubArbolIzquierdo(raiz);
        }

        private void pushSubArbolIzquierdo(NodoArbolBinario<T> nodo) {
            while (nodo != null) {
                pilaNodos.push(nodo);
                nodo = nodo.hijoIzq;
            }
        }

        @Override
        public boolean hasNext() {
            return !pilaNodos.empty();
        }

        @Override
        public T next() {
            if (pilaNodos.empty()) {
                throw new NoSuchElementException();
            }
            NodoArbolBinario<T> nodoSig = pilaNodos.pop();
            pushSubArbolIzquierdo(nodoSig.hijoDer);
            return nodoSig.dato;
        }
    }

    private class PreorderIterator implements Iterator<T> {
        private final LinkedListStack<NodoArbolBinario<T>> pilaNodos = new LinkedListStack<>();

        public PreorderIterator() {
            if (raiz != null) {
                pilaNodos.push(raiz);
            }
        }

        @Override
        public boolean hasNext() {
            return !pilaNodos.empty();
        }

        @Override
        public T next() {
            if (pilaNodos.empty()) {
                throw new NoSuchElementException();
            }
            NodoArbolBinario<T> nodoSig = pilaNodos.pop();
            if (nodoSig.hijoDer != null) {
                pilaNodos.push(nodoSig.hijoDer);
            }
            if (nodoSig.hijoIzq != null) {
                pilaNodos.push(nodoSig.hijoIzq);
            }
            return nodoSig.dato;
        }
    }

    private class PostorderIterator implements Iterator<T> {
        private final LinkedListStack<NodoArbolBinario<T>> pilaNodos = new LinkedListStack<>();
        private NodoArbolBinario<T> nodoActual = raiz;
        private NodoArbolBinario<T> ultimoVisitado = null;

        @Override
        public boolean hasNext() {
            return nodoActual != null || !pilaNodos.empty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            while (true) {
                while (nodoActual != null) {
                    pilaNodos.push(nodoActual);
                    nodoActual = nodoActual.hijoIzq;
                }
                NodoArbolBinario<T> tope = pilaNodos.peek();
                if (tope.hijoDer != null && ultimoVisitado != tope.hijoDer) {
                    nodoActual = tope.hijoDer;
                } else {
                    pilaNodos.pop();
                    ultimoVisitado = tope;
                    return tope.dato;
                }
            }
        }
    }

    public BinaryTree() {
        raiz = null;
    }

    public BinaryTree(T datoRaiz) {
        raiz = new NodoArbolBinario<>(datoRaiz);
    }

    public BinaryTree(T datoRaiz, BinaryTree<T> arbolIzq, BinaryTree<T> arbolDer) {
        setTree(datoRaiz, arbolIzq, arbolDer);
    }

    private void setTree(T datoRaiz, BinaryTree<T> arbolIzq, BinaryTree<T> arbolDer) {
        raiz = new NodoArbolBinario<>(datoRaiz);
        if (arbolIzq != null && !arbolIzq.empty()) {
            raiz.hijoIzq = copy(arbolIzq.raiz);
        }
        if (arbolDer != null && !arbolDer.empty()) {
            raiz.hijoDer = copy(arbolDer.raiz);
        }
    }

    private NodoArbolBinario<T> copy(NodoArbolBinario<T> nodo) {
        NodoArbolBinario<T> nuevoNodo = new NodoArbolBinario<>(nodo.dato);
        if (nodo.hijoIzq != null) {
            nuevoNodo.hijoIzq = copy(nodo.hijoIzq);
        }
        if (nodo.hijoDer != null) {
            nuevoNodo.hijoDer = copy(nodo.hijoDer);
        }
        return nuevoNodo;
    }

    @Override
    public Iterator<T> getInorderIterator() {
        return new InorderIterator();
    }

    @Override
    public Iterator<T> getPreorderIterator() {
        return new PreorderIterator();
    }

    @Override
    public Iterator<T> getPostorderIterator() {
        return new PostorderIterator();
    }

    private String recorridoATexto(Iterator<T> iter) {
        StringBuilder sb = new StringBuilder("[");
        if (iter.hasNext()) {
            sb.append(iter.next());
            while (iter.hasNext()) {
                sb.append(", ").append(iter.next());
            }
        }
        return sb.append("]").toString();
    }

    public String toStringInOrder() {
        return recorridoATexto(getInorderIterator());
    }

    public String toStringPreOrder() {
        return recorridoATexto(getPreorderIterator());
    }

    public String toStringPostOrder() {
        return recorridoATexto(getPostorderIterator());
    }
}
