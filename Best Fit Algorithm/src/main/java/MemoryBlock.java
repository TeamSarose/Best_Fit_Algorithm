class MemoryBlock {
    private int id;
    private int size;

    public MemoryBlock(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public void allocate(int processSize) {
        this.size -= processSize;
    }
}