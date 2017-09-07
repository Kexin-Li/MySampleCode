package org.likexin.algs;

/**
 * 字符串查找：
 *
 * Created by likexin5 on 2017/9/6.
 */
public class Strstr {

    /**
     * AC
     * @param source 主串
     * @param target 副串
     * @return index or -1
     */
    public int strstr_1(String source, String target) {
        if (source == null || target == null)
            return -1;
        for (int i = 0; i < source.length() - target.length() + 1; i++) {
            int j;
            for (j = 0; j < target.length(); j++) {
                if (source.charAt(i + j) != target.charAt(j))
                    break;
            }
            if (j == target.length()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * AC
     * @param source 主串
     * @param target 副串
     * @return index or -1
     */
    public int strstr_2(String source, String target) {
        if (source == null || target == null)
            return -1;
        for (int i = 0; i <= source.length() - target.length(); i++) {
            int j = 0;
            while (j < target.length()) {
                if (source.charAt(i + j) != target.charAt(j))
                    break;
                j++;
            }
            if (j == target.length()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 报错
     * @param source 主串
     * @param target 副串
     * @return index or -1
     */
    public int strstr_3(String source, String target) {
        if (source == null || target == null)
            return -1;
        int i = 0, j = 0;
        while (source.charAt(i) != ' ' && target.charAt(j) != ' ') { // TODO StringIndexOutOfBoundsException
            if (source.charAt(i) == target.charAt(j)) {
                i++; j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (target.charAt(j) == ' ') {
            return i - j;
        }
        return -1;
    }

    /**
     * 在两个串都为空的情况下会报异常，可能是正面去比较相等的原因。如果反过来比较不相等，如 strstr_1 就不会报异常。
     * @param source 主串
     * @param target 副串
     * @return index or -1
     */
    public int strstr_4(String source, String target) {
        if (source == null || target == null)
            return -1;
        for (int i = 0; i < source.length() - target.length() + 1; i++) { // TODO exception
            int k = i;
            for (int j = 0; j < target.length(); j++) {
                if (source.charAt(k) == target.charAt(j)) {
                    if (j == target.length() - 1)
                        return i;
                    k++;
                } else {
                    break;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Strstr strstr = new Strstr();
        System.out.println(strstr.strstr_1("", ""));
        System.out.println(strstr.strstr_2("", ""));
        System.out.println(strstr.strstr_3("", ""));
        System.out.println(strstr.strstr_4("", ""));
    }

}
