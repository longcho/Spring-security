package cn.longzzai.interview.textface;

/**
 * @author longcho
 * 2017-11-18-16:41
 */
public class Test1 {
    public static void main(String[] args) {
        String s1 = new StringBuilder("vo")
                .append("id").toString();
        System.out.println(s1.intern() == s1);
        String s2 = new StringBuilder("ja")
                .append("va").toString();
        System.out.println(s2.intern() == s2);
        String s3 = "abcd";
        String s4 = new String(s3);
        System.out.println(s4 == s3);
    }
}
