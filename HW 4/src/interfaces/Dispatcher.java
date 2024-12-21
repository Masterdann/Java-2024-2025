package interfaces;

import exceptions.NoAvailableTaxiException;

public interface Dispatcher {
    void placeOrder(String orderDetails) throws NoAvailableTaxiException, InterruptedException;

    void notifyTaxiFree(models.TaxiDriver taxi);
}