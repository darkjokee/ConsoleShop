public class Order {
    private Product products[];
    private User user;
    private int shuliang;
    private double shangpinjiage;
    private double shijijiage;
    private String shijian;

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getShuliang() {
        return shuliang;
    }

    public void setShuliang(int shuliang) {
        this.shuliang = shuliang;
    }

    public double getShangpinjiage() {
        return shangpinjiage;
    }

    public void setShangpinjiage(double shangpinjiage) {
        this.shangpinjiage = shangpinjiage;
    }

    public double getShijijiage() {
        return shijijiage;
    }

    public void setShijijiage(double shijijiage) {
        this.shijijiage = shijijiage;
    }

    public String getShijian() {
        return shijian;
    }

    public void setShijian(String shijian) {
        this.shijian = shijian;
    }
}
