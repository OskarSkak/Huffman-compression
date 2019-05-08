import java.io.*;

public class Decode {
    public static void main(String[] args) throws IOException {

        //TODO: COMMENT IN AT HAND IN!
/*
        FileInputStream fileInputStream = new FileInputStream(args[0]);
        FileOutputStream fileOutputStream = new FileOutputStream(args[1]);
*/

        File filein = new File("/home/setero/Documents/Algorithms_part3/src/out.txt");
        File decoded = new File("/home/setero/Documents/Algorithms_part3/src/decoded.txt");

        FileInputStream fileInputStream = new FileInputStream(filein);
        FileOutputStream fileOutputStream = new FileOutputStream(decoded);

        BitInputStream bitInputStream = new BitInputStream(fileInputStream);

        FrequenceBuilder frequenceBuilder = new FrequenceBuilder();

        int i = 0;
        int bit = 0;
        String path = "";
        while ( bit != -1)  {
            if(i < 256) {
                bit = bitInputStream.readInt();
                frequenceBuilder.getFrequenceTable()[i] = bit;
                i++;
            } else{
                bit = bitInputStream.readBit();
                path += bit;
            }
        }

        char[] charArr = path.toCharArray();
        Huffman huffman = new Huffman(frequenceBuilder.getFrequenceTable());
        Node root = huffman.buildTree();
        Node toWrite = root;

        for(int j = 0; j < charArr.length;){
            if(toWrite.getLeft() != null && toWrite.getRight() != null) {
                if (charArr[j] == '0') {
                    toWrite = toWrite.getLeft();
                }else if (charArr[j] == '1'){
                    toWrite = toWrite.getRight();
                }
                j++;
            }else{
                fileOutputStream.write(toWrite.getSpot());
                toWrite = root;
            }
        }

        fileInputStream.close();
    }
}
