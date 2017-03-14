/**
 * Created by Robert on 10/03/17.
 */
import java.io.*;
import java.net.*;

public class InternetContent {
    public static String get(String url) {
        String data = "";
        try {
            URL page = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(page.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null){
                data += inputLine;
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(data);
        return data;
    }
}
