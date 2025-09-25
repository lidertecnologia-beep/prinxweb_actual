package com.smarttmt.utilities;

import lombok.extern.slf4j.Slf4j;

import java.util.Base64;

public class DecodeAuthoritzationBasic {

    public static String[] decoderAuthorization(String authString) {
        if(authString == null){
            return  null;
        }
        String[] authParts = authString.split("\\s+");
        String authInfo = authParts[1];
        String decodedAuth = new String(Base64.getDecoder().decode(authInfo));
        String[] credenciales = decodedAuth.split(":");
        return credenciales;
    }

}
