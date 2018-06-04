package controller;

import model.Sale;
import integration.Printer;
import integration.ItemDTO;
import integration.RegistryCreator;
import integration.ItemRegistry;
import integration.AccountingSystem;
import integration.ItemNotFoundException;
import integration.InventorySystem;
import java.util.ArrayList;
import java.util.List;
import model.InvalidPaymentException;
import model.ItemInformation;
import model.RevenueObserver;
import model.TaxInformation;

/**
 * This is the application's only controller. All calls to the model pass through here.
 */
public class Controller {
    private Sale sale;
    private final ItemRegistry itemRegistry;
    private final AccountingSystem accounting;
    private final InventorySystem inventory;
    private final Printer printer;
    private final List<RevenueObserver> revenueObservers = new ArrayList<>();
    
    /**
     * Creates a new instance.
     * @param regCreator the object responsible for creating registries.
     * @param printer the printer that is responsible for printing the receipt.
     * @param accounting the external accounting system.
     * @param inventory the external inventory system.
     */
    public Controller(RegistryCreator regCreator, Printer printer,
            AccountingSystem accounting, InventorySystem inventory) {
    	this.itemRegistry   = regCreator.getItemRegistry();
        this.printer        = printer;
        this.accounting     = accounting;
        this.inventory      = inventory;
    }
    
    /**
     * Creates an empty instance of {@link Sale}, which will be used for all
     * information regarding the sale that is now started.
     */
    public void startSale() {
        sale = new Sale();
        for (RevenueObserver revenueObserver : revenueObservers) {
            sale.addRevenueObserver(revenueObserver);
        }
    }
    
    /**
     * Takes an entered itemID, finds the corresponding item, adds it to
     * <code>sale</code> and returns information about the sale.
     * @param itemID An identifier of an item that is bought.
     * @return Updated sale information (newly added item's price and
     * description, and the total price of sale) that is to be sent to View.
     * @throws integration.ItemNotFoundException Thrown to View when an item is not found.
     */
    public ItemInformation enterItemID(String itemID) throws ItemNotFoundException {
        ItemDTO registeredItem = itemRegistry.findItem(itemID);
    	return sale.addItem(registeredItem);
    }
    
    /**
     * Takes an entered <code>itemID</code>, finds the corresponding item, adds
     * a specified amount of them to <code>sale</code> and returns information
     * about the sale.
     * @param itemID An identifier of an item that is bought.
     * @param quantity The amount of a type of item that is bought.
     * @return Updated sale information (newly added item's price and
     * description, and the total price of sale) that is to be sent to View.
     * @throws integration.ItemNotFoundException
     */
    public ItemInformation enterItemIDAndQuantity(String itemID, int quantity) throws ItemNotFoundException {
        ItemDTO registeredItem = itemRegistry.findItem(itemID);
        return sale.addItems(registeredItem, quantity);
    }
    
    /**
     * Calculates the total taxes of a sale after all items are registered.
     * @return the total tax amount of the sale.
     */
    public TaxInformation indicateAllItemsRegistered() {
        return sale.calculateTaxes();
    }
    
    /**
     * Calculates the change based on the running total of <code>sale</code>
     * and the amount paid by the customer, prints a receipt and displays
     * the change.
     * @param paidAmount the amount that is paid by the customer.
     * @return the change that is to be given to the customer.
     * @throws model.InvalidPaymentException Thrown to View when an item is not found.
     */
    public int enterPaidCash(int paidAmount) throws InvalidPaymentException {
        int changeAmount = sale.finalizeSale(paidAmount);
        sale.printReceipt(printer);
        accounting.bookkeep(sale);
        inventory.updateInventory(sale);
        return changeAmount;
    }
    
    public void addRevenueObserver(RevenueObserver revenueObserver) {
        revenueObservers.add(revenueObserver);
    }
            
    
}