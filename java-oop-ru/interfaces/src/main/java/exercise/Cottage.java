package exercise;

// BEGIN
public class Cottage implements Home {
    double area;
    int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return (int) area;
    }

    @Override
    public int compareTo(Home i) {
        if (getArea() > i.getArea()) {
            return 1;
        } else if (getArea() < i.getArea()) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return floorCount + " этажный коттедж площадью " + area + " метров";
    }
}
// END
