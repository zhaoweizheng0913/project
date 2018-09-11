package speedchina.addresselement.tools;

import java.util.UUID;


public class Tools {

    public static String getUuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
