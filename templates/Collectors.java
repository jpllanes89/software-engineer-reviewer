import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collectors {

  public static void main(String[] args) {

    List<Book> books = generateBooks();
    System.out.println(books);
  }

  private static List<Book> generateBooks() {

    List<Book> books = new ArrayList<>();

    Book book1 =
        new Book.Builder(1)
            .setTitle("The Molecule of More")
            .setAuthors(Arrays.asList(new String[] {"Daniel Z. Lieberman", "Michael E. Long"}))
            .setNumberOfPages(240)
            .build();

    Book book2 =
        new Book.Builder(2)
            .setTitle("Atomic Habits")
            .setAuthors(Arrays.asList(new String[] {"James Clear"}))
            .setNumberOfPages(320)
            .build();

    Book book3 =
        new Book.Builder(3)
            .setTitle("Thinking, Fast and Slow")
            .setAuthors(Arrays.asList(new String[] {"Daniel Kahneman"}))
            .setNumberOfPages(512)
            .build();

    Book book4 =
        new Book.Builder(4)
            .setTitle("Never Split the Difference")
            .setAuthors(Arrays.asList(new String[] {"Chris Voss", "Tahl Raz"}))
            .setNumberOfPages(288)
            .build();

    Book book5 =
        new Book.Builder(5)
            .setTitle("Stop Overthinking")
            .setAuthors(Arrays.asList(new String[] {"Nick Trenton"}))
            .setNumberOfPages(200)
            .build();

    Book book6 =
        new Book.Builder(6)
            .setTitle("The Art Of Saying NO")
            .setAuthors(Arrays.asList(new String[] {"Damon Zahariades"}))
            .setNumberOfPages(170)
            .build();

    Book book7 =
        new Book.Builder(7)
            .setTitle("Digital Detox")
            .setAuthors(Arrays.asList(new String[] {"Daman Zahariades"}))
            .setNumberOfPages(202)
            .build();

    Book book8 =
        new Book.Builder(8)
            .setTitle("Get to the Point")
            .setAuthors(Arrays.asList(new String[] {"Ace Monroe"}))
            .setNumberOfPages(58)
            .build();

    Book book9 =
        new Book.Builder(9)
            .setTitle("80/20")
            .setAuthors(Arrays.asList(new String[] {"Richard Koch"}))
            .setNumberOfPages(336)
            .build();

    Book book10 =
        new Book.Builder(10)
            .setTitle("The 5AM Club")
            .setAuthors(Arrays.asList(new String[] {"Robin Sharma"}))
            .setNumberOfPages(336)
            .build();

    Book book11 =
        new Book.Builder(11)
            .setTitle("The Power of Habit")
            .setAuthors(Arrays.asList(new String[] {"Charles Duhigg"}))
            .setNumberOfPages(416)
            .build();

    books.add(book1);
    books.add(book2);
    books.add(book3);
    books.add(book4);
    books.add(book5);
    books.add(book6);
    books.add(book7);
    books.add(book8);
    books.add(book9);
    books.add(book10);
    books.add(book11);

    return books;
  }
}

class Book {

  private Integer id;
  private String title;
  private List<String> authors;
  private Integer numberOfPages;

  public Book(Builder builder) {

    this.id = builder.id;
    this.title = builder.title;
    this.authors = builder.authors;
    this.numberOfPages = builder.numberOfPages;
  }

  public Integer getId() {

    return id;
  }

  public String getTitle() {

    return title;
  }

  public List<String> getAuthors() {

    return authors;
  }

  public Integer getNumberOfPages() {

    return numberOfPages;
  }

  @Override
  public String toString() {

    return "{\"Book\" : {\"id\" : "
        + id
        + ", \"title\" : "
        + title
        + ", \"authors\" : "
        + authors
        + ", \"numberOfPages\" : "
        + numberOfPages
        + "}}";
  }

  public static class Builder {

    private Integer id;
    private String title;
    private List<String> authors;
    private Integer numberOfPages;

    public Builder(Integer id) {

      this.id = id;
    }

    public Builder setTitle(String title) {

      this.title = title;
      return this;
    }

    public Builder setAuthors(List<String> authors) {

      this.authors = authors;
      return this;
    }

    public Builder setNumberOfPages(Integer numberOfPages) {

      this.numberOfPages = numberOfPages;
      return this;
    }

    public Book build() {

      return new Book(this);
    }
  }
}
