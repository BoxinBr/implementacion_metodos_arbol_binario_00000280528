public interface ITree<T> {

    public T getRootData() throws TreeException;
    public int getHeight();
    public int getNumberNodes();
    public boolean empty();
    public void clear();
}
