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
    private final String[] DaysOfTheWeekNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    public DateObj(int inputYear, int inputMonth, int inputDay, int dayOfTheWeekNumber){
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
        // January first 1500 was a Monday
        int DayNumber = 0;



        return DayNumber;
    }

    public String GetNote(){
        return this.note;
    }

    public void SetNote(String input){
        this.note = input;
    }

    public int GetDay(){
        return this.Day;
    }

    public int GetMonth(){
        return this.Month;
    }

    public int GetYear(){
        return this.Year;
    }



    public static void main(String[] args) {

    }

    @Override
    public String toString(){

        return (String.valueOf(
                this.Year +
                '.' +
                this.Month +
                '.' +
                this.Day));
    }


}
