import exceptions.NoAvailableTaxiException;
import models.TaxiDispatcher;
import models.TaxiDriver;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TaxiApplication {

    public static void main(String[] args) {
        TaxiDispatcher dispatcher = new TaxiDispatcher();
        ExecutorService taxiExecutor = Executors.newCachedThreadPool();

        // Create and register taxi drivers
        TaxiDriver taxi1 = new TaxiDriver("Taxi-1", dispatcher);
        TaxiDriver taxi2 = new TaxiDriver("Taxi-2", dispatcher);
        TaxiDriver taxi3 = new TaxiDriver("Taxi-3", dispatcher);

        dispatcher.registerTaxi(taxi1);
        dispatcher.registerTaxi(taxi2);
        dispatcher.registerTaxi(taxi3);

        // NUMBER OF TAXIS
        taxiExecutor.execute(taxi1);
        taxiExecutor.execute(taxi2);
        taxiExecutor.execute(taxi3);

        //ORDERS
        try {
            dispatcher.placeOrder("Order1");
            Thread.sleep(1000); // Delay between orders
            dispatcher.placeOrder("Order2");
            Thread.sleep(1000);
            dispatcher.placeOrder("Order3");
            Thread.sleep(1000);
            dispatcher.placeOrder("Order4");
            Thread.sleep(1000);
            dispatcher.placeOrder("Order5");
            Thread.sleep(1000);
            dispatcher.placeOrder("Order6");
        } catch (InterruptedException | NoAvailableTaxiException e) {
            System.err.println("Error placing order: " + e.getMessage());
        }
        System.out.println("Work done with success");
        taxiExecutor.shutdown();
        
    }
}