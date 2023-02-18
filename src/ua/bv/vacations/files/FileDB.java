package ua.bv.vacations.files;
//
//import java.io.FileWriter;
////import java.io.FileReader;
//import java.nio.file.Files;
//import java.io.IOException;
//import java.nio.file.Path;
//import java.util.Arrays;
//import java.util.List;
//import java.util.ArrayList;
//
///** Class to work with a database in a file
// * @author Trevertor
// */
//public class FileDB {
//    String fileName = "database.txt";
//
//    public void write(String[] array){
//        try{
//            FileWriter myWriter = new FileWriter(fileName);
//            String whole = "";
//            for(byte i = 0; i< array.length;i++){
//                whole+=array[i] + (i==array.length-1  ? "" : " ");
//            }
//            myWriter.append(whole);
//            myWriter.close();
//        }
//        catch (IOException e){
//            System.out.println("An error occurred (FileDB write).");
//            e.printStackTrace();
//        }
//    }
//
//    public String[][] read(){
//        ArrayList<String[]> arrFull = new ArrayList<String[]>();
//        try{
//            List<String> lines = Files.readAllLines(Path.of(fileName));
//            String[] arr = lines.toArray(new String[lines.size()]);
//            int index = 0;
//            for(String arrItem : arr){
//               // arrFull.add(arrItem.split(" "));
//                arrFull.add(arrItem.split(" "));
//            }
//        }
//        catch (IOException e){
//            arrFull = new String[][];
//            System.out.println("An error occurred (FileDB write).");
//            e.printStackTrace();
//        }
//        return arrFull.toArray(new String[][]);
//    }
//
//}
