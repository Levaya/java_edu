public class Runner {
    public static void main(String[] args){
     Point a=new Point(5, 6);
     Point b=new Point(2, 3);
     System.out.println(Point.distance(a,b));
     System.out.println(a.distance(b));
    }
}
