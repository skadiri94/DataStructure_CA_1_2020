public class SubString {
    public static void main(String[] args) {
        String s = "Suleman";
        int len = s.length() - 1;
        String newS ="";

        for(char n : s.toCharArray()) {
            newS += (n);
            System.out.println(" New String From " + n +" : " + s.substring(1));
        }

        System.out.println(s.substring(0,1) + " " + len + " New String " + newS);
    }
}
