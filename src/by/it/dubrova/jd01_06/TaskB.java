package by.it.dubrova.jd01_06;

//vowel

import java.util.regex.*;

//проверяет первую и последнюю букву на гласность

public class TaskB {
    public static void main(String[] args) {
        //boolean test1 = isVowels("Aня");

    //    String[] txt = Data.lukomor.split(" ");
    //   for (String s : txt)
    //        System.out.println(s);

        StringBuilder text=new StringBuilder(Data.lukomor);

        Pattern p = Pattern.compile("[а-яА-ЯёЁ]+");
        Matcher m = p.matcher(text);

        while (m.find()){
            if(isVowels(m.group()))
                System.out.println(m.group());
        }

    }

    public static boolean isVowels(String word){
        char vowel[] = {'а', 'у', 'о', 'ы', 'и', 'э', 'я', 'ю', 'ё', 'е', 'А', 'У', 'О', 'Ы', 'И', 'Э', 'Я', 'Ю', 'Ё', 'Е'};
        char first = word.charAt(0);
        char last = word.charAt(word.length()-1);

        for (char c : vowel){
            if (first == c)
                for (char d : vowel)
                    if (last == d)
                        return true;
        }

       // System.out.println(last);
        return false;
    }

}
