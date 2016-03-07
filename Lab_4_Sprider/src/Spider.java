import java.io.*;
import java.util.*;


/**
 * Created by Maria on 7/03/2016.
 */

public class Spider{
    public static void main(String args[]) throws IOException, NullPointerException{

        myFile mf = new myFile();						//makes an instance of myFile
    }
}

class myFile{

    File f;
    File ff[];
    String s[];
    String directories[];							//string to store list of files
    FileInputStream fis;
    String virusString;
    String ouputStr = "";
    //File virus = new File("virus.txt");
    static HashMap hm = new HashMap();
    FileInputStream fis2;
    FileOutputStream fos2;
    static byte[] b2;

    myFile() throws IOException, NullPointerException{			//constructor

        f = new File("F:\\");							//makes a file
        if(f.exists())							//check whether the file exists
        {
            if(f.isDirectory())							//checks if it is a directory
            {
                getFileName("F:\\");					//if the file is a directory then send it to the method getFileName to list the files
            }
        }

        System.out.println("Enter the key");
        Scanner sc = new Scanner(System.in);
        String keyInput = sc.nextLine().toString();



        try {
            String value = hm.get(keyInput).toString();
            System.out.println(value);
        }
        catch (Exception e)
        {
            System.out.println("No result Found !!!");
        }

    }

    public void getFileName(String s) throws IOException, NullPointerException
    {
        File[] fileArray ;
        File newFile = new File(s);						//makes
        fileArray = newFile.listFiles();				//makes a file Array to store files in a directory to find out the required one

        if(fileArray==null)
            return;							            //if the directory is empty then it returns

        for(int i=0 ; i<fileArray.length ; i++)
        {
            if(fileArray[i].isDirectory())				//checks if it if a directory
            {
                getFileName(fileArray[i].getAbsolutePath());	                    //if it is a directory then it again send the name to same method for recursive searching
            }
            ouputStr +=fileArray[i].getAbsolutePath();


            //System.out.print(fileArray[i].getAbsolutePath());
            if (fileArray[i].getPath().endsWith(".txt"))
            {
                //System.out.println("\t **************************");
                String[] filePaths = fileArray[i].getPath().split("\\\\");//split(File.pathSeparator);

                String fileName = filePaths[filePaths.length-1];

                //System.out.println("--"+fileName+"--");

                String[] fileNameParts = fileName.split(" ");
                // Put elements to the map

                for (String tempName:fileNameParts) {

                    tempName  = tempName.replace(".txt", "");
                    //System.out.println(tempName.replace(".txt", ""));
                    String val = "";

                    try {
                        val = hm.get(tempName).toString();
                    }
                    catch (Exception e)
                    {
                        //System.out.println(e.getMessage());
                    }
                    hm.put(tempName, val+ "\n * " +fileArray[i].getPath());

                }

                readFile(fileArray[i].getPath());
            }
        }
    }//


    public void readFile(String name)
    {

        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader(name));
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                String[] fileNameParts = line.split(" ");
                for (String tempVal: fileNameParts) {
                    String val = "";
                    try {
                        val = hm.get(tempVal).toString();
                    }
                    catch (Exception e)
                    {
                    }
                    if (!val.toUpperCase().contains(name.toUpperCase()))
                    {
                        hm.put(tempVal, val+ "\n * " +name);
                    }
                }
            }

        } catch (IOException e) {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}//end class


