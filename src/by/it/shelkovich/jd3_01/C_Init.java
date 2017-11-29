package by.it.shelkovich.jd3_01;

import java.io.*;

public class C_Init {
    private File file;
    public C_Init(String filepath){
        this.file = new File(filepath);
    }

    String[] parse(){
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while((line = reader.readLine()) != null){
                if (line.startsWith("--")) continue;
                if (line.startsWith("/*")) continue;
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] queries = sb.toString().split(";");
        return queries;
    }


    void run(){
        String[] queries = parse();
        for(String query: queries){
            DataBase.exeChangeQuery(query);
        }
    }


}
