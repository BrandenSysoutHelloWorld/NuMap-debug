package za.edu.st10112216.numap.classes;

public class PlaceDetailsClass {
    String name;
    String userId;
    String openCheck;
    double ratings;
    String rating;
    String cellphone;
    String website;

    public PlaceDetailsClass() {

    }

    public PlaceDetailsClass(String userId, String name) {
        this.name = name;
        this.userId = userId;
    }

    public PlaceDetailsClass(String name, String openCheck, double ratings, String cellphone, String website) {
        this.name = name;
        this.openCheck = openCheck;
        this.ratings = ratings;
        this.cellphone = cellphone;
        this.website = website;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenCheck() {
        return openCheck;
    }

    public void setOpenCheck(String openCheck) {
        this.openCheck = openCheck;
    }

    public double getRatings() {

        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {

        if (ratings == -1){
            rating = "Not Reviewed Yet";
        }
        return  "Name: " + name + '\n' +
                "Currently: " + openCheck + '\n' +
                "Ratings: " + ratings + '\n' +
                "Cellphone: " + cellphone + '\n' +
                "Website: "  + website + '\n';

    }
}
