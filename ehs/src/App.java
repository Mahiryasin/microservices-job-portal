import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class App {
    private static AtomicBoolean flag1=new AtomicBoolean(false);

    private static synchronized void  turn(){
        flag1.set(true);
    }

    public static void main(String[] args) throws Exception {
        CountDownLatch latch=new CountDownLatch(5);
        Map<String,String>consurrentmap=new ConcurrentHashMap<>();
        consurrentmap.put("1", "1");
    


        Runnable executorThread=()->{
            int i=0;
            i++;
            System.out.println("islem tamamlandır: "+i);
            latch.countDown();
        };
        ExecutorService executorService=Executors.newSingleThreadExecutor();
        executorService.execute(executorThread);
        executorService.shutdown();

        ExecutorService executorService2=Executors.newFixedThreadPool(3);
        IntStream.range(1, 5).forEachOrdered((value)->{
            executorService2.submit(executorThread);
        
        });
        executorService2.shutdown();

        Thread thread=new Thread(()->{
                    int i=0;

            while(!flag1.get()){
              i++;
            }
            System.out.println(i);
            System.out.println(Thread.currentThread().getName());
        });
        thread.start();

        TimeUnit.SECONDS.sleep(1);
        turn();
        
        // atomıc oldugu icin diger threadte bu degeri ramden okur rama yazılır cpu cache
        // ten okumaz bu degeri !

        

    //    coffe builder=new coffe.Builder("coffe").GetSurname("surname").Build();

    //     // static factory method -->static class + 

    // //     // private class factory{
    //         // static methodlar ! 
    //       // builder design pattern --> string ...
    // //     }
    //  Supplier<String> suplier=()->new String("couple");
    //  System.out.println(suplier.get());

    //  Integer k=new Integer(6);
    //  System.out.println(k.intValue());
    //  int m=4;
    //  System.out.println(Integer.hashCode(m));
       
     }
}
