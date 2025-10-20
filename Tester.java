public class Tester {
    public static void main(String[] args) {
        //DateObj BirthDate = new DateObj(1982,3,19);

        //System.out.println(BirthDate.toString());
        //System.out.println(BirthDate.GetDayOfTheWeekName());

        //BirthDate.SetNote("It's my birthday!");

        //System.out.println(BirthDate.GetNote());

        SodaMachine testermachine = new SodaMachine();
        testermachine.checkInventory();
        testermachine.insertCoin("quarter");
        testermachine.insertCoin("quarter");
        testermachine.insertCoin("quarter");
        testermachine.insertCoin("quarter");
        testermachine.BuySoda(0);
        testermachine.checkInventory();
        testermachine.checkCashInMachine();
        testermachine.setInventory(0,0);
        testermachine.insertCoin("quarter");
        testermachine.insertCoin("quarter");
        testermachine.insertCoin("quarter");
        testermachine.BuySoda(0);
    }
}
