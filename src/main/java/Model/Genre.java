package Model;

public enum Genre {//класс для жанра
    WAR("WAR"),
    HISTORICAL("HISTORICAL"),
    FANTASY("FANTASY"),
    SCIFI("SCIFI");

    public String getName() {
        return name;
    }

    private final String name;

    Genre(String name) {
        this.name = name;
    }
@Override
    public String toString(){
        return name;
}


}
