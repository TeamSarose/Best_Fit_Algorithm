public class ProcessFactory {
    public static Process createProcess(int id, int size) {
        return new Process(id, size);
    }
}