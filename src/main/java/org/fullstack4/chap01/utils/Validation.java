package org.fullstack4.chap01.utils;

import java.util.Date;

public class Validation {

   public boolean checkEmpty(String param) {
       if (param == null || param.isEmpty()) {
           return false;
       }
       return true;
   }

   public boolean checkLength(String param, int min, int max) {
       if (param == null || param.isEmpty()) {
           return false;
       }
       if (param.length() < min || param.length() > max) {
           return false;
       }
       return true;
   }

   public boolean checkArray(String[] param) {
       int cnt = 0;

       if (param != null) {
           for (int i=0; i<param.length; i++) {
               if (param[i] != null) {
                   cnt++;
               }
           }
           if (cnt > 0) {
               return true;
           } else return false;
       }
       else return false;

   }

}
