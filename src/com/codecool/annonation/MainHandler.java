package com.codecool.annonation;

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
        Reflections reflection = new Reflections("com.codecool.annotation.routes");
        Set<Class<?>> classSets = reflection.getTypesAnnotatedWith(com.codecool.annonation.Route.class);
        for (Class<?> classSet : classSets) {
            Annotation annotation = classSet.getAnnotation(com.codecool.annonation.Route.class);
            if (annotation instanceof com.codecool.annonation.Route && ((com.codecool.annonation.Route)
                    (annotation))
                    .path()
                    .equals(t.getRequestURI()
                    .getPath())) {
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
        String response = "Hey there!";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
