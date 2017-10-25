package oving10Regex;

public class o10 {

    public static void main(String[] args) {
        String s1 = "asdfgh";
        String s2 = "asd3fgh";
        String s3 = "as2d3fgh";
        System.out.println(s1.matches(".*\\d+.*"));
        System.out.println(s2.matches(".*\\d+.*"));
        System.out.println(s3.matches(".*\\d+.*"));

        String d1 = "2010-12-03";
        String d2 = "2010/12/03";
        String d3 = "14/05/2007";
        System.out.println(d1.matches("\\d{2}\\/\\d{2}\\/\\d{4}"));
        System.out.println(d2.matches("\\d{2}\\/\\d{2}\\/\\d{4}"));
        System.out.println(d3.matches("\\d{2}\\/\\d{2}\\/\\d{4}"));

        String f1 = "sdfer2345";
        String f2 = "sdfer2345d";
        String f3 = "sdfer2345d3";
        System.out.println(f1.matches(".{10}.*"));
        System.out.println(f2.matches(".{10}.*"));
        System.out.println(f3.matches(".{10}.*"));

        String g1 = "asdfjkklf";
        String g2 = "as2dfjkklf";
        String g3 = "as=dfjkklf";
        System.out.println(g1.matches(".*[^(A-Za-z)]+.*"));
        System.out.println(g2.matches(".*[^(A-Za-z)]+.*"));
        System.out.println(g3.matches(".*[^(A-Za-z)]+.*"));
    }


}
