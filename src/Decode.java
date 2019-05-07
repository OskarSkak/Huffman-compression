import java.io.*;

public class Decode {
    public static void main(String[] args) throws IOException {

        File filein = new File("C:\\Users\\skakk\\Documents\\Java_Projects\\Algorithms_part3\\src\\out.txt");
        File decoded = new File("C:\\Users\\skakk\\Documents\\Java_Projects\\Algorithms_part3\\src\\decoded.txt");

        FileInputStream fileInputStream = new FileInputStream(filein);
        FileOutputStream fileOutputStream = new FileOutputStream(decoded);

        BitInputStream bitInputStream = new BitInputStream(fileInputStream);

        FrequenceBuilder frequenceBuilder = new FrequenceBuilder();

        /*int i = 0;
        int bit = 0;
        String path = "";
        while ( bit != -1)  {
            if(i < 256) {
                bit = bitInputStream.readInt();
                frequenceBuilder.insert(bit);
                i++;
            } else{
                bit = bitInputStream.readBit();
                path += bit;
            }
        }

        HERE__--------------------------

        int bit = 0;
        int i = 0;
        while(( bit = bitInputStream.get32BitDecimal()) != -1){
            frequenceBuilder.insert(bit);
        }
*/

        String path = "";

        char[] charArr = path.toCharArray();
        Huffman huffman = new Huffman(frequenceBuilder.getFrequenceTable());
        Node root = huffman.buildTree();
        Node toWrite = root;

        for(int j = 0; j < charArr.length; j++){
            if(toWrite.getLeft() != null && toWrite.getRight() != null) {
                if (charArr[j] == '0') {
                    toWrite = toWrite.getLeft();
                }else if (charArr[j] == '1'){
                    toWrite = toWrite.getRight();
                }
            }else{
                fileOutputStream.write(toWrite.getSpot());
                toWrite = root;
            }
        }

        fileInputStream.close();
    }
}
