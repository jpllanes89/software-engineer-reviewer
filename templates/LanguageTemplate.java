import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LanguageTemplate {

  public static void main(String[] args) {

    List<Optional<Language>> languageList = generateLanguages();

    // REPLACE_WITH_QUESTIONS
  }

  private static List<Optional<Language>> generateLanguages() {

    List<Optional<Language>> languageList = new ArrayList<>();

    Country america = new Country.CountryBuilder().setId("1").setName("America").build();
    Country china = new Country.CountryBuilder().setId("2").setName("China").build();
    Country india = new Country.CountryBuilder().setId("3").setName("India").build();

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
