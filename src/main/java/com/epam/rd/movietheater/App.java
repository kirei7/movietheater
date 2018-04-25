package com.epam.rd.movietheater;

import com.epam.rd.movietheater.server.TomcatServer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        TomcatServer server = new TomcatServer();
        server.run();
    }
}
