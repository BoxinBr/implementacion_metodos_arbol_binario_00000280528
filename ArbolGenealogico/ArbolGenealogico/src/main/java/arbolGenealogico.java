import java.util.Iterator;

// Elaborado por: Jesús Rafael López Campas

public class arbolGenealogico {

    private static BinaryTree<Familiar> crearArbolGenealogico() {
        Familiar yo = new Familiar("Jesus Rafael Lopez Campas", 19, "Estudiante", false, "Navojoa, Sonora");

        Familiar padre = new Familiar("Rafael Lopez", 39, "Empleado", false, "Navojoa, Sonora");
        Familiar madre = new Familiar("Rosario Campas", 38, "Empleada", false, "Navojoa, Sonora");

        Familiar abueloPaterno = new Familiar("Rafael Lopez Diaz", 63, "Jubilado", false, "Sinaloa");
        Familiar abuelaPaterna = new Familiar("Irma Valenzuela", 59, "Ama de casa", false, "Sonora");
        Familiar abueloMaterno = new Familiar("Alvaro Campas", 69, "Ganadero", false, "Mexiquillo, Sonora");
        Familiar abuelaMaterna = new Familiar("Olga Gracia", 65, "Ama de casa", false, "Tepahui, Sonora");

        BinaryTree<Familiar> arbolPadre = new BinaryTree<>(
                padre,
                new BinaryTree<>(abueloPaterno),
                new BinaryTree<>(abuelaPaterna));

        BinaryTree<Familiar> arbolMadre = new BinaryTree<>(
                madre,
                new BinaryTree<>(abueloMaterno),
                new BinaryTree<>(abuelaMaterna));

        return new BinaryTree<>(yo, arbolPadre, arbolMadre);
    }

    private static void recorrer(String encabezado, Iterator<Familiar> iterador) {
        System.out.println(encabezado);
        int posicion = 1;
        
        while (iterador.hasNext()) {
            System.out.println("  " + posicion + ". " + iterador.next());
            posicion++;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        BinaryTree<Familiar> arbol = crearArbolGenealogico();

        System.out.println("Raiz del arbol: " + arbol.getRootData());
        System.out.println("Altura: " + arbol.getHeight());
        System.out.println("Numero de familiares: " + arbol.getNumberNodes());
        System.out.println();

        recorrer("Recorrido en orden:", arbol.getInorderIterator());
        recorrer("Recorrido en preorden:", arbol.getPreorderIterator());
        recorrer("Recorrido en postorden:", arbol.getPostorderIterator());
    }
}
