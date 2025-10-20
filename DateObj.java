import java.util.Objects;

public final class DateObj {

    public DateObj(int inputYear, int inputMonth, int inputDay){
        final int Day = inputDay;
        final int Month = inputMonth;
        final int Year = inputYear;
    }

    public int GetDay(DateObj inputDate){

    }

    // constructor

    // compare dates

    // day of the week

    // override .toString

    public static void main(String[] args) {

    }

    @Override
    public String toString(DateObj inputDate){
        StringBuilder output = new StringBuilder();

        output.append(this.GetYear);
        output.append('.');
        output.append(this.GetMonth);
        output.append('.');
        output.append(this.GetDay);

        return output.toString();
    }


}
