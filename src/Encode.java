import Heap.Element;
import Heap.PQ;
import Heap.PQHeap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Encode {


    public static void main(String[] args) throws Exception {


        File filein = new File("C:\\Users\\skakk\\Documents\\Java_Projects\\Algorithms_part3\\src\\in.txt");
        File fileout = new File("C:\\Users\\skakk\\Documents\\Java_Projects\\Algorithms_part3\\src\\out.txt");


        FileInputStream fileInputStream = new FileInputStream(filein);
        FileOutputStream fileOutputStream = new FileOutputStream(fileout);

        BitInputStream bitInputStream = new BitInputStream(fileInputStream);
        BitOutputStream bitOutputStream = new BitOutputStream(fileOutputStream);

        FrequenceBuilder frequenceBuilder = new FrequenceBuilder();

        int bit;
        while (( bit = bitInputStream.GetDecimal()) != -1) {
            frequenceBuilder.insert(bit);
        }

        Huffman huffman = new Huffman(frequenceBuilder.getFrequenceTable());
        Node node = huffman.buildTree();

        String[] codeArr = huffman.orderedTraversal();

        String path = "";

        fileInputStream = new FileInputStream(filein);
        bitInputStream = new BitInputStream(fileInputStream);

        int secondBit;
        while ((secondBit = bitInputStream.GetDecimal()) != -1){
            path += codeArr[secondBit];
        }

        char[] charArr = path.toCharArray();

        for(int i = 0; i < frequenceBuilder.getFrequenceTable().length; i++){
            bitOutputStream.writeInt(frequenceBuilder.getFrequenceTable()[i]);
        }

        for(int i = 0; i < charArr.length; i++){
            String c = "";
            c += charArr[i];
            bitOutputStream.writeBit(Integer.parseInt(c));
        }

        bitOutputStream.writeBit(0);
        bitOutputStream.writeBit(1);
        bitInputStream.close();
        bitOutputStream.close();


    }
}
