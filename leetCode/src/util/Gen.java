package util;

import java.io.*;

/**
 * Created by hui10.yang on 18/9/11.
 */
public class Gen {
    public static void main(String[] args) {
        String fileName = "/Users/trade.vip/Desktop/yanghui.txt";
        String line = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            line = in.readLine();
            while (line != null) {
                System.out.println(line);
                writeOut(line);
                line = in.readLine();
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeOut(String content) {
        String fileName = "/Users/trade.vip/Desktop/outyanghui.txt";
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName,true));
            out.write(content);
            out.newLine();  //注意\n不一定在各种计算机上都能产生换行的效果
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
