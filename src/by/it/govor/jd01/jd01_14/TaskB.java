package by.it.govor.jd01.jd01_14;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;


class TaskB {
    static void taskB() {
       File file = new File(System.getProperty("user.dir")+"/src/by/it/govor/jd01/jd01_14/text2.txt");
       int b;
       int space=0;
       int point=0;

       try(FileReader reader = new FileReader(file)){
           while(reader.read()!=-1){
               b=reader.read();
               if(b==','){point++;}
               if(b==' '){space++;}
           }
       }catch (IOException e){
       System.out.println("Ошибка чтения файла: "+e);

   }
        System.out.println("Кол-во слов ="+space);
        System.out.println("Кол-во знаков ',' ="+point);

   }
}
