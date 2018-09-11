package speedchina.addresselement.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.UUID;

/**
 * Created by ym on 2018/1/15.
 * Function :
 * Params :
 * Return :
 */
public class Tools {

    public static void writeLog(String txt,String title){
        FileOutputStream fs = null;
        PrintStream p = null;
        try {
            fs = new FileOutputStream(new File("../bzdzbuilder/log/bzdzbuilder_log"+title+".txt"));
            p = new PrintStream(fs);
            p.println(txt);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            p.close();

        }
    }

    public static void writeErrorLog(String txt,String title){
        FileOutputStream fs = null;
        PrintStream p = null;
        try {
            fs = new FileOutputStream(new File("../bzdzbuilder/log/bzdzbuilder_errorlog"+title+".txt"));
            p = new PrintStream(fs);
            p.println(txt);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            p.close();

        }
    }


    public static String getUuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
