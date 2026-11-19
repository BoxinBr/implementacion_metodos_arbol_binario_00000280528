public class LinkedListStack<T> {

    private class Nodo {
        T dato;
        Nodo siguiente;

        Nodo(T dato, Nodo siguiente) {
            this.dato = dato;
            this.siguiente = siguiente;
        }
    }

    private Nodo tope;

    public void push(T dato) {
        tope = new Nodo(dato, tope);
    }

    public T pop() {
        if (tope == null) {
            return null;
        }
        T dato = tope.dato;
        tope = tope.siguiente;
        return dato;
    }

    public T peek() {
        return tope == null ? null : tope.dato;
    }

    public boolean empty() {
        return tope == null;
    }
}
