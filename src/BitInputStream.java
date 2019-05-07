import java.io.IOException;
import java.io.InputStream;

public class BitInputStream {

    private InputStream input;

    private int nextBits;


    private int numBitsRemaining;

    private boolean isEndOfStream;

    // Creates a bit input stream based on the given byte input stream.
    public BitInputStream(InputStream in) {
        if (in == null)
            throw new NullPointerException("No input stream given");
        input = in;
        numBitsRemaining = 0;
        isEndOfStream = false;
    }

    public int readBit() throws IOException {
        if (isEndOfStream)
            return -1;
        if (numBitsRemaining == 0) {
            nextBits = input.read();
            if (nextBits == -1) {
                isEndOfStream = true;
                return -1;
            }

            numBitsRemaining = 8;
        }
        numBitsRemaining--;
        return (nextBits >>> numBitsRemaining) & 1;
    }

    public int get32BitDecimal() throws IOException{
        int bit;
        int i = 0;
        String hexbyte = "";
        int decimal = 0;
        while ((bit = readBit() ) != -1) {
            hexbyte += Integer.toString(bit);
            i++;
            if (i == 32) {
                decimal = Integer.parseInt(hexbyte , 2);
                return decimal;
            }
        }
        return -1;
    }


    public int GetDecimal() throws IOException {
        int bit;
        int i = 0;
        String hexbyte = "";
        int decimal = 0;
        while ((bit = readBit() ) != -1) {
            hexbyte += Integer.toString(bit);
            i++;
            if (i == 8) {
                decimal = Integer.parseInt(hexbyte , 2);
                return decimal;
            }

        }
        return -1;
    }


    // Reads an int from the stream. Throws IOException if 32 bits are
    // not available.
    public int readInt() throws IOException {
        int output = 0;
        int nextBit;
        int bitsAdded = 0;
        while(bitsAdded < 32){
            nextBit = readBit();
            if (nextBit == -1)
                throw new IOException("Not enough bits while trying to read int");
            output = output << 1 | nextBit;
            bitsAdded++;
        }
        return output;
    }

    // Closes this stream and the underlying InputStream.
    public void close() throws IOException {
        input.close();
    }

}
