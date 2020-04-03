package cn.net.nigel;

import cn.net.nigel.common.Config;

import java.io.*;
import java.time.Instant;

public class App {

    public static void main( String[] args ) throws IOException {

        Config config = new Config(".config.properties");
//        System.out.println(config.getValue("upyun-name"));
        System.out.println( "Hello World!" );

        System.out.println(Math.floorDiv(0, 100000));
        System.out.println(Instant.ofEpochSecond(1500000000));
    }
}
