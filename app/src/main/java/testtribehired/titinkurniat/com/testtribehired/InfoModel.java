package testtribehired.titinkurniat.com.testtribehired;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Titin Kurniati on 22-May-16.
 */
public class InfoModel implements Parcelable{
    private int id;
    private String name;
    private String speciality;
    private String area;
    private String currency;
    private int rate;
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    protected InfoModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        speciality = in.readString();
        area = in.readString();
        currency = in.readString();
        rate = in.readInt();
        photo = in.readString();
    }

    public static final Creator<InfoModel> CREATOR = new Creator<InfoModel>() {
        @Override
        public InfoModel createFromParcel(Parcel in) {
            return new InfoModel(in);
        }

        @Override
        public InfoModel[] newArray(int size) {
            return new InfoModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(speciality);
        dest.writeString(area);
        dest.writeString(currency);
        dest.writeInt(rate);
        dest.writeString(photo);
    }

    @Override
    public String toString() {
        return "InfoModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", speciality='" + speciality + '\'' +
                ", area='" + area + '\'' +
                ", currency='" + currency + '\'' +
                ", rate=" + rate +
                ", photo='" + photo + '\'' +
                '}';
    }


}
