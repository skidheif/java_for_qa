package ru.stqa.pft.sandbox;

public class MyFirstProgram {

	public static void main(String[] args) {
		hello("world");
		hello("user");
		hello("Vasya");

		Square s = new Square(5);
		System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

		Rectangle r = new Rectangle(4, 6);
		System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
		System.out.println();

		//hometask #2
		Point p1 = new Point( 12, 12);
		Point p2 = new Point(12,12);
		System.out.println("Расстояние между двумя точками на плоскости с координатами" + " x1 = " + p1.x + ", y1 = " + p1.y + " и " + " x2 = " + p2.x + ", y2 = " + p2.y  + " равно " + p1.distance(p2));
	}

	public static void hello(String somebody){
		System.out.println("Hello, " + somebody + "!");
	}

}