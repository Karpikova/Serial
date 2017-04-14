package Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/*
 * ${Classname}
 * 
 * Version 1.0 
 * 
 * 11.04.2017
 * 
 * Karpikova
 */
public class ProxyCollection implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        switch (method.getName()){
            case("add"):
                return true;
            case("contains"):
                boolean[] mas = {true, false, true, true};
                Integer index = (Integer) args[0];
                if (index >= mas.length)
                    return false;
                return mas[index];

            case("remove"):
                return true;
        }
        return null;
       // return method.invoke(proxy, args);
    }
}
