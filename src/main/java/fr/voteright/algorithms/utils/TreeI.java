package fr.voteright.algorithms.utils;

public interface TreeI<T>{
    T data();
    Tree<T> child(int i);
    int nbChildren();
    Tree<T> parent();
    @SuppressWarnings("unchecked")
    void addChildren(Tree<T>... childs);
    void setChild(int i, Tree<T> child);
    void remove();
    String toString();
    void display();
}