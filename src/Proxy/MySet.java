package Proxy;

import library.Modules.Book;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;

/*
 * ${Classname}
 * 
 * Version 1.0 
 * 
 * 11.04.2017
 * 
 * Karpikova
 */
public class MySet implements Set<Book> {

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        System.out.println("op");
        return false;
    }

    @Override
    public Iterator<Book> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Book book) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Book> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Spliterator<Book> spliterator() {
        return null;
    }
}
