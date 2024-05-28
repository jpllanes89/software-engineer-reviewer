import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.function.BiPredicate;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.Collection;
import java.util.Optional;
import java.util.Random;


public class BooksTemplateTemp {

  public static void main(String[] args) {

    List<Book> books = generateBooks();

    // REPLACE_WITH_QUESTIONS
    // [1-1] Create an appendTitle UnaryOperator that will add "TITLE: " to a string. For each title of book, apply the UnaryOperator and print the output: TITLE: <title>
    UnaryOperator<String> appendTitle = s -> "TITLE: " + s;
    books.stream().forEach(b -> System.out.println(appendTitle.apply(b.getTitle())));

    // [1-2] Create an appendCategory UnaryOperator that will add "CATEGORY: " to a string. For each category of book, apply the UnaryOperator and print the output: CATEGORY: <category>
    UnaryOperator<String> appendCategory = s -> "CATEGORY: " + s;
    books.stream().forEach(b -> System.out.println(appendCategory.apply(b.getCategory())));

    // [1-3] Create an appendAuthor UnaryOperator that will add "AUTHOR: " to a string. For each author of book, apply the UnaryOperator and print the output: AUTHOR: <author>
    UnaryOperator<String> appendAuthor = s -> "AUTHOR: " + s;
    books.stream().map(Book::getAuthors).collect(Collectors.toList()).stream().flatMap(Collection::stream).forEach(a -> System.out.println(appendAuthor.apply(a)));

    // [2-1] Create a createHeaderByTitleAndGenre BinaryOperator that will take the title and genre as parameter and will return "<title>: <genre>"
    BinaryOperator<String> createHeaderByTitleAndGenre = (t, g) -> t + ": " + g;
    books.stream().forEach(b -> System.out.println(createHeaderByTitleAndGenre.apply(b.getTitle(), b.getGenre())));

    // [2-2] Create a createHeaderByTitleAndCategory BinaryOperator that will take the title and category as parameter and will return "<title>: <category>" 
    BinaryOperator<String> createHeaderByTitleAndCategory = (t, c) -> t + ": " + c;
    books.stream().forEach(b -> System.out.println(createHeaderByTitleAndCategory.apply(b.getTitle(), b.getCategory())));
    
    // [3-1] Create a toPhpPrice Function that will take price as parameter and will return a String "Php <price>"
    Function<Double, String> toPhpPrice = p -> "Php " + String.valueOf(p);
    books.stream().forEach(b -> System.out.println(toPhpPrice.apply(b.getPrice())));

    // [3-2] Create a toNumberOfPages Function that will take numberOfpages parameter an will return a String "No. of Pages: <numberOfPages>"
    Function<Integer, String> toNumberOfPages = n -> "No. of Pages: " + String.valueOf(n); 
    books.stream().forEach(b -> System.out.println(toNumberOfPages.apply(b.getNumberOfPages())));

    // [4-1] Create a createHeaderByTitleAndPages that will take title and numberOfPages as parameters and will return a String "<title>: <numberOfPages>"
    BiFunction<String, Integer, String> createHeaderByTitleAndPages = (t, n) -> t + ": " + n; 
    books.stream().forEach(b -> System.out.println(createHeaderByTitleAndPages.apply(b.getTitle(), b.getNumberOfPages())));

    // [4-2] Create a createHeaderByTitleAndPrice that will take title an price as parameters and will return a String "<title>: <price>"
    BiFunction<String, Double, String> createHeaderByTitleAndPrice = (t, p) -> t + ": " + p;
    books.stream().forEach(b -> System.out.println(createHeaderByTitleAndPrice.apply(b.getTitle(), b.getPrice())));

    // [5-1] Create two UnaryOperators. The first will be an encapsulateTitle which accepts a title parameter and returns "[<title>]". The Second will be an createFormattedTitle which accepts the result of the UnaryOperator then returns "TITLE: [<title>]"
    UnaryOperator<String> encapsulateTitle = t -> "[" + t + "]";
    UnaryOperator<String> createFormattedTitle = t -> "TITLE: " + t;
    books.stream().forEach(b -> System.out.println(encapsulateTitle.andThen(createFormattedTitle).apply(b.getTitle())));

    // [5-2] Create two UnaryOperators. The first will be an encapsulateTitle which accepts the result of the second UnaryOperator then returns "[TITLE: <title>]". The Second will be an createFormattedTitle which accepts a title parameter and returns "TITLE: <title>"
    UnaryOperator<String> encapsulateTitle2 = t -> "[" + t + "]";
    UnaryOperator<String> createFormattedTitle2 = t -> "TITLE: " + t;
    books.stream().forEach(b -> System.out.println(encapsulateTitle2.compose(createFormattedTitle2).apply(b.getTitle())));

    // [6-1] Create a printTitle Consumer which accepts a title parameter and prints "Title=<title>"
    Consumer<String> printTitle = t -> System.out.println("Title=" + t);
    books.stream().forEach(b -> printTitle.accept(b.getTitle()));
    
    // [6-2] Create a printCategory Consumer which accepts a category parameter and prints "Category=<category>"
    Consumer<String> printCategory = c -> System.out.println("Category=" + c);
    books.stream().forEach(b -> printCategory.accept(b.getCategory()));

    // [6-3] Create a printAuthor Consumer which accepts a author parameter and prints "Author=<author>"
    Consumer<String> printAuthor = a -> System.out.println("Author=" + a);
    books.stream().map(Book::getAuthors).collect(Collectors.toList()).stream().flatMap(Collection::stream).collect(Collectors.toList()).forEach(a -> printAuthor.accept(a));

    // [7-1] Create two Consumers. The first will be a printGenre which accepts a genre and prints "Genre=<genre>". The second will be a printFormattedGenre which accepts the result of the first genre and prints "[<genre>]" 
    Consumer<String> printGenre = g -> System.out.println("Genre=" + g);
    Consumer<String> printFormattedGenre = g -> System.out.println("[" + g + "]" );
    books.stream().forEach(b -> printGenre.andThen(printFormattedGenre).accept(b.getGenre()));
    
    // [8-1] Create a generateGreetings which return a String "Hello, I am a Supplier" and print it.
    Supplier<String> generateGreetings = () -> "Hello, I am a Supplier";
    System.out.println(generateGreetings.get()); 

    // [8-2] Create a generateRandomPageNumber which returns an Integer <random-number> and print it.
    Random random = new Random();
    Supplier generateRandomPageNumber = () -> random.nextInt();
    System.out.println(generateRandomPageNumber.get());

    // [9-1] Create an isEvenNumber which accepts an Integer and returns true if the number is even.
    IntPredicate isEvenNumber = n -> n % 2 == 0;
    System.out.println(isEvenNumber.test(2));

    // [9-2] Create an isOddNumber which accepts an Integer and returns true if the number id odd.
    IntPredicate isOddNumber = n -> n % 2 != 0;
    System.out.println(isOddNumber.test(3));

    // [10-1] Create an doesBookContainEvenNumberOfPages which accepts a Book and returns true if the number of pages is even.
    Predicate<Book> doesBookContainEvenNumberOfPages = b -> b.getNumberOfPages() % 2 == 0;
    books.stream().forEach(b -> System.out.println(String.format("book has even number of pages: %s", doesBookContainEvenNumberOfPages.test(b))));

    // [10-1] Create an doesBookContainOddNumberOfPages which accepts a Book and returns true if the number of pages is odd.
    Predicate<Book> doesBookContainOddNumberOfPages = b -> b.getNumberOfPages() % 2 != 0;
    books.stream().forEach(b -> System.out.println(String.format("book has odd number of pages: %s", doesBookContainOddNumberOfPages.test(b))));

    // [11-1] Create a hasTheSameCategory which accepts two books and returns true if both books has same category
    BiPredicate<Book, Book> hasTheSameCategory = (b1, b2) -> b1.getCategory().equalsIgnoreCase(b2.getCategory());
    System.out.println(String.format("%s and %s has the same category: %s", books.get(0).getTitle(), books.get(2).getTitle(), hasTheSameCategory.test(books.get(0), books.get(2))));
    
    // [12-1] Create a Functional Interface Random10Generator which has a generateRandom10 method which accepts no parameter and return a random number from 1 - 10
    Random rnd = new Random();
    Random10Generator random10Generator = () -> rnd.nextInt(11);
    System.out.println(random10Generator.generateRandom10());

    // [12-2] Create a Functional Interface name Greeting which has a greet method which accepts a String parameter and return a String "Hello <parameter>"
    Greeting greeting = (s) -> "Hello " + s;
    System.out.println(greeting.greet("Banana"));
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
            .setNumberOfPages(289)
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
            .setNumberOfPages(205)
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
            .setNumberOfPages(175)
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

class Person{

  private Optional<Banana> banana;

  public void setBanana(Optional<Banana> banana){

    this.banana = banana;
  }

  public Optional<Banana> getBanana(){

    return this.banana;
  }

  @Override
  public String toString(){

    return "{Person : { \"banana\" : "+banana+"}}";
  }

}

class Banana{

  private String name;

  public void setName(String name){

    this.name = name;
  }
  
  public String getName(){

    return this.name;
  }

  @Override
  public String toString(){

    return "{Banana : {\"name\" : "+name+"}}";
  }
}

@FunctionalInterface
public interface Random10Generator{

  public Integer generateRandom10();
}


@FunctionalInterface
public interface Greeting{

  public String greet(String s);
}

