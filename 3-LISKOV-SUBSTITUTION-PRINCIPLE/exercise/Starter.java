interface Shape {
    double getArea();
}

class Rectangle implements Shape {
    protected int width;
    protected int height;
      
    public void setWidth(int width) { 
        this.width = width; 
    }
    public void setHeight(int height) { 
        this.height = height;
    }
    
    @Override
    public double getArea() { 
        return width * height;
    }
}

class Square implements Shape {
    protected int side;

    public void setSide(int side){
        this.side = side;
    }

    @Override
    public double getArea() { 
        return side * side;
    } 
}



class Main {

    public static void printArea(Shape[] shapes){
    for (Shape shape : shapes) {
          System.out.println("Area " + shape.getArea());
         }
}
    
    public static void main(String[] args) {
        Rectangle rec = new Rectangle();
        rec.setWidth(5);
        rec.setHeight(10);

        Square squ = new Square();
        squ.setSide(10);
        
        Shape[] myShapes = {rec, squ};
        printArea(myShapes);
        
    }
    
}