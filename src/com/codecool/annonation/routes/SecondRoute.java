package com.codecool.annonation.routes;

import com.codecool.annonation.Route;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

@Route(path = "/2nd")
public class SecondRoute {

    public void onTest(HttpExchange t) throws IOException {
        String response = "What's up...";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
