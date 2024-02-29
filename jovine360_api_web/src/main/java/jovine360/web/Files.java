/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jovine360.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JOSSY
 * This class enables you to perform basic common io operations from creating a file 
 * to copying to deleting.
 */
public class Files {
    /**
     * This method creates a file in the relative path of the folder location passed as an argument.
     * @param folder_name specify the directory name
     * @param filename specify the file name
     * @return 0 for success otherwise -1
     */
    public static int createRelativeFile(String folder_name, String filename){
        int flag = -1;
        File file = new File(folder_name, filename);
        try {
            file.createNewFile();
            flag = 0;
        } catch (IOException ex) {
            Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        return flag;
    }
    /**
     * This method creates a file with content in the relative path of the folder location passed as an argument.
     * @param folder_name specify the directory name
     * @param filename specify the file name to be created
     * @param msg represent the content to be written to the file upon creation.
     * @return 0 for success otherwise -1
     */
    public static int createRelativeContentFile(String folder_name, String filename, String msg){
        int flag = -1;
        File file = new File(folder_name, filename);
        FileWriter writer = null;
        if(writer == null){
            try {
                file.createNewFile();
                writer = new FileWriter(file, true);
                writer.write(msg);

                writer.flush();
                writer.close();
                flag = 0;
            } catch (IOException ex) {
                Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            writer = null;
            createRelativeContentFile(folder_name, filename, msg);
        }
        
        return flag;
    }
    /**
     * This method creates a file in the absolute path or current location.
     * @param filename  specify the file name to be created
     * @return 0 for success otherwise -1
     */
    public static int createAbsoluteFile(String filename){
        int flag = -1;
        File file = new File(filename);
        try {
            file.createNewFile();
            flag = 0;
        } catch (IOException ex) {
            Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
        }       
       
        return flag;
    }
    /**
     * This method creates a file with content in the absolute path or current location.
     * @param filename specify the file name to be created
     * @param msg represent the content to be written to the file upon creation.
     * @return 0 for success otherwise -1
     */
    public static int createAbsoluteContentFile(String filename, String msg){
        int flag = -1;
        File file = new File(filename);
        FileWriter writer;
        try {
            file.createNewFile();
            writer = new FileWriter(file, true);
            writer.write(msg);
            
            writer.flush();
            writer.close();
            flag = 0;
        } catch (IOException ex) {
            Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
        }       
       
        return flag;
    }
    /**
     * This method creates multiple files in the directory location path provided.
     * @param folder_name represent the directory name to be created.
     * @param files specify the files to be created
     * @return 0 for success otherwise -1
     */
    public static int createRelativeFiles(String folder_name, String[] files){
        int flag = -1;
        for (String file1 : files) {
            File file = new File(folder_name, file1);
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
            }
            flag = 0;
        }
        return flag;
    }
    /**
     * This is a Boolean method used to create a directory
     * @param folder_name represent the directory name to be created.
     * @return  true upon folder creation otherwise false
     */
    public Boolean createFolder(String folder_name){
        Boolean folder = new File(folder_name).mkdir();
        if(folder == true){
            System.out.println("Folder created successfully");
        }
        
        return folder;

    }
    /**
     * This is a Boolean method used to create a directory with sub-directories.
     * @param folder_path  represent the directory and sub-directories name to be created.
     * @return  true upon folder creation otherwise false
     */
    public Boolean createFolderWithSubFolders(String folder_path){
        Boolean folder = new File(folder_path).mkdirs();
        if(folder == true){
            System.out.println("Folder created successfully");
        }
        
        return folder;
    }
    /**
     * This method copies the file specified to the destination location argument
     * @param filename specify the file name to be created
     * @param destination_location specify the directory name
     * @return 0 for success otherwise -1
     */
    public static int copyFile(String filename, String destination_location){
        int status = -1;
        File file = new File(filename);
        FileInputStream fis;
        FileOutputStream fos;
        try {            
            fis = new FileInputStream(filename);            
            fos = new FileOutputStream(destination_location+file.getName());
            int data;
            while ((data = fis.read()) != -1) {
                fos.write(data);
            }
            status = 0;
            fis.close();
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    /**
     * This method copies the files specified to the destination location argument
     * @param source_location specify the source directory name
     * @param destination_location specify the destination directory name
     * @return 0 for success otherwise -1
     */
    public static int copyFiles(String source_location, String destination_location){
        int flag = -1;
        int counter = 0;
        File[] files = new File(source_location).listFiles();
        FileInputStream fis;
        FileOutputStream fos;
        for (File file : files) {
//            int j = i + 1;
            try {
                fis = new FileInputStream(source_location + file.getName());
                fos = new FileOutputStream(destination_location + file.getName());
                int data;
                while((data = fis.read()) != -1){
                    fos.write(data);
                }
                fis.close();
                fos.close();
            }catch (IOException ex) {
                Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
            }
            flag = 0;
            counter++;
        }
        
        return flag;
    }
    /**
     * This method list the names of all files in a directory and add to LinkedHashMap object.
     * @param folder_name specify the directory name
     * @return an object of LinkedHashMap containing the folder contents
     */
    public static LinkedHashMap listFolderContents(String folder_name){
        
        int counter = 0;
        LinkedHashMap link = null;
        if(link == null){
            File[] files = new File(folder_name).listFiles();
            link = new LinkedHashMap();
            String id = "";
            String val = "";
            for (int i = 0; i < files.length; i++) {
                
                id = String.valueOf(i);
                val = files[i].getName();
                link.put(id, val);
                
                counter++;
            }
        }else{
            link = null;
            listFolderContents(folder_name);
        }
        
        return link;
    }
    /**
     * This method is used to delete a file
     * @param filename specify the filename to be deleted.
     * @return true upon deletion otherwise false
     */
    public static boolean deleteFile(String filename){
        Boolean status = false;
        File file = new File(filename);
        status = file.delete();
        return status;
        
    }
    /**
     * This method is used to delete files specified in an array argument.
     * @param filename pecify the filenames to be deleted.
     * @return true upon deletion otherwise false
     */
    public static Boolean deleteFiles(String[] filename){
        Boolean status = false;
        for (String file_name : filename) {
            File file = new File(file_name);
            status = file.delete();
        }
        return status;
        
    }
    /**
     * This is used to delete a directory
     * @param folder specify the directory name
     * @return rue upon deletion otherwise false
     */
    public static Boolean deleteFolder(String folder){
        Boolean status = false;
        File file = new File(folder);
        status = file.delete();
        return status;
    }
   
}
