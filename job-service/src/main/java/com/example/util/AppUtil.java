package com.example.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.stereotype.Component;

@Component // singleton design pattern !
public class AppUtil {

    private static String HOSTNAME = null;

    public static String getHostName() throws Exception {
        if (HOSTNAME == null) {
            try {
                HOSTNAME = InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException e) {
                throw new Exception("error");
            }
        }
        return HOSTNAME;
    }

}
