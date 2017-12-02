package ru.atom.matchmaker.thread;

/**
 * Created by sergey on 3/14/17.
 */
//public class ConnectionProducer implements Runnable {
//    private static final Logger log = LogManager.getLogger(ConnectionProducer.class);
//    private static final String[] names = {"John", "Paul", "George", "Someone else"};
//
//    private static AtomicLong id = new AtomicLong();
//
//    @Override
//    public void run() {
//        while (!Thread.currentThread().isInterrupted()) {
//            long newId = id.getAndIncrement();
//
//            ConnectionQueue.getInstance().offer(new Connection(newId, names[(int) (newId % names.length)]));
//            log.info("Connection {} added.", newId);
//            try {
//                Thread.sleep(1_000);
//            } catch (InterruptedException e) {
//                log.info("Interrupted");
//            }
//        }
//    }
//}
