package Proxy;

import library.Modules.Book;

import java.lang.reflect.Proxy;
import java.util.Set;

/*
 * ${Classname}
 * 
 * Version 1.0 
 * 
 * 11.04.2017
 * 
 * Karpikova
 */
public class Main {
    public static void main(String[] args) {
        Set<Book> catalog = (Set<Book>) Proxy.newProxyInstance(
                MySet.class.getClassLoader(),
                MySet.class.getInterfaces(),
                new ProxyCollection());
        System.out.println(catalog.contains(2));

    }
}
