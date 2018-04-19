package com.epam.rd.movietheater;

import com.epam.rd.movietheater.server.JettyServer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        JettyServer server = new JettyServer();
        server.run();
    }
}
