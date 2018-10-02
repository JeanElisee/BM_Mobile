package com.example.layout_module.beans;

public final class LinkToServer {
    public static final class LinkDetails {
//        ======< ARTHUR HOME >=========
//       private static final String IP_ADDRESS = "192.168.0.14";

//      ======< My HOME >==========
       private static final String IP_ADDRESS = "192.168.43.44"; //Raspberry Pi

//        ======< COLLEGE >=========
//      private static final String IP_ADDRESS = "10.0.11.47";

        private static final String PORT = "9112";

        public static final String SERVER_ADDRESS = "http://" + IP_ADDRESS + ":" + PORT + "/";
    }
}
