package library.Utils;

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
public class ProxyForTests implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("¬í \u0005sr \u0014library.Modules.Bookse\f\u0006¶ØÕ\u001C\u0002 \u0004I \u0004yearL \u0006authort \u0012Ljava/lang/String;L \u0004isbnq ~ \u0001L \u0005titleq ~ \u0001xp  \u0007Æt \u0001Ft \u00012t \u0003Maysq ~    \u0007Èt \u0001Mt \u00013t \u0004Pair");
        return 0;
    }
}