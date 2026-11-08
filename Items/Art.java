package Items;
public class Art extends Item {
    String artist;
    int creationYear;

    public Art(String id, String name, String description, String SellerId, String artist, int creationYear) {
        super(id, name, description, SellerId);
        this.artist = artist;
        this.creationYear = creationYear;
    }

    public String getArtist() {
        return artist;
    }

    public int getCreationYear() {
        return creationYear;
    }

    @Override
    public String getInfo () {
        return super.getInfo() + "-----Art-----" + "\nArtist: "+ artist +"\nCreationYear: "+ creationYear;
    }
}