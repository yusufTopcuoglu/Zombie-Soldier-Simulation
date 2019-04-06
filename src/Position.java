
import java.util.Random;

/**
 * Represents a position in 2-D gird with
 * double x and y coordinates.
 *
 */
public class Position {
    private double x;
    private double y;
    
    private double length;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
        
        this.calculateLength();
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    public double distance(Position other) {
        return Math.sqrt(Math.pow(this.x-other.getX(), 2)+Math.pow(this.y-other.getY(), 2));
    }
    
    void add(Position other) {
        this.x += other.x;
        this.y += other.y;
        
        this.calculateLength();
    }

    Position getSummedPosition(Position other){
        return new Position(this.x + other.x, this.y + other.y);
    }

    Position getSubtractedPosition(Position other){
        return new Position(this.x - other.x, this.y - other.y);
    }

    Position getMultipliedPosition(double constant){
        return new Position(this.x * constant, this.y * constant);
    }

    private void calculateLength() {
        this.length = Math.sqrt(Math.pow(x, 2.0)+Math.pow(y, 2.0));
    }
    
    void normalize() {
        this.x /= this.length;
        this.y /= this.length;
        
        this.length = 1.0;
    }
    
    static Position generateRandomDirection() {
        Random random = new Random();
        double x = -1+random.nextDouble()*2;
        double y = -1+random.nextDouble()*2;
        
        Position result = new Position(x, y);
        result.normalize();
        return result;
    }
    
    @Override
    protected Position clone() {
        return new Position(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.x != other.x) {
            return false;
        }
        return this.y == other.y;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return String.format("(%.2f, %.2f)", x, y);
    }
    
}
