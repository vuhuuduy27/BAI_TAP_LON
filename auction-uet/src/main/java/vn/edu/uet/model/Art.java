package vn.edu.uet.model;

public class Art extends Item {
    private String artistName;
    private int creationYear;

    public Art(String name, String description, double startingPrice, String artistName, int creationYear) {
        super(name, description, startingPrice);
        this.artistName = artistName;
        this.creationYear = creationYear;
    }

    public String getArtistName() {
        return artistName;
    }

    public int getCreationYear() {
        return creationYear;
    }

    @Override
    public void printInfo() {
        System.out.println("[Nghệ thuật] " + name + " | Tác giả: " + artistName 
            + " (" + creationYear + ") | Giá khởi điểm: " + startingPrice + " VNĐ");
    }
}