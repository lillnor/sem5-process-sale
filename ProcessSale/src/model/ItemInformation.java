package model;

/**
 * Contains the price and description of a recently added item, as well as
 * the running total of the entire sale.
 */
public class ItemInformation {
	private final int price;
	private final String descript;
	private final int runningTotal;
	
        /**
         * Creates a new instance.
         * @param price the price of the latest registered item.
         * @param descript the description of the latest registered item.
         * @param runningTotal the total cost of all registered items.
         */
	public ItemInformation(int price, String descript, int runningTotal) {
		this.price = price;
		this.descript = descript;
		this.runningTotal = runningTotal;
	}
	
        @Override
        public boolean equals(Object other){
            if (other == null || !(other instanceof ItemInformation)) {
                return false;
            }
            ItemInformation otherInfo = (ItemInformation) other;
            return (this.price == otherInfo.price && this.descript.equals(otherInfo.descript)
                    && this.runningTotal == otherInfo.runningTotal);
        }
        
        @Override
	public String toString() {
            return descript + " (" + price + "kr)    Total: " + runningTotal + "kr";
	}
}
