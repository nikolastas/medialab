package recorder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Math.max;

public class write {
    public static void setVariable(File file, int lineNumber, String data) throws IOException {
        Path path = Path.of(file.getPath());
//        ArrayList<String> lines = new ArrayList<>();
        List<String> lines = Files.readAllLines(path);
        lines.set(lineNumber, data);
        Files.write(path, lines);
    }
    public void createFileLine(File file, int line, String data) throws IOException{
        Path path = Path.of(file.getPath());
        List<String> lines = Files.readAllLines(path);
//        System.out.println(lines);
//        System.out.println("i should add "+line+" with data= "+data);
        lines.add(line,data);
//        System.out.println(lines);
        Files.write(path, lines);
    }
    public void createAndWriteFile(String wo, String wi, Integer t){
        String filename = "./Logs/log.txt";
        File file= new File(filename);

        try {

//            Scanner myReader = new Scanner(file);
            int line=0;
            // int line -> mod0
            // str word -> mod0+1
            // str winner -> mod5+2
            // int how-many-tries -> mod5 +3
            Path path = Paths.get(filename);
//            URI uri = Objects.requireNonNull(this.getClass().getResource(filename)).toURI();
            List<String> lines = Files.readAllLines(path);
            System.out.println("path= "+String.valueOf(path.toString()));
            long lineCount;
            try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
                lineCount = stream.count();
            }
            System.out.println("file has length= "+lineCount);
            System.out.println("lines= ");
            System.out.println(lines);
            if(lineCount<=4*5-1){
                line= (int) lineCount;
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
//            System.out.println("i should write to "+(line%5));
//            System.out.println("or [] i should write to "+ line);
            try{
                if(lineCount>=20) {
                    System.out.println("trying to modify file...");
                    setVariable(file, (line % 5)*4, String.valueOf(line + 1));
                    setVariable(file, (line % 5)*4+1 , wo);
                    setVariable(file, (line % 5)*4 + 2, wi);
                    setVariable(file, (line % 5)*4+ 3, String.valueOf(t));
                    System.out.println("file modified succefully");
                }
                else{
                    System.out.println("the line i will write is: "+line);
                    createFileLine(file, line, String.valueOf(line+1));
                    createFileLine(file, line+1, wo);
                    createFileLine(file, line+2, wi);
                    createFileLine(file, line+3, String.valueOf(t));

//                    myWriter.write(line + 1 +"\n");
//                    myWriter.write(wo +"\n");
//                    myWriter.write(wi +"\n");
//                    myWriter.write(t +"\n");
                }


            }catch (Exception e){
                System.out.println(e);
                e.printStackTrace();
            }



//            myWriter.close();
            System.out.println("Successfully change file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
