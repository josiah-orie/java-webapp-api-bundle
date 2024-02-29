/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jovine360.web;

/**
 *
 * @author JOSSY
 * This <code>Formatter</code> class contains methods that takes Integer, String, 
 * and Double arguements and returns a currency formatted String value to two decimal places.
 */
public class Formatter {
    /**
     * This method is used to format an integer value to a currency format.
     * @param val is an integer value argument to be formatted.
     * @return a String value in currency format.
     * <pre>
     *      String formattedString = formatInteger(10000); 
     *      System.out.println(formattedString);
     * 
     *      Result: 10,000.00
     * </pre>
     */
    public static String formatInteger(int val){
        String valu = "";        
        String values = "";        
        valu = String.valueOf(val);
//        System.out.println("Number: "+ valu);
        int length = valu.length();
        if(length < 4){
            valu = val+".00";
        }else{
            int counter = 0;
            int len = length + (length/3);
            String[] value = new String[len];
            for (int i = 0; i < length; i++) {
                value[i] = valu.substring(i, i+1);
            }
            String[] holder = new String[len];
            for (int i = len; i > 0; i--) {
                int j = i-1;
                if(counter == 3){
                    holder[i-1] = ",";
                    counter = -1;
                }else{
                    String letter = value[length-1];
                    holder[i-1] = letter;    
                    
                    length--;
                }                
                counter++;                
            }
            valu = new Formatter().getString(holder);
            if(valu.substring(0, 1).equals(",")){
                valu = valu.substring(1, valu.length());
            }
        }
        
        return valu;
    }
    /**
     *  This method is used to format a String value to a currency format.
     * @param val is an String value argument to be formatted.
     * @return a String value in currency format.
     * <pre>
     *      String formattedString = formatString("10000"); 
     *      System.out.println(formattedString);
     * 
     *      Result: 10,000.00
     * </pre>
     */
    public static String formatString(String val){
        String valu = "";        
        String values = "";        
        int pos = val.indexOf('.');
        if(pos == -1){
            valu = val;
        }else{
            valu = val.substring(0, pos);
        }
        int length = valu.length();
        if(length < 4){
            valu = val+".00";
        }else{
            int counter = 0;
            int len = length + (length/3);
            String[] value = new String[len];
            for (int i = 0; i < length; i++) {
                value[i] = valu.substring(i, i+1);
            }
            String[] holder = new String[len];
            for (int i = len; i > 0; i--) {
                int j = i-1;
                if(counter == 3){
                    holder[i-1] = ",";
                    counter = -1;
                }else{
                    String letter = value[length-1];
                    holder[i-1] = letter;    
                    
                    length--;
                }                
                counter++;                
            }
            valu = new Formatter().getString(holder);
            if(valu.substring(0, 1).equals(",")){
                valu = valu.substring(1, valu.length());
            }
        }
        
        return valu;
    }
    /**
     * This method is used to format a Double value to a currency format.
     * @param val is an Double value argument to be formatted.
     * @return a String value in currency format.
     * <pre>
     *      String formattedString = formatDouble(10000.00); 
     *      System.out.println(formattedString);
     * 
     *      Result: 10,000.00
     * </pre>
     */
    public static String formatDouble(Double val){
        String valu = "";        
        String values = "";        
        values = String.valueOf(val);
        int pos = values.indexOf('.');
        if(pos == -1){
            valu = values;
        }else{
            valu = values.substring(0, pos);
        }
        
        int length = valu.length();
        if(length < 4){
            valu = val+".00";
        }else{
            int counter = 0;
            int len = length + (length/3);
            String[] value = new String[len];
            for (int i = 0; i < length; i++) {
                value[i] = valu.substring(i, i+1);
            }
            String[] holder = new String[len];
            for (int i = len; i > 0; i--) {
                int j = i-1;
                if(counter == 3){
                    holder[i-1] = ",";
                    counter = -1;
                }else{
                    String letter = value[length-1];
                    holder[i-1] = letter;    
                    
                    length--;
                }                
                counter++;                
            }
            valu = new Formatter().getString(holder);
            if(valu.substring(0, 1).equals(",")){
                valu = valu.substring(1, valu.length());
            }
        }
        
        return valu;
    }
    /**
     * This method is used to concat all parts and append the fractional value 
     * and is implemented by the formatter methods.
     * @param value invoked by all methods to return the currency format
     * @return 
     */
    private String getString(String[] value){
        String res = "";
        for (String value1 : value) {
            res = res + value1;
        }
        
        return res+".00";
    }
}
