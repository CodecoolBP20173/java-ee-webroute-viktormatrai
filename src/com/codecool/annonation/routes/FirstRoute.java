package com.codecool.annonation.routes;

import com.sun.net.httpserver.HttpExchange;

import com.codecool.annonation.Route;
import java.io.IOException;
import java.io.OutputStream;

@Route(path = "/")
public class FirstRoute {

    public void onTest(HttpExchange t) throws IOException {
        String response = "Hey there";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }
}
