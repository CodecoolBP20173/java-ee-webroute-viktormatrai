package com.codecool.annonation;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class Main {

        public static void main(String[] args) throws Exception {
            HttpServer server = HttpServer.create(new InetSocketAddress(3333), 0);
            server.createContext("/", new com.codecool.annonation.MainHandler());
            server.start();

        }
    }
