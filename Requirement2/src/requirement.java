class Threadsrequirement {
    public static void main(String... args) throws InterruptedException {
        BookStock b = new BookStock (10);

        Supplier s = new Supplier(b);
        StoreBranch giza_sb = new StoreBranch(b);
        StoreBranch cairo_sb = new StoreBranch(b);
        StoreBranch dagahley_sb = new StoreBranch(b);

        // threads
        Thread t_supp = new Thread(s);
        Thread t_giza = new Thread(giza_sb);
        t_giza.setName("Giza branch");
        Thread t_cairo = new Thread(cairo_sb);
        t_cairo.setName("Cairo branch");
        Thread t_dagahley = new Thread(dagahley_sb);
        t_dagahley.setName("Dagahley branch");
        //TODO-1: Create 4 threads,
        // 1 for Supplier
        // 3 for StoreBranches and Name them as the following: Giza branch, Cairo branch, Daqahley branch


        //TODO-2: Run the 4 threads


        t_dagahley.start();
        t_giza.start();
        t_cairo.start();
        t_supp.start();

    }
}

class BookStock {
    private int book_count;
    private int max;
    public BookStock  (int max) {
        this.max = max;
    }
    public void produce() {
        book_count++;
    }

    public void consume () {
        book_count--;
    }

    public int getCount () {
        return book_count;
    }
}

//TODO-3: should it implement or extend anything? DONE
class Supplier implements Runnable {
    private BookStock b;

    public Supplier (BookStock b) {
        this.b = b;
    }
    //TODO-4:is a function missing ? DONE

    public void run(){
        doWork();
    }
    public void doWork () {
        while (true) {
            //TODO-5: how to make it stop producing when it reaches max, without adding extra sleeps or busy waiting ?
            synchronized (b){
                while(b.getCount() == 10) {
                    try{
                        b.wait();
                    }catch(Exception e){
                        return ;
                    }
                }
                b.produce();
                b.notifyAll();
            }

            System.out.println (Thread.currentThread().getName() + " provided a book, total " +b.getCount());
            try {
                Thread.sleep (1000);
            } catch (InterruptedException e) {
                System.out.println (Thread.currentThread().getName() + "is awaken");
            }
        }
    }

}

//TODO-6: should it implement or extend anything? DONE
class StoreBranch  implements  Runnable{
    private BookStock b;

    public StoreBranch (BookStock b) {
        this.b = b;
    }

    //TODO-7: is a function missing ? DONE

    public void run(){
        doWork();
    }
    public void doWork () {
        while (true) {
            //TODO-8: how to make it stop consuming when the store is empty, without adding extra sleeps or busy waiting ?
//            System.out.printf("  %d  %s \n",b.getCount(),Thread.currentThread().getName());
            synchronized (this.b){
                while(b.getCount() == 0) {
                    try{
                      b.notify();
                      b.wait();
                    }catch(Exception e){
                        return ;
                    }
                }
                b.consume();
            }

            System.out.println (Thread.currentThread().getName() + " sold a book");
            try {
                Thread.sleep (5000);
            } catch (InterruptedException e) {
                System.out.println (Thread.currentThread().getName() + "is awaken");
            }
        }
    }
}