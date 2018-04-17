/*
 * @(#)ConcurrencyHashMapTest.java 1.0 Sep 29, 2017
 */
package concurrency;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * <code>ConcurrencyHashMapTest</code> class is  Testing Hashtable, SynchronizedHashMap and ConcurrentHashMap
 * <p>
 * <p>
 * <pre>
 * <strong>History</strong>    Name              Date            Description
 * <strong>History</strong>    --------------------------------------------------------------------
 * <strong>History</strong>   Pratyush Giri    9/29/17
 * </pre>
 *
 * @author Pratyush Giri
 * @since Sep 29, 2017
 */



public class ConcurrencyHashMapTest {

    public final static int NUM_OF_THREADS = 5;
    public final static int HOW_MANY_TIMES = 5;

    public static Map<String, Integer> hashTable = null;
    public static Map<String, Integer> synchronizedHashMap = null;
    public static Map<String, Integer> concurrentHashMap = null;

    public static void main(String[] args) throws InterruptedException {

        // Test with Hashtable Object
        hashTable = new Hashtable<String, Integer>();
        test(hashTable);

        // Test with synchronizedMap Object
        synchronizedHashMap = Collections.synchronizedMap(new HashMap<String, Integer>());
        test(synchronizedHashMap);

        // Test with ConcurrentHashMap Object
        concurrentHashMap = new ConcurrentHashMap<String, Integer>();
        test(concurrentHashMap);

    }

    public static void test(final Map<String, Integer> testedMap) throws InterruptedException {

        System.out.println("Test started for: " + testedMap.getClass());
        long averageTime = 0;
        for (int i = 0; i < HOW_MANY_TIMES; i++) {
            long startTime = System.nanoTime();
            ExecutorService threadServer = Executors.newFixedThreadPool(NUM_OF_THREADS);

            for (int j = 0; j < NUM_OF_THREADS; j++) {
                threadServer.execute(new Runnable() {
                    @SuppressWarnings("unused")
                    @Override
                    public void run() {

                        for (int i = 0; i < 500000; i++) {
                            Integer crunchifyRandomNumber = (int) Math.ceil(Math.random() * 550000);

                            // Retrieve value. We are not using it anywhere
                            Integer crunchifyValue = testedMap.get(String.valueOf(crunchifyRandomNumber));

                            // Put value
                            testedMap.put(String.valueOf(crunchifyRandomNumber), crunchifyRandomNumber);
                        }
                    }
                });
            }

            // Make sure executor stops
            threadServer.shutdown();

            // Blocks until all tasks have completed execution after a shutdown request
            threadServer.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

            long entTime = System.nanoTime();
            long totalTime = (entTime - startTime) / 1000000L;
            averageTime += totalTime;
            System.out.println("2500K entried added/retrieved in " + totalTime + " ms");
        }
        System.out.println("For " + testedMap.getClass() + " the average time is " + averageTime / 5 + " ms\n");
    }
}



