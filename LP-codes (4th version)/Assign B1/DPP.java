import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class DPP {
    // The number of philosophers
    private static final int NUM_PHILOSOPHERS = 5;

    public static void main(String[] args) {
        // Model each chopstick with a lock
        Semaphore[] chopsticks = new Semaphore[NUM_PHILOSOPHERS];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            chopsticks[i] = new Semaphore(1);
        }
        // Create the philosophers and start each running in its own thread.
        Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
        System.out.println("Enter how many no of times you want to repeat the process:");
        int x = sc.nextInt();
        while (x-- > 0) {
            for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
                try {
                    philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % NUM_PHILOSOPHERS]);
                    Thread t = new Thread(philosophers[i]);
                    t.start();
                    t.join();
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                }
            }
        }

    }
}

class Philosopher implements Runnable {
    // Used to vary how long a philosopher thinks before eating and how long the
    // philosopher eats
    private Random numGenerator = new Random();
    // The philosopher's unique id
    private int id;
    // The chopsticks this philosopher may use
    private Semaphore leftChopstick;
    private Semaphore rightChopstick;

    public Philosopher(int id, Semaphore leftChopstick, Semaphore rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    /**
     * Repeatedly think, pick up chopsticks, eat and put down chopsticks
     */
    public void run() {
        try {
            int i = 0;
            while (i < 1) {
                think();
                pickUpLeftChopstick();
                pickUpRightChopstick();
                eatanddrop();
                i++;

            }
        } catch (Exception e) {
            System.out.println("Philosopher " + (id + 1) + " was interrupted.");
        }
    }

    /**
     * Lets a random amount of time pass to model thinking.
     *
     * @throws InterruptedException
     */
    private synchronized void think() throws InterruptedException {
        System.out.println("Philosopher " + (id + 1) + " is thinking.");
        System.out.flush();
        Thread.sleep(numGenerator.nextInt(10));
    }

    /**
     * Locks the left chopstick to signify that this philosopher is holding it
     */
    private synchronized void pickUpLeftChopstick() throws InterruptedException {
        if (leftChopstick.availablePermits() == 1) {
            System.out.println("Philosopher " + (id + 1) + " is WAITING for left  chopstick");
        }
        System.out.flush();
        leftChopstick.acquire();
        System.out.println("Philosopher " + (id + 1) + " picks  left  chopstick.");
        System.out.flush();
        // }

    }

    /**
     * Locks the right chopstick to signify that this philosopher is holding it
     */
    private synchronized void pickUpRightChopstick() throws InterruptedException {
        if (rightChopstick.availablePermits() == 1) {
            System.out.println("Philosopher " + (id + 1) + " is WAITING for right chopstick");
        }
        System.out.flush();
        rightChopstick.acquire();
        System.out.println("Philosopher " + (id + 1) + " picks  right   chopstick.");
        System.out.flush();

    }

    /**
     * Lets a random amount of time pass to model eating.
     *
     * @throws InterruptedException
     */
    /**
     * Releases the locks on both chopsticks to model putting them down so the
     * other philosophers can use them.
     */
    private synchronized void eatanddrop() throws InterruptedException {
        System.out.println("Philosopher " + (id + 1) + " is eating.");

        System.out.flush();
        Thread.sleep(numGenerator.nextInt(10));
        System.out.println("Philosopher " + (id + 1) + " drops both Chopsticks.");
        leftChopstick.release();
        rightChopstick.release();
    }

}
