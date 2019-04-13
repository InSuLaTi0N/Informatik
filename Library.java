public class Library {

   private Book [] myBooks;

   private int number;

   public Library() {
	   myBooks = new Book[10];
       number = 0;
       System.out.println("Hello, I am a small library for at most 10 books.");
       }

   public void register(Book book) {
       myBooks[number] = book;
       number += 1;
       System.out.println("A new book is registered: " + book);
    }
}