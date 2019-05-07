
public class FrequenceBuilder {


    private int[] frequenceTable;

    public FrequenceBuilder() {
        this.frequenceTable = new int[256];
    }


    public void insert(int value) {
        this.frequenceTable[value]++;
    }

    public int[] getFrequenceTable( ) {
        return this.frequenceTable;
    }

}
