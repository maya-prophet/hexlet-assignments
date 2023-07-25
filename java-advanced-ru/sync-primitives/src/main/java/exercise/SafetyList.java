package exercise;

class SafetyList {
    // BEGIN
    private int[] array;
    private int curIndex = 0;

    public SafetyList() {
        this(10);
    }

    public SafetyList(int size) {
        this.array = new int[size];
    }

    public synchronized void add(int num) {
        if (curIndex >= array.length) {
            int[] newArray = new int[array.length * 3 / 2 + 1];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        array[curIndex++] = num;
    }

    public int get(int index) {
        return array[index];
    }

    public int getSize() {
        return curIndex;
    }
    // END
}
