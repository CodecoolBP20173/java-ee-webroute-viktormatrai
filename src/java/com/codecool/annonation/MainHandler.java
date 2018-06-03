package java.com.codecool.annonation;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import org.reflections.Reflections;

public class MainHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange t) throws IOException {
        Reflections reflection = new Reflections("java.com.codecool.annotation.routes");
        Set<Class<?>> classSets = reflection.getTypesAnnotatedWith(Route.class);
        for (Class<?> classSet : classSets) {
            Annotation annotation = classSet.getAnnotation(Route.class);
            if (annotation instanceof Route && ((Route) (annotation)).path().equals(t.getRequestURI().getPath())) {
                Method[] methods = classSet.getMethods();
                for (Method method : methods) {
                    try {
                        method.invoke(classSet.newInstance(), t);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        String response = "This is the response";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
