import javafx.scene.control.Tab;

import java.io.InputStream;
import java.util.Scanner;

public class Test {
    static Product carts[] = new Product[3];//创建购物车（用数组模拟）
    static int count = 0;

    public static void main(String[] args) throws ClassNotFoundException {
        /*
        CTRL+ALT+L
         */
        boolean bool = true;
        while (bool) {
            System.out.println("请输入用户名：");
            Scanner sc = new Scanner(System.in);
            String username = sc.next();//阻塞方法

            System.out.println("请输入密码：");
            String password = sc.next();

            //File file=new File("C:\\Users\\Administrator\\IdeaProjects\\ConsoleShop\\src\\users.xlsx");
            InputStream inPro = Class.forName("Test").getResourceAsStream("/product.xlsx");//  /表示的就是classpath
            InputStream in = Class.forName("Test").getResourceAsStream("/users.xlsx");//  /表示的就是classpath
            ReadProductExcel readProductExcel = new ReadProductExcel();
            ReadUserExcel readExcel = new ReadUserExcel();//创建对象
            User users[] = readExcel.readExcel(in);
            Product products[] = readProductExcel.getAllProduct(inPro);
            for (int i = 0; i < users.length; i++) {
                if (username.equals(users[i].getUsername()) && password.equals(users[i].getPassword())) {
                    bool = false;
                    shopping(sc);
                    while (true) {
                        System.out.println("查看购物车请按1  继续购物请按2  结账请按3  退出请按4");
                        int choose = sc.nextInt();
                        if (choose == 1) {
                            showCarts();
                        } else if (choose == 2) {
                            shopping(sc);
                        }else if (choose == 3) {
                             shopEnding();
                            Order order=new Order();
                            order.setUser(users[i]);
                            order.setProducts(carts);
                            for (Product product : carts) {
                            }
                        } else if (choose == 4) {
                            System.exit(0);
                            break;
                        }
                    }
                    break;
                } else {
                    System.out.println("登录失败");
                }
            }
        }
    }

    public static void shopping(Scanner sc) throws ClassNotFoundException {
        InputStream inPro = Class.forName("Test").getResourceAsStream("/product.xlsx");//  /表示的就是classpath
        ReadProductExcel readProductExcel = new ReadProductExcel();
        Product products[] = readProductExcel.getAllProduct(inPro);
        for (Product product : products) {
            System.out.print(product.getId());
            System.out.print("\t" + product.getName());
            System.out.print("\t\t" + product.getPrice());
            System.out.println("\t\t" + product.getDesc());
        }
        /*
        遍历数组
         */
        System.out.println("请输入商品ID，把该商品加入购物车：");
        String pId = sc.next();
        ReadProductExcel readProductExcel1 = new ReadProductExcel();
        inPro = null;
        inPro = Class.forName("Test").getResourceAsStream("/product.xlsx");//  /表示的就是classpath
        Product product = readProductExcel1.getProductById(pId, inPro);
        if (product != null) {
            /*
            把商品加入购物车
             */
            carts[count++] = product;
        }
    }

    public static void showCarts(){
        for (Product product : carts) {
            if (product != null) {
                System.out.print(product.getId());
                System.out.print("\t" + product.getName());
                System.out.print("\t\t" + product.getPrice());
                System.out.println("\t\t" + product.getDesc());
            }
        }
    }

    public static void shopEnding(){
        double n=0.0;
        for (Product product : carts) {
            if (product != null) {
                n=n+product.getPrice();
            }
        }
        System.out.println("总共"+n+"元");
    }
}