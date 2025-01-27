package fr.voteright.algorithms.utils;

public interface ListI<T> extends BTreeI<T>{
    T data();
    List<T> tail();
    List<T> parent();
    int length();
    void setData(T data);
    void update(int i, T data);
    void setTail(List<T> l);
    void add (int pos, T data);
    List<T> remove(int i);
    List<T> copy();
    String toString();
    void display();

    /* Les méthodes suivantes sont ajoutées pour que les listes fonctionnent pour les fonctions acceptant des arbres
     * mais ne sont pas utiles en général.
     */
     
    List<T> left();
    List<T> right();
    void setLeft(List<T> t);
    List<T> child(int i);
    int nbChildren();
    @SuppressWarnings("unchecked")
    void addChildren(List<T>... childs);
    void setChild(int i, List<T> child);
}