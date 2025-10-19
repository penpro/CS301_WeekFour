public class StringFirstUnique {

    public static void main(String[] args) {
        if (args.length < 1) throw new IllegalArgumentException("requires a string argument");

        // extracts the string fom the arguments
        String holder = args[0];

        // initializes the unassigned char values
        char FirstUnique = 0;

        // looks at a character
        for (int i = 0; i < holder.length(); i++) {
            char ThisChar = holder.charAt(i);


            //checks it against the other characters in the string
            boolean isDuplicate = false;

            for (int j = 0; j < holder.length(); j++) {
                if (i != j && holder.charAt(j) == ThisChar) {
                    isDuplicate = true;
                    break;
                }
            }

            // if it's not anywhere else in the string we're done, send it
            if (!isDuplicate) {
                FirstUnique = ThisChar;
                System.out.println(FirstUnique);
            }
        }
    }
}
