/*
 * Lehrstuhl Softwaretechnologie, TU Dresden, Datei HelloLibrary.java
 *
 * HelloLibrary-Klasse implementiert eine Anwendung,
 * die die Aufnahme von BÃ¼chern simuliert
 */

public class HelloLibrary {

  // Simulation durch main()
  public static void main(String[] args) {

  // Erzeugung eines Library-Objektes
	Library myLib = new Library();
	

  // Erzeugung von zwei Book-Objekten
	Book b1 = new Book("C Programmierung");
	Book b2 = new Book("Mathe");


  // Nachricht an die Library, dass die Books aufgenommen werden sollen
	myLib.register(b1);
	myLib.register(b2);


  }

}