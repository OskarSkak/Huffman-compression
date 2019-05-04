import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Encode {


    public static void main(String[] args) throws Exception {


        File filein = new File("/Users/casperhasnsen/Documents/SDU/4thsemester/Algorithms/lab3/projekt3/src/in.txt");
        File fileout = new File("/Users/casperhasnsen/Documents/SDU/4thsemester/Algorithms/lab3/projekt3/src/out.txt");


        FileInputStream fileInputStream = new FileInputStream(filein);
        FileOutputStream fileOutputStream = new FileOutputStream(fileout);

        


    }
}
