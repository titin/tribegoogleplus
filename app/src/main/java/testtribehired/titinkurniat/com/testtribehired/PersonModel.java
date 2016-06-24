package testtribehired.titinkurniat.com.testtribehired;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Titin Kurniati on 23-May-16.
 */
public class PersonModel implements Parcelable {
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(int recommendation) {
        this.recommendation = recommendation;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitute() {
        return longitute;
    }

    public void setLongitute(double longitute) {
        this.longitute = longitute;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private int id;
    private String name;
    private String speciality;
    private String area;
    private String currency;
    private int rate;
    private String photo;
    private int recommendation;
    private String schedule;
    private int experience;
    private double latitude;
    private double longitute;
    private String description;

    protected PersonModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        speciality = in.readString();
        area = in.readString();
        currency = in.readString();
        rate = in.readInt();
        photo = in.readString();
        recommendation = in.readInt();
        schedule = in.readString();
        experience = in.readInt();
        latitude = in.readDouble();
        longitute = in.readDouble();
        description = in.readString();
    }

    public static final Creator<PersonModel> CREATOR = new Creator<PersonModel>() {
        @Override
        public PersonModel createFromParcel(Parcel in) {
            return new PersonModel(in);
        }

        @Override
        public PersonModel[] newArray(int size) {
            return new PersonModel[size];
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
        dest.writeInt(recommendation);
        dest.writeString(schedule);
        dest.writeInt(experience);
        dest.writeDouble(latitude);
        dest.writeDouble(longitute);
        dest.writeString(description);
    }

    @Override
    public String toString() {
        return "PersonModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", speciality='" + speciality + '\'' +
                ", area='" + area + '\'' +
                ", currency='" + currency + '\'' +
                ", rate=" + rate +
                ", photo='" + photo + '\'' +
                ", recommendation=" + recommendation +
                ", schedule='" + schedule + '\'' +
                ", experience=" + experience +
                ", latitude=" + latitude +
                ", longitute=" + longitute +
                ", description='" + description + '\'' +
                '}';
    }

}
