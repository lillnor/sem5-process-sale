package view;

import controller.Controller;
import integration.ItemNotFoundException;
import integration.ItemRegistryException;
import model.InvalidPaymentException;
import util.LogHandler;

/**
 * This is a placeholder for the view. It contains only hardcoded calls to the controller.
 */
public class View {
    private Controller contr;
    private final LogHandler logger = LogHandler.getLogger();
    
    /**
     * Constructs a new view, using the specified controller.
     * @param contr This controller will be used for all system operations.
     */
    public View(Controller contr) {
        this.contr = contr;
    }
    
    /**
     * Simulates a sample execution containing calls to all system operations.
     */
    public void sampleExecution() {
        System.out.println("Starting sample execution.");
        contr.addRevenueObserver(new TotalRevenueView());
        logger.setFileLogger();
        
        contr.startSale();
        System.out.println("\nAfter call to startSale()");
        try {
            System.out.println(contr.enterItemID("LK"));
            System.out.println(contr.enterItemIDAndQuantity("SK", 2));
            System.out.println(contr.enterItemIDAndQuantity("LK", 3));
            System.out.println(contr.enterItemID("SK"));
            System.out.println(contr.enterItemID("SK"));
            System.out.println(contr.enterItemID("MK"));
        }
        catch (ItemNotFoundException exc) {
            handleException(invalidIDMessage(exc.getID()), exc);
        }
        catch (ItemRegistryException exc) {
            handleException(databaseFailureMessage(), exc);
        }
        System.out.println(contr.indicateAllItemsRegistered());
        try {
            contr.enterPaidCash(3);
        }
        catch (InvalidPaymentException exc) {
            handleException(invalidPaymentMessage(exc.getPayment(), exc.getPrice()), exc);
        }
        try {
            contr.enterPaidCash(125);
        }
        catch (InvalidPaymentException exc) {
            handleException(invalidPaymentMessage(exc.getPayment(), exc.getPrice()), exc);
        }
        
        contr.startSale();
        System.out.println("\nAfter call to startSale()");
        try {
            System.out.println(contr.enterItemID("LK"));
            System.out.println(contr.enterItemIDAndQuantity("SK", 2));
            System.out.println(contr.enterItemIDAndQuantity("LK", 3));
            System.out.println(contr.enterItemID("SK"));
            System.out.println(contr.enterItemID("SK"));
            System.out.println(contr.enterItemID("rofl"));
        }
        catch (ItemNotFoundException exc) {
            handleException(invalidIDMessage(exc.getID()), exc);
        }
        System.out.println(contr.indicateAllItemsRegistered());
        try {
            contr.enterPaidCash(500);
        }
        catch (InvalidPaymentException exc) {
            handleException(invalidPaymentMessage(exc.getPayment(), exc.getPrice()), exc);
        }
    }
    
    private String databaseFailureMessage() {
        return "The database could not be accessed. Shutting down. \n(the program would shut down here)";
    }
    
    private String invalidIDMessage(String ID) {
        return "The specified ID could not be found: " + ID;
    }
    
    private String invalidPaymentMessage(int payment, int price) {
        return "The entered payment is not enough for the purchase. (Missing " + -(payment - price) + "kr)";
    }
    
    private void handleException(String message, Exception exc) {
        System.out.println(message);
        logger.logException(exc);
    }
}
