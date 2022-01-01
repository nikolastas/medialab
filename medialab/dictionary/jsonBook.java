package dictionary;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import javax.json.*;


class jsonBook {
   public String ID;
   String allBookDescription;
   String[] bookDescription;
   public void jsonresult(String bookID) {
      ID=bookID;
      String stringUrl = "https://openlibrary.org/works/" + bookID.toString() + ".json";
      URL url = null;
      InputStream is = null;
      try {
         url = new URL(stringUrl);
         is = url.openStream();
      } catch (Exception e) {
         System.out.println(e);
      }
      System.out.println(stringUrl);
      JsonReader rdr = Json.createReader(is);
      JsonObject obj = rdr.readObject();
      String results = null;
      try{
          results = obj.getString("description");
      }
      catch (Exception e){
         JsonObject obj2 = obj.getJsonObject("description");
          results = obj2.getString("value");
      }
      allBookDescription = results;
      bookDescription = results.split("\s+");

   }

}
