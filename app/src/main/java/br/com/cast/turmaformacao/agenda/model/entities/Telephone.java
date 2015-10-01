package br.com.cast.turmaformacao.agenda.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Telephone implements Parcelable {
    private Contact contact;
    private Long id;
    private String number;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Telephone telephone = (Telephone) o;

        if (contact != null ? !contact.equals(telephone.contact) : telephone.contact != null)
            return false;
        if (id != null ? !id.equals(telephone.id) : telephone.id != null) return false;
        return !(number != null ? !number.equals(telephone.number) : telephone.number != null);

    }

    @Override
    public int hashCode() {
        int result = contact != null ? contact.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Telephone{" +
                "contact=" + contact +
                ", id=" + id +
                ", number='" + number + '\'' +
                '}';
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.contact, 0);
        dest.writeValue(this.id);
        dest.writeString(this.number);
    }

    public Telephone() {
    }

    protected Telephone(Parcel in) {
        this.contact = in.readParcelable(Contact.class.getClassLoader());
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.number = in.readString();
    }

    public static final Creator<Telephone> CREATOR = new Creator<Telephone>() {
        public Telephone createFromParcel(Parcel source) {
            return new Telephone(source);
        }

        public Telephone[] newArray(int size) {
            return new Telephone[size];
        }
    };
}
