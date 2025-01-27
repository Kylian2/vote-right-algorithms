package fr.voteright.algorithms.utils;

public interface BTreeI<T> extends TreeI<T>{
    T data();
    BTree<T> parent();
    BTree<T> left();
    void setLeft(BTree<T> t);
    BTree<T> right();
    void setRight(BTree<T> t);
    void remove();
    String toString();
    void display();

    /* Les méthodes suivantes sont ajoutées pour que les arbres binaires BTree fonctionnent pour les fonctions acceptant des arbres Tree
     * mais ne sont pas utiles si l'arbre est binaire.
     */
    Tree<T> child(int i);
    int nbChildren();
    @SuppressWarnings("unchecked")
    void addChildren(BTree<T>... childs);
    void setChild(int i, BTree<T> child);
}