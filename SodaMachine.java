import java.util.ArrayList;
import java.util.Random;

public class SodaMachine {

    private final ArrayList<String> machineContents = new ArrayList<>();
    private float cashInMachine;
    private final ArrayList<Integer> inventory = new ArrayList<>();
    private final ArrayList<Double> prices = new ArrayList<>();
    private float coinsPending;

    public SodaMachine(){
        this.cashInMachine = 0.0f;
        this.coinsPending = 0.0f;
        this.machineContents.addFirst("Dr. Pepper");
        this.inventory.addFirst(10);
        this.prices.addFirst(0.75);
    }

    public void insertCoin(String coinName){
        String LocalName = coinName.toLowerCase();
        switch (LocalName){
            case "quarter"  : setCoinsPending(0.25f);
                System.out.println(randomCoinInsertMessage() + String.format("%.2f", coinsPending));
                break;
            case "nickel"   : setCoinsPending(0.05f);
                System.out.println(randomCoinInsertMessage() + String.format("%.2f", coinsPending));
                break;
            case "penny"    : setCoinsPending(0.01f);
                System.out.println(randomCoinInsertMessage() + String.format("%.2f", coinsPending));
                break;
            case "dime"     : setCoinsPending(0.10f);
                System.out.println(randomCoinInsertMessage() + String.format("%.2f", coinsPending));
                break;
            default:
                System.out.println("Your coin was rejected and plops into the coin return with a \"plink\"");
        }
    }

    private void setCoinsPending(float dollarDecimal){
        coinsPending += dollarDecimal;
    }

    public Double GetSelectionPrice(int selection){
        return prices.get(selection);
    }

    public String GetSelectionName(int selection){
        return machineContents.get(selection);
    }

    public void BuySoda(int selection){
        int available = inventory.get(selection);
        if (coinsPending >= GetSelectionPrice(selection) && available >=1){
            coinsPending -= GetSelectionPrice(selection);
            inventory.set(selection, (available-1) );
            cashInMachine += GetSelectionPrice(selection);
            System.out.println("The machine whirs to life lamely and just when you think it ate your money you hear a can drop into the shoot.");
        }
        else if (available < 1) {
            System.out.println("The machine whirs to life lamely and just when you think it ate your money you see the \"Please Make Another Selection\" text scrawl across the display.");
        }
        if (coinsPending > 0){
            System.out.println("Your $" + String.format("%.2f",coinsPending) + " clinks down into the coin return.");
            coinsPending = 0;
        }
    }

    public void checkInventory(){
        for (int i = 0 ; i < machineContents.size(); i++){
            System.out.println(machineContents.get(i) + "   QTY:   " + inventory.get(i));
        }
    }

    private String randomCoinInsertMessage(){
        Random roll = new Random();
        float thisRoll = roll.nextFloat();
        if (thisRoll < 0.35f){
            return "Your coin rattles inside the machine and the display reads :  $";
        }
        else if (thisRoll < 0.65f) {
            return "You can't hear your coin go in but the display reads :  $";
        }
        else if (thisRoll < 0.95) {
            return "Your coin gets stuck for a second but you bump the machine and the display eventually reads :  $";
        }
        return "Your coin pops out the coin return but the display eventually reads :  $";
    }

    public void checkCashInMachine(){
        System.out.println("You open up the machine and find $" + String.format("%.2f",cashInMachine));
    }

    public void setInventory(int selection, int qty){
        this.inventory.set(selection, qty);
    }





    // insertcoin DONE
    // getchange
    // buy
}
