package epam;

public class Circle {

    private Point center;
    private int radius;

    public Circle(){
        center = new Point();
        radius=10;
    }

    private void move(int distance){
        this.center.setX(center.getX()+distance);
        this.center.setY(center.getY()+distance);
        System.out.println("x= " + center.getX() + " y= " + center.getY());
    }

    private boolean hit(Point p){

        double n = Math.pow(p.getX()-this.center.getX(),2);
        double m = Math.pow(p.getY()-this.center.getY(),2);
        double rad=Math.pow(getRadius(),2);

        if(n+m<=rad) {
            return true;
        }else return false;
    }

    private boolean hitCircle(Circle c){


        return false;
    }

    public static void main(String[] args){
        Circle c = new Circle();
        //c.move(11);
        System.out.println(c.hit(new Point()));
        System.out.println(c.hit(new Point(34, 25)));
        System.out.println(c.hit(new Point(21, 12)));
        System.out.println(c.hit(new Point(21, 0)));
        System.out.println(c.hit(new Point(10, 10)));
        System.out.println(c.hit(new Point(10, 8)));
        System.out.println(c.hit(new Point(10, 0)));
        System.out.println(c.hit(new Point(6, 6)));
        System.out.println(c.hit(new Point(-6, -6)));


        System.out.println(c.toString());
        System.out.println("___");
        Circle c1= new Circle();
        c1.move(15);
    }

    @Override
    public String toString() {
        return center.toString() + " radius="+radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

}
class Point{

    private int x;
    private int y;

     Point(){
        x=0;
        y=0;
    }

     Point(int xx, int yy){
        x=xx;
        y=yy;
    }

    @Override
    public String toString() {
        return "x="+x+" y="+y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
