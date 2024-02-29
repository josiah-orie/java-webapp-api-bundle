/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jovine360.web;

/**
 *
 * @author JOSSY
 * This class enables you to trim and add ellipsis to a content to enhance display layout. 
 * Especially contents from which introductions or description can be generated.
 */
public class Ellipsis {
    /**
     * 
     * @param content represents the content to trim.
     * @param size specifies the total number of characters to append the ellipsis
     * @return a String value of the trimmed content with the ellipsis appended at the end.
     */
    public static String content(String content, int size){
        String status = "";
        if(content.length() <= size){
            status = content;
        }else{
            status = content.substring(0, size) + "...";
        }
        
        return status;
    }
}
