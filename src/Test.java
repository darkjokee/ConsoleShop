import javafx.scene.control.Tab;

import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        /*
        CTRL+ALT+L
         */
        boolean bool=true;
        while (bool) {
            System.out.println("请输入用户名：");
            Scanner sc = new Scanner(System.in);
            String username = sc.next();//阻塞方法

            System.out.println("请输入密码：");
            String password = sc.next();

            //File file=new File("C:\\Users\\Administrator\\IdeaProjects\\ConsoleShop\\src\\users.xlsx");
            InputStream in = Class.forName("Test").getResourceAsStream("/users.xlsx");//  /表示的就是classpath

            InputStream inPro = Class.forName("Test").getResourceAsStream("/product.xlsx");//  /表示的就是classpath

            ReadUserExcel readExcel = new ReadUserExcel();//创建对象
            User users[] = readExcel.readExcel(in);
            for (int i = 0; i < users.length; i++) {
                if (username.equals(users[i].getUsername()) && password.equals(users[i].getPassword())) {
                    /*
                    显示商品的信息
                     */
                    ReadProductExcel readProductExcel=new ReadProductExcel();
                    Product products[]=readProductExcel.getAllProduct(inPro);
                    showProduct();
                    /*
                    遍历数组
                     */
                    int count=0;
                    Product carts[]=new Product[3];//创建购物车（用数组模拟）
                    System.out.println("请输入商品ID，把该商品加入购物车：");
                    String pId=sc.next();
                    ReadProductExcel readProductExcel1=new ReadProductExcel();
                    inPro=null;
                    inPro = Class.forName("Test").getResourceAsStream("/product.xlsx");//  /表示的就是classpath
                    Product product=readProductExcel1.getProductById(pId,inPro);
                    if(product!=null){
                        /*
                        把商品加入购物车
                         */
                        carts[count]=product;
                        count++;
                }
                    while (bool=true) {
                        System.out.println("下一步操作（1查看购物车，2继续购物，3结算）：");
                        String nextStep = sc.next();
                        switch (nextStep) {
                            case "1":
                                readProductExcel = new ReadProductExcel();
                                for (int n = 0; n < count; n++) {
                                    if (carts[n]!=null) {
                                        System.out.print(carts[n].getId());
                                        System.out.print("\t" + carts[n].getName());
                                        System.out.print("\t\t" + carts[n].getPrice());
                                        System.out.println("\t\t" + carts[n].getDesc());
                                    }
                                }
                                continue;
                            case "2":
                                System.out.println("请输入商品ID，把该商品加入购物车：");
                                pId = sc.next();
                                readProductExcel1 = new ReadProductExcel();
                                inPro = null;
                                inPro = Class.forName("Test").getResourceAsStream("/product.xlsx");//  /表示的就是classpath
                                product = readProductExcel1.getProductById(pId, inPro);
                                if (product != null) {
                            /*
                            把商品加入购物车
                             */
                                    carts[count] = product;
                                    count++;
                                }
                                continue;
                            case "3":
                                System.out.println("结算");
                                break;
                            default:
                                break;

                        }
                        break;
                    }
                    bool=false;
                } else {
                    System.out.println("登录失败");
                }
            }
        }
    }
    public static void showProduct() throws ClassNotFoundException{
        InputStream inPro = Class.forName("Test").getResourceAsStream("/product.xlsx");//  /表示的就是classpath
        ReadProductExcel readProductExcel=new ReadProductExcel();
        Product products[]=readProductExcel.getAllProduct(inPro);
        for(Product product:products){
            System.out.print(product.getId());
            System.out.print("\t"+product.getName());
            System.out.print("\t\t"+product.getPrice());
            System.out.println("\t\t"+product.getDesc());
        }
    }
    public static void buyProduct(int count) throws ClassNotFoundException{
        InputStream inPro = Class.forName("Test").getResourceAsStream("/product.xlsx");//  /表示的就是classpath
        ReadProductExcel readProductExcel=new ReadProductExcel();
        Product products[]=readProductExcel.getAllProduct(inPro);
        Scanner sc = new Scanner(System.in);
        Product carts[]=new Product[3];//创建购物车（用数组模拟）
        System.out.println("请输入商品ID，把该商品加入购物车：");
        String pId=sc.next();
        ReadProductExcel readProductExcel1=new ReadProductExcel();
        inPro=null;
        inPro = Class.forName("Test").getResourceAsStream("/product.xlsx");//  /表示的就是classpath
        Product product=readProductExcel1.getProductById(pId,inPro);
        if(product!=null){
                        /*
                        把商品加入购物车
                         */
            carts[count]=product;
        }
    }
}
