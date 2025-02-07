public final class semaphoreGeneral extends semaphore {
    private final int maxThreads;

    public semaphoreGeneral(int maxThreads) {
        super(maxThreads);
        this.maxThreads = maxThreads;
    }

    public final synchronized void syncSignal() {
        System.out.println("Je sors de section critique \n");
        super.syncSignal();
        if (valeur > maxThreads) {
            valeur = maxThreads;
        }
    }

    public final synchronized void syncWait() {
        super.syncWait();
        System.out.println("J’entre en section critique \n");
    }
}
