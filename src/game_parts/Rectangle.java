package game_parts;

import java.util.ArrayList;
import java.util.List;

//Itamar Cohen 318897089

/**
 * The type Rectangle.This code implements a Rectangle class that represents a rectangle shape in a 2D plane.
 * The class contains a constructor that initializes the upper left corner point, the width, and the height of
 * the rectangle.
 * The class also has a method called intersectionPoints that receives a Line object as an input parameter and
 * returns a list of intersection points between the rectangle and the line. This method internally uses another
 * method called createLines, which creates an array of four lines representing the sides of the rectangle.
 */
public class Rectangle {
    private static final int RECTANGLE_EDGES = 4;
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Instantiates a new Rectangle.
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */

    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Instantiates a new Rectangle.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     */
    public Rectangle(double x, double y, double width, double height) {
        this.upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;
    }

    /**
     * Intersection points list.
     * Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line the line
     * @return the list
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();
        Line[] recSides = new Line[4];
        recSides = createLines(this);
        for (int i = 0; i < 4; i++) {
            if (recSides[i].isIntersecting(line)) {
                Point intersectionPoint = recSides[i].intersectionWith(line);
                if (!intersectionPoints.contains(intersectionPoint)) {
                    intersectionPoints.add(intersectionPoint);
                }
            }
        }
        return intersectionPoints;
    }

    /**
     * Create lines line [ ].
     *
     * @param rec the rec
     * @return the line [ ]
     */
    public static Line[] createLines(Rectangle rec) {
        double x1 = rec.getUpperLeft().getX();
        double y1 = rec.getUpperLeft().getY();
        double x2 = x1 + rec.getWidth();
        double y2 = y1 + rec.getHeight();

        Line[] sides = new Line[RECTANGLE_EDGES];
        sides[0] = new Line(x1, y1, x2, y1); // top side
        sides[1] = new Line(x2, y1, x2, y2); // right side
        sides[2] = new Line(x2, y2, x1, y2); // bottom side
        sides[3] = new Line(x1, y2, x1, y1); // left side

        return sides;
    }


    /**
     * Get vertices point [ ].
     * Returns an array of the four vertices of the rectangle.
     *
     * @return the point [ ]
     */
    public Point[] getVertices() {
        Point[] vertices = new Point[RECTANGLE_EDGES];
        vertices[0] = this.upperLeft;
        vertices[1] = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        vertices[2] = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() - this.height);
        vertices[3] = new Point(this.upperLeft.getX(), this.upperLeft.getY() - this.height);
        return vertices;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }


    /**
     * Gets center x.
     *
     * @return the center x
     */
    public double getCenterX() {
        return ((this.upperLeft.getX() + this.width) / 2);
    }

    /**
     * Gets center y.
     *
     * @return the center y
     */
    public double getCenterY() {
        return ((this.upperLeft.getY() + this.height) / 2);
    }

    /**
     * Sets upper left.
     *
     * @param upperLeft the upper left
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }

    /**
     * Sets width.
     *
     * @param width the width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(double height) {
        this.height = height;
    }
}