
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class Encode {


    public static void main(String[] args) throws Exception {


        //TODO: COMMENT IN AT HAND IN!
/*
        FileInputStream fileInputStream = new FileInputStream(args[0]);
        FileOutputStream fileOutputStream = new FileOutputStream(args[1]);
*/
        File filein = new File("/Users/casperhasnsen/Documents/SDU/4thsemester/Algorithms/lab3/projekt3/src/in.txt");
        File fileout = new File("/Users/casperhasnsen/Documents/SDU/4thsemester/Algorithms/lab3/projekt3/src/out.txt");


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

        StringBuilder path = new StringBuilder();
        String bite  = "";

        fileInputStream = new FileInputStream(filein);
        bitInputStream = new BitInputStream(fileInputStream);


        int secondBit;

        while ((secondBit = bitInputStream.GetDecimal()) != -1){
             path.append(codeArr[secondBit]);

        }

        char[] charArr = path.toString().toCharArray();

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
