package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串s,通过字符串s中的每个字母转变大小写，我们可以获得一个新的字符串，返回所有
 * 可能得到到字符串集合
 * Created by hui10.yang on 18/8/30.
 */
public class RandomOrder {

    public List<String> orderChar(String s) {
        List<String> result = new ArrayList<>();
        result.add("");
        char[] in = s.toCharArray();

        for (int i = 0; i < in.length; i++) {
            int len = result.size();
            if (in[i] >= 48 && in[i] <= 57) {
                for (int j = 0; j < len; j++) {
                    result.set(j, result.get(j).concat(Character.toString(in[i])));
                }
            } else {
                for (int j = 0; j < len; j++) {
                    char c = Character.toLowerCase(in[i]);
                    char c1 = Character.toUpperCase(in[i]);
                    String a = result.get(j);
                    String cS=a.concat(Character.toString(c));
                    result.set(j, cS);
                    String d=a.concat(Character.toString(c1));
                    result.add(j + len, d);
                }
            }
        }
        return result;
    }
}
