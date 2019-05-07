import Heap.Element;
import Heap.PQ;
import Heap.PQHeap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Encode {


    public static void main(String[] args) throws Exception {


        File filein = new File("/Users/casperhasnsen/Documents/SDU/4thsemester/Algorithms/lab3/projekt3/src/in.txt");
        File fileout = new File("/Users/casperhasnsen/Documents/SDU/4thsemester/Algorithms/lab3/projekt3/src/out.txt");


        FileInputStream fileInputStream = new FileInputStream(filein);
        FileOutputStream fileOutputStream = new FileOutputStream(fileout);

        BitInputStream bitInputStream = new BitInputStream(fileInputStream);

        FrequenceBuilder frequenceBuilder = new FrequenceBuilder();

        int bit;
        while (( bit = bitInputStream.GetDecimal()) != -1) {
            frequenceBuilder.insert(bit);
        }

        PQ inHeap = new PQHeap(256);

        for (int i=0; i<frequenceBuilder.getFrequenceTable().length; i++) {
            inHeap.insert(new Element(i , frequenceBuilder.getFrequenceTable()[i]));
        }

        



    }
}
