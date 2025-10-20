public class Tester {
    public static void main(String[] args) {
        DateObj BirthDate = new DateObj(1982,3,19);

        System.out.println(BirthDate.toString());
        System.out.println(BirthDate.GetDayOfTheWeekName());

        BirthDate.SetNote("It's my birthday!");

        System.out.println(BirthDate.GetNote());
    }
}
