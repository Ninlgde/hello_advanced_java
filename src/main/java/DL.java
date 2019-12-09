public class DL {
    private static DL ourInstance = new DL();

    public static DL getInstance() {
        return ourInstance;
    }

    private DL() {
    }
}
