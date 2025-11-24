import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.time.*;
// constructor DONE

// compare dates

// day of the week

// override .toString DONE
public final class DateObj {

    private final int Day;
    private final int Month;
    private final int Year;
    private final String DayOfTheWeekName;
    private final int DayOfTheWeekNumber;
    private String note;
    private static final String[] DaysOfTheWeekNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};



    // you can pass in a LocalDate type to create this DateObject
    public DateObj(LocalDate date){
        this(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
    }

    // if there's no arguments it assumes you're asking for today
    public DateObj(){
        this(LocalDate.now());
    }

    // 3 constructor argument
    public DateObj(int inputYear, int inputMonth, int inputDay){
        // maybe do some error handling
        this.Day = inputDay;
        this.Month = inputMonth;
        this.Year = inputYear;
        this.DayOfTheWeekNumber = CalculateDayOfTheWeek();
        this.DayOfTheWeekName = (DayOfTheWeekName(this.DayOfTheWeekNumber));

    }

    private String DayOfTheWeekName(int DoW){
        return DaysOfTheWeekNames[DoW];
    }

    public String GetDayOfTheWeekName(){
        return this.DayOfTheWeekName;
    }

    public int GetDayOfTheWeekNumber(){
        return this.DayOfTheWeekNumber;
    }

    public int CalculateDayOfTheWeek(){
        // my birthday is march 19th 1982 and it was a friday
        // January first 1500 was a Monday, so December 31st 1499 was a sunday which is index 0
        int DayNumber = 0;
        long days = ChronoUnit.DAYS.between(LocalDate.of(1499, 12, 31),LocalDate.of(this.Year, this.Month, this.Day));
        DayNumber = (int) (days % 7);
        return DayNumber;
    }

    public String getNote(){
        return this.note;
    }

    public void setNote(String input){
        this.note = input;
    }

    public int getDay(){
        return this.Day;
    }

    public int getMonth(){
        return this.Month;
    }

    public int getYear(){
        return this.Year;
    }

    public static void main(String[] args) {

    }

    @Override
    public String toString(){

        return String.format("%04d.%02d.%02d", this.Year, this.Month, this.Day);
    }

    public int daysBetween(DateObj other) {
        LocalDate d1 = LocalDate.of(Year, Month, Day);
        LocalDate d2 = LocalDate.of(other.Year, other.Month, other.Day);
        long days = ChronoUnit.DAYS.between(d1, d2);
        return (int) days;
    }




}
