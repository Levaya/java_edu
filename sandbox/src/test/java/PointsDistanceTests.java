import org.testng.Assert;
import org.testng.annotations.Test;

public class PointsDistanceTests {
    @Test
    public void testDistance(){
        Point a = new Point(5, 8);
        Point b = new Point(2, 4);
        Assert.assertTrue (Point.distance(a,b)==5.0);
        Assert.assertTrue (a.distance(b)==5.0);
    }
}
