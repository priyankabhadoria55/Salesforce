package utils;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;


public class Test {

    public static void main(String[] args) {
       UpdateUtils( ".\\src\\test\\java\\testScript\\Sample.java");

        System.out.println("done");


    }

    public static void RunRandom() {
        UpdateUtils( ".\\src\\test\\java\\testScript\\Sample.java");
        System.out.println("done");
    }
    public static void UpdateUtils(String filePath) {

        ArrayList<String> ExecutedScenerio = new ArrayList<>();
        ArrayList<String> OtherScenerio = new ArrayList<>();
        ArrayList<String> FinalTestToExecute = new ArrayList<>();
        ArrayList<String> ReRunTest = new ArrayList<>();


        String[] Order_Type = new String[0];
        try {
            List<String> allLines = Files.readAllLines(Paths.get(filePath));
            for (String line : allLines) {

                if (line.contains("@Test") && !line.contains("@TestInfo")) {
//                    System.out.println(line);
                    if (line.replace(" ", "").contains("enabled=true")) {
                        String[] arrOfStr = line.replace("@Test(", "").replace(")", "").split(",");
                        for (String desc : arrOfStr) {
                            if (desc.contains("description")) {
                                String[] arrOfStr1 = desc.split("=");
                                //  System.out.println("Executed Scenerios :"+arrOfStr1[1].trim().replace("\"",""));
                                ExecutedScenerio.add(arrOfStr1[1].trim().replace("\"", ""));
                            }

                        }
                    } else {
                        String[] arrOfStr = line.replace("@Test(", "").replace(")", "").split(",");
                        for (String desc : arrOfStr) {
                            if (desc.contains("description")) {
                                String[] arrOfStr1 = desc.split("=");
                                // System.out.println("Other Scenerios :"+arrOfStr1[1].trim().replace("\"",""));
                                OtherScenerio.add(arrOfStr1[1].trim().replace("\"", ""));
                            }

                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int l = 0; l < ExecutedScenerio.size(); l++) {
            for (int J = 0; J < OtherScenerio.size(); J++) {
                String ShortOrderType = OtherScenerio.get(J).substring(0, 6);
                if (ExecutedScenerio.get(l).contains(ShortOrderType)) {
                    if (!FinalTestToExecute.contains(OtherScenerio.get(J).trim())) {
                        FinalTestToExecute.add(OtherScenerio.get(l).trim());
                        break;
                    }
                }


            }
        }
        boolean flag = false;
        for (int l = 0; l < ExecutedScenerio.size(); l++) {
            String ShortOrderType = ExecutedScenerio.get(l).substring(0, 6);

            for (int J = 0; J < OtherScenerio.size(); J++) {
                if (OtherScenerio.get(J).contains(ShortOrderType)) {
                    flag = true;
                    break;
                } else {
                    flag = false;
                }

            }
            if (!flag) {
                if (!ReRunTest.contains(ExecutedScenerio.get(l).trim())) {
                    ReRunTest.add(ExecutedScenerio.get(l).trim());
                }
            }
        }


        System.out.println("Executed Scenerio: "+ ExecutedScenerio);
        System.out.println("Non Executed Scenerio: "+ OtherScenerio);
        System.out.println("Need to Execute Scenerio: "+ FinalTestToExecute);
        System.out.println("ReRun Scenerio: "+ ReRunTest);

        for (int k = 0; k < ExecutedScenerio.size(); k++) {
            modifyFile1(filePath, ExecutedScenerio.get(k),"true", "false");
        }
        for (int k = 0; k < FinalTestToExecute.size(); k++) {
            modifyFile1(filePath, FinalTestToExecute.get(k),"false", "true");
        }
        for (int k = 0; k < ReRunTest.size(); k++) {
            modifyFile1(filePath, ReRunTest.get(k),"false", "true");
        }
    }

    public static void modifyFile(String filePath, String oldString, String newString) {
        File fileToBeModified = new File(filePath);

        String oldContent = "";

        BufferedReader reader = null;

        FileWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            //Reading all the lines of input text file into oldContent

            String line = reader.readLine();

            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();

            }

            //Replacing oldString with newString in the oldContent

            String newContent = oldContent.replaceAll(oldString, newString);

            //Rewriting the input text file with newContent

            writer = new FileWriter(fileToBeModified);

            writer.write(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Closing the resources

                reader.close();

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void modifyFile1(String filePath, String ScenerioName, String oldString, String newString) {
        File fileToBeModified = new File(filePath);

        String oldContent = "";

        BufferedReader reader = null;

        FileWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            //Reading all the lines of input text file into oldContent

            String line = reader.readLine();

            while (line != null) {
                if(line.contains(ScenerioName))
                {
                    oldContent= oldContent + line.replace(oldString, newString) + System.lineSeparator();
                }
                else {
                    oldContent = oldContent + line + System.lineSeparator();
                }
                line = reader.readLine();

            }

            //Replacing oldString with newString in the oldContent
            String newContent = oldContent;

            //Rewriting the input text file with newContent

            writer = new FileWriter(fileToBeModified);

            writer.write(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Closing the resources

                reader.close();

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateproprtywithvalue(String key,String value) throws IOException {
        FileInputStream in = new FileInputStream("config.properties");
        Properties props = new Properties();
        props.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream("config.properties");
        props.setProperty(key, value);
        props.store(out, null);
        out.close();
    }
    public static String readproperty(String key) throws IOException
    { String St=null;

        Properties pr= new Properties();
        InputStream file= new FileInputStream("config.properties");
        pr.load(file);
        St=pr.getProperty(key);
        file.close();
        return St;

    }
}