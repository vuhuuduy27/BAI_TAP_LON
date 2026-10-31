public class ArtFactory extends ItemFactory {
    private String artist;
    private int creationYear;
    public ArtFactory(String artist, int creationYear){
        this.artist = artist;
        this.creationYear = creationYear;
    }

    @Override
    public Item createItem(String name, long startingPrice, String sellerId) {
        return new Art(name, startingPrice, sellerId, artist, creationYear);
    }
}
