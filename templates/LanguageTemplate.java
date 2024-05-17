import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LanguageTemplate {

  public static void main(String[] args) {

    List<Optional<Language>> languageList = generateLanguages();
    System.out.println(languageList);

    // 1-1=Create an Optional<Language> object for Spanish Language: id = 5, name = "Spanish",
    // numberOfSpeakers = 548000000

    // 1-2=Create an Optional<Language> object for French Language: id = 5, name = "French",
    // numberOfSpeakers = 280000000

    // 2-1=Using stream, print out the language with values from languageList
    languageList.stream().forEach(n -> n.ifPresent(System.out::println));

    // 3-1=Create a new list of languages and copy the values from the pre-generated languageList.
    // replace the null language with Russian Language: id = 6, name = "Russian", numberOfSpeaker =
    // 258200000. You can use Java 9's ifPresentOrElse

    // 3-2=Create a new list of languages and copy the values from the pre-generated languageList.
    // replace the null language with Russian Language: id = 6, name = "Portuguese", numberOfSpeaker
    // =
    // 257000000. You can use Java 8's orElse or Java 9's ifPresentOrElse
    List<Optional<Language>> languageList2 = new ArrayList<>();
    languageList.stream()
        .forEach(
            (n) -> {
              n.ifPresentOrElse(
                  (o) -> languageList2.add(n),
                  () ->
                      languageList2.add(
                          Optional.of(
                              new Language.LanguageBuilder()
                                  .setId("5")
                                  .setName("Russian")
                                  .setNumberOfSpeakers(258200000)
                                  .build())));
            });
    System.out.println(languageList2);

    languageList.stream()
        .forEach(
            (n) -> {
              Language newLang = n.orElse(new Language.LanguageBuilder().setId("6").build());
              languageList2.add(Optional.of(newLang));
            });

    System.out.println(languageList2);
    // 4-1=Iterate on the languageList and when encountering a null language, throw
    // IllegalStateException
    /*
    languageList.stream()
        .forEach(
            (n) -> {
              n.orElseThrow(IllegalStateException::new);
            });
            */
    // 5-1=Iterate on the languageList and print out languages with more than 700,000,000 speakers.
    // Message printed should be: <language-name> has more than 700,000,000 speakers
    languageList.stream()
        .forEach(
            (n) -> {
              n.filter(lang -> 700000000 < lang.getNumberOfSpeakers())
                  .ifPresent(
                      o -> System.out.println(o.getName() + " has more than 700,000,000 speakers"));
            });

    // 5-1=Iterate on the languageList and print out languages with less than 1,000,000,000 speakers.
    // Message printed should be: <language-name> has more than 700,000,000 speakers

  }

  private static List<Optional<Language>> generateLanguages() {

    List<Optional<Language>> languageList = new ArrayList<>();

    Country america = new Country.CountryBuilder().setId("1").setName("America").build();
    Country china = new Country.CountryBuilder().setId("2").setName("China").build();
    Country india = new Country.CountryBuilder().setId("3").setName("India").build();
    Country bangladesh = new Country.CountryBuilder().setId("4").setName("Bangladesh").build();

    languageList.add(
        Optional.of(
            new Language.LanguageBuilder()
                .setId("1")
                .setName("English")
                .setNumberOfSpeakers(1400000000)
                .setCountry(america)
                .build()));
    languageList.add(
        Optional.of(
            new Language.LanguageBuilder()
                .setId("2")
                .setName("Manadarin")
                .setNumberOfSpeakers(1100000000)
                .setCountry(china)
                .build()));
    languageList.add(
        Optional.of(
            new Language.LanguageBuilder()
                .setId("3")
                .setName("Hindi")
                .setNumberOfSpeakers(602000000)
                .setCountry(india)
                .build()));
    languageList.add(
        Optional.of(
            new Language.LanguageBuilder()
                .setId("4")
                .setName("Bengali")
                .setNumberOfSpeakers(272700000)
                .setCountry(bangladesh)
                .build()));

    languageList.add(Optional.ofNullable(null));

    return languageList;
  }
}

class Country {

  private String id;
  private String name;

  public Country(CountryBuilder builder) {

    this.id = builder.id;
    this.name = builder.name;
  }

  public String id() {

    return this.id;
  }

  public String name() {

    return this.name;
  }

  @Override
  public String toString() {

    return "{Country : { \"id\" : " + id + ", \"name\" : " + name + "}}";
  }

  public static class CountryBuilder {

    private String id;
    private String name;

    public CountryBuilder setId(String id) {

      this.id = id;
      return this;
    }

    public CountryBuilder setName(String name) {

      this.name = name;
      return this;
    }

    public Country build() {

      return new Country(this);
    }
  }
}

class Language {

  private String id;
  private String name;
  private Integer numberOfSpeakers;
  private Country country;

  public Language(LanguageBuilder builder) {

    this.id = builder.id;
    this.name = builder.name;
    this.numberOfSpeakers = builder.numberOfSpeakers;
    this.country = builder.country;
  }

  public String getId() {

    return this.id;
  }

  public String getName() {

    return this.name;
  }

  public Integer getNumberOfSpeakers() {

    return this.numberOfSpeakers;
  }

  public Country getCountry() {

    return this.country;
  }

  @Override
  public String toString() {

    return "{Language : { \"id\" : "
        + id
        + ", \"name\" : "
        + name
        + ", \"numberOfSpeakers\" : "
        + numberOfSpeakers
        + ", \"country\" : "
        + country
        + " }}";
  }

  public static class LanguageBuilder {

    private String id;
    private String name;
    private Integer numberOfSpeakers;
    private Country country;

    public LanguageBuilder setId(String id) {

      this.id = id;
      return this;
    }

    public LanguageBuilder setName(String name) {

      this.name = name;
      return this;
    }

    public LanguageBuilder setNumberOfSpeakers(Integer numberOfSpeakers) {

      this.numberOfSpeakers = numberOfSpeakers;
      return this;
    }

    public LanguageBuilder setCountry(Country country) {

      this.country = country;
      return this;
    }

    public Language build() {

      return new Language(this);
    }
  }
}
