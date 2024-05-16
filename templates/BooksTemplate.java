import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BooksTemplate {

  public static void main(String[] args) {

    List<Book> books = generateBooks();

    // REPLACE_WITH_QUESTIONS
  }

  private static List<Book> generateBooks() {

    List<Book> books = new ArrayList<>();

    Book book1 =
        new Book.Builder(1)
            .setTitle("The Molecule of More")
            .setAuthors(Arrays.asList(new String[] {"Daniel Z. Lieberman", "Michael E. Long"}))
            .setNumberOfPages(240)
            .setPrice(14.50)
            .setIsAvailable(true)
            .setCategory("Hardcover")
            .setReviewStars(4)
            .setGenre("Social Science")
            .build();

    Book book2 =
        new Book.Builder(2)
            .setTitle("Atomic Habits")
            .setAuthors(Arrays.asList(new String[] {"James Clear"}))
            .setNumberOfPages(320)
            .setPrice(13.79)
            .setCategory("Audiobook")
            .setIsAvailable(true)
            .setReviewStars(5)
            .setGenre("Self-Help")
            .build();

    Book book3 =
        new Book.Builder(3)
            .setTitle("Thinking, Fast and Slow")
            .setAuthors(Arrays.asList(new String[] {"Daniel Kahneman"}))
            .setPrice(8.95)
            .setNumberOfPages(512)
            .setIsAvailable(false)
            .setCategory("Hardcover")
            .setReviewStars(4)
            .setGenre("Self-Help")
            .build();

    Book book4 =
        new Book.Builder(4)
            .setTitle("Never Split the Difference")
            .setAuthors(Arrays.asList(new String[] {"Chris Voss", "Tahl Raz"}))
            .setNumberOfPages(288)
            .setPrice(24.49)
            .setIsAvailable(true)
            .setCategory("Hardcover")
            .setReviewStars(4)
            .setGenre("Social Science")
            .build();

    Book book5 =
        new Book.Builder(5)
            .setTitle("Stop Overthinking")
            .setAuthors(Arrays.asList(new String[] {"Nick Trenton"}))
            .setNumberOfPages(200)
            .setPrice(10.99)
            .setCategory("Digital")
            .setIsAvailable(false)
            .setReviewStars(4)
            .setGenre("Self-Help")
            .build();

    Book book6 =
        new Book.Builder(6)
            .setTitle("The Art Of Saying NO")
            .setAuthors(Arrays.asList(new String[] {"Damon Zahariades"}))
            .setNumberOfPages(170)
            .setPrice(9.99)
            .setIsAvailable(false)
            .setCategory("Hardcover")
            .setReviewStars(4)
            .setGenre("Self-Help")
            .build();

    Book book7 =
        new Book.Builder(7)
            .setTitle("The Digital Fast")
            .setAuthors(Arrays.asList(new String[] {"Darren Whitehead", "Henry Cloud"}))
            .setNumberOfPages(220)
            .setPrice(19.92)
            .setCategory("Audiobook")
            .setIsAvailable(true)
            .setReviewStars(5)
            .setGenre("Digital World")
            .build();

    Book book8 =
        new Book.Builder(8)
            .setTitle("Get to the Point")
            .setAuthors(Arrays.asList(new String[] {"Ace Monroe"}))
            .setNumberOfPages(58)
            .setPrice(16.74)
            .setIsAvailable(true)
            .setCategory("Digital")
            .setReviewStars(4)
            .setGenre("Social Science")
            .build();

    Book book9 =
        new Book.Builder(9)
            .setTitle("80/20 principle")
            .setAuthors(Arrays.asList(new String[] {"Richard Koch"}))
            .setNumberOfPages(336)
            .setPrice(11.89)
            .setIsAvailable(false)
            .setCategory("Hardcover")
            .setReviewStars(4)
            .setGenre("Social Science")
            .build();

    Book book10 =
        new Book.Builder(10)
            .setTitle("The 5AM Club")
            .setAuthors(Arrays.asList(new String[] {"Robin Sharma"}))
            .setNumberOfPages(336)
            .setPrice(12.99)
            .setCategory("Audiobook")
            .setIsAvailable(true)
            .setReviewStars(4)
            .setGenre("Social Science")
            .build();

    Book book11 =
        new Book.Builder(11)
            .setTitle("The Power of Habit")
            .setAuthors(Arrays.asList(new String[] {"Charles Duhigg"}))
            .setNumberOfPages(416)
            .setPrice(10.00)
            .setIsAvailable(true)
            .setCategory("Hardcover")
            .setReviewStars(4)
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
  private Double price;
  private Boolean isAvailable;
  private String category;
  private Integer reviewStars;
  private String genre;

  public Book(Builder builder) {

    this.id = builder.id;
    this.title = builder.title;
    this.authors = builder.authors;
    this.numberOfPages = builder.numberOfPages;
    this.isAvailable = builder.isAvailable;
    this.price = builder.price;
    this.category = builder.category;
    this.reviewStars = builder.reviewStars;
    this.genre = builder.genre;
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

  public Boolean isAvailable() {

    return isAvailable;
  }

  public Double getPrice() {

    return price;
  }

  public String getCategory() {

    return category;
  }

  public Integer getReviewStars() {

    return reviewStars;
  }

  public String getGenre() {

    return genre;
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
        + ", \"isAvailable\" : "
        + isAvailable
        + ", \"price\" : "
        + price
        + ", \"category\" : "
        + category
        + ", \"reviewStars\" : "
        + reviewStars
        + ", \"genre\" : "
        + genre
        + "}}";
  }

  public static class Builder {

    private Integer id;
    private String title;
    private List<String> authors;
    private Integer numberOfPages;
    private Boolean isAvailable;
    private Double price;
    private String category;
    private Integer reviewStars;
    private String genre;

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

    public Builder setIsAvailable(Boolean setIsAvailable) {

      this.isAvailable = isAvailable;
      return this;
    }

    public Builder setPrice(Double price) {

      this.price = price;
      return this;
    }

    public Builder setCategory(String category) {

      this.category = category;
      return this;
    }

    public Builder setReviewStars(Integer reviewStars) {

      this.reviewStars = reviewStars;
      return this;
    }

    public Builder setGenre(String genre) {

      this.genre = genre;
      return this;
    }

    public Book build() {

      return new Book(this);
    }
  }
}
