public class Rot13 {
    // error check args

    // we ingest a string

    // loops each character in the string and add 13

    // if the resulting character is greater than 26th character, subtract 26 for the rotated position so 110+13 = 123 which is ~, subtract 26 is 97 which equals a

    // going to make a tool method to print the unicode library from a - z

    // need to account for upper and lower case upper case is 65-90, lower case is 97-122

    // maybe make another one for non letter strict encrypting?

    // find a way to send it to your clipboard so it's not useless?


    public static void main(String[] args) {
        if (args.length != 1) throw new IllegalArgumentException("This class except one and only one argument");
        String ToEncrypt = args[0];
        if (ToEncrypt.isBlank()) throw new IllegalArgumentException("No point in encrypting nothing");

        //PrintUnicodeLibrary();

        System.out.println(rot13(ToEncrypt));

    }

    public static void PrintUnicodeLibrary(){
        char output = 0;
        for (int i = 0; i < 200;i++){
            output = (char) i;
            System.out.print(i + "    ");
            System.out.println(output);

        }
    }

    private static String Rot13BuilderLetterStrict(String inputString){


        StringBuilder StringInProgress = new StringBuilder();

    for (int i = 0 ; i < inputString.length() ; i++){
        char ThisChar = inputString.charAt(i);

        // if it's in lower case range
        if (ThisChar > 'a' && ThisChar < 'z'){
            StringInProgress.append(((char) ThisChar + 13) > 'z' ? ((char) (ThisChar-13)) : ((char) (ThisChar+13)));
        }
        // if it's in upper care range
        else if (ThisChar > 'A' && ThisChar < 'Z') {
            StringInProgress.append((ThisChar + 13) > 'Z' ? ((char) (ThisChar-13)) : ((char) (ThisChar+13)));
        }

        // if it's not a letter
        else {
            StringInProgress.append(ThisChar);

        }
    }


        return StringInProgress.toString();
    }
    private static String rot13(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c >= 'a' && c <= 'z') {
                c = (char) ('a' + (c - 'a' + 13) % 26);
            } else if (c >= 'A' && c <= 'Z') {
                c = (char) ('A' + (c - 'A' + 13) % 26);
            }

            sb.append(c);
        }
        return sb.toString();
    }
}




