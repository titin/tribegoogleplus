package testtribehired.titinkurniat.com.testtribehired;

/**
 * Created by Titin Kurniati on 23-May-16.
 */
public class AreaModel {
    private String area;
    private String city;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public AreaModel(String area, String city){
        this.setArea(area);
        this.setCity(city);

    }

}
