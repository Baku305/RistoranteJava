package com.fra.ristorante;

import java.util.Arrays;

public class Test {
    public static String[] m(String s) {
        String[] res = s.split("(?<=\\G..)");
        for (int i = 0; i < res.length; i++) {
            if (res[i].length() < 2 && res[i].length() > 0) {
                res[i] = res[i].concat("_");
            }
        }
        return res;
    }

    public static String CamelCase(String s) {
        String[] res = s.split("-");
        System.out.println(res.length);
        String newString = "";
        if(res.length > 1){
            newString = subStringReplace(res, newString);
        } else if (!res[0].isEmpty()){
            String[] res2 = s.split("_"); 
            System.out.println(res2.length);
            newString = subStringReplace(res2, newString);  
        }
        return newString;
    }

    private static String subStringReplace(String[] SplittedS,String string){
        for (int i = 0; i < SplittedS.length; i++) {
            if (i > 0 || SplittedS[i].substring(0,1) == SplittedS[i].substring(0,1).toUpperCase()) {
                SplittedS[i] = SplittedS[i].replaceFirst(SplittedS[i].substring(0,1), SplittedS[i].substring(0,1).toUpperCase());
            }
            string = string.concat(SplittedS[i]);
        } 
        return string; 
    }

    public static String order(String words){
        String[] splitted = words.split(" ");
        String[] newArray = new String[splitted.length];
        StringBuilder stringBuilder = new StringBuilder("");
        for(String string: splitted){
                char[] splittedSubString = string.toCharArray();
                for(char s : splittedSubString){
                    if(Character.isDigit(s)){
                        int n = Character.getNumericValue(s);
                        newArray[n-1] = string;
                    }
                } 
        }
        int i = 0;
        for(String string : newArray){
            if(i>0){
                stringBuilder.append(" "+string);
            } else if (string != null){
                stringBuilder.append(string);
            }
            i++;
        }

        String newString = stringBuilder.toString();
        return newString;
    }

}
