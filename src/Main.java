import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File f = new File("text.txt");
        FileWriter writer = null;
        Scanner sc = null;
        int sum = 0;
        int count = 0;

        try {
            writer = new FileWriter("text.txt", true); // 追加写入
            sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.contains("元/台")) {
                    String[] parts = line.split("，");
                    if (parts.length > 1) {
                        String pricePart = parts[1].split("元/台")[0];
                        int price = Integer.parseInt(pricePart);
                        sum += price;
                        count++;
                        System.out.println(price);
                    }
                }
            }

            if (count > 0) {
                float ave = (float) sum / count;
                writer.write("\n平均价格：" + ave + "元/台");
                System.out.println("平均价格：" + ave + "元/台");
            } else {
                System.out.println("没有找到有效的价格数据。");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (sc != null) {
                    sc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
