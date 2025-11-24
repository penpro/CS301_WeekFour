public class StringFirstUnique {
    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("requires a string argument");
        }

        String s = args[0];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean isDuplicate = false;

            for (int j = 0; j < s.length(); j++) {
                if (i != j && s.charAt(j) == c) {
                    isDuplicate = true;
                    break;
                }
            }

            if (!isDuplicate) {
                System.out.println(c);
                return;   // stop after first unique
            }
        }
    }
}
