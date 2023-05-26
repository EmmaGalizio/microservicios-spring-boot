package emma.galzio.moviecatalogservice.restView.model;

public class CatalogItem {

    private int rating;
    private String name;
    private String desc;

    public CatalogItem() {
    }

    public CatalogItem(int rating, String name, String desc) {
        this.rating = rating;
        this.name = name;
        this.desc = desc;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
