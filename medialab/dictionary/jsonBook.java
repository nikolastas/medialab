package dictionary;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.InputStream;
import java.net.URL;


class jsonBook {
   public String ID;
   String allBookDescription;
   String[] bookDescription;
   public void jsonresult(String bookID) throws NullPointerException{
      ID=bookID;

      String stringUrl = "https://openlibrary.org/works/" + bookID + ".json";
      URL url;
      InputStream is = null;
      try {
         url = new URL(stringUrl);
         is = url.openStream();
      } catch (Exception e) {
         e.printStackTrace();
      }
      System.out.println(stringUrl);
      JsonReader rdr = Json.createReader(is);
      JsonObject obj = rdr.readObject();
      String results ;
      try{
            try {
               results = obj.getString("description");
            }
            catch (ClassCastException E){
               JsonObject obj2 = obj.getJsonObject("description");
               results = obj2.getString("value");
            }
            if(results.length()==0){
               throw  new NullPointerException("no description found");
            }
         allBookDescription = results;
         bookDescription = results.split("\s+");
      }
      catch (Exception e){
         System.out.println("no object found");
      }


   }

}
