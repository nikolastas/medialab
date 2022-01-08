package recorder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.max;

public class write {
    public static void setVariable(String filename, int lineNumber, String data) throws IOException {
        Path path = Paths.get(filename);
//        ArrayList<String> lines = new ArrayList<>();
        List<String> lines = Files.readAllLines(path);
        lines.set(lineNumber, data);
        Files.write(path, lines);
    }
    public void createFileLine(String filename, int line, String data) throws IOException{
        Path path = Paths.get(filename);
        List<String> lines = Files.readAllLines(path);
        lines.add(line,data );
        Files.write(path, lines);
    }
    public void createAndWriteFile(String wo, String wi, Integer t){
        String filename = "./Logs/log.txt";
        File file= new File(filename);
        try{


            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter(file);
            Scanner myReader = new Scanner(file);
            int line=0;
            // int line -> mod0
            // str word -> mod0+1
            // str winner -> mod5+2
            // int how-many-tries -> mod5 +3
            Path path = Paths.get(filename);
            List<String> lines = Files.readAllLines(path);
            if(lines.size()<=4*5-1){
                line=lines.size();
            }
            else {
                for (int i = 0; i < 5; i++) {

                    try {
                        line = max(line, Integer.valueOf(lines.get(i * 4)));
                    } catch (IndexOutOfBoundsException e) {
                        continue;
                    }
                }
            }
            try{
                if(file.length()>=line%5+3) {
                    setVariable(filename, line % 5, String.valueOf(line + 1));
                    setVariable(filename, line % 5 + 1, wo);
                    setVariable(filename, line % 5 + 2, wi);
                    setVariable(filename, line % 5 + 3, String.valueOf(t));
                }
                else{
                    System.out.println("the line i will write is: "+line);
                    createFileLine(filename, line%5, String.valueOf(line+1));
                    createFileLine(filename, line%5+1, wo);
                    createFileLine(filename, line%5+2, wi);
                    createFileLine(filename, line%5+3, String.valueOf(t));

//                    myWriter.write(line + 1 +"\n");
//                    myWriter.write(wo +"\n");
//                    myWriter.write(wi +"\n");
//                    myWriter.write(t +"\n");
                }


            }catch (Exception e){
                System.out.println(e);
                e.printStackTrace();
            }



            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
