public class Art extends Item{
    private String artist;
    private int creationYear;

    public Art(String name, long startingPrice, String sellerId, String artist, int creationYear){
        super(name, startingPrice, sellerId);
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
    public String getInfo() {
        return "Art: " + super.getInfo() + " - " + artist + " - " + creationYear;
    }
}
