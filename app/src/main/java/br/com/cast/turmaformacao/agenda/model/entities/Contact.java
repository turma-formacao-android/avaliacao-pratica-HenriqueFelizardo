package br.com.cast.turmaformacao.agenda.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class Contact implements Parcelable {

    private Long id;
    private String name;
    private Address address;
    private ArrayList<Email> emails;
    private ArrayList<Telephone> telephones;
    private ArrayList<Social> socials;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (id != null ? !id.equals(contact.id) : contact.id != null) return false;
        if (name != null ? !name.equals(contact.name) : contact.name != null) return false;
        if (address != null ? !address.equals(contact.address) : contact.address != null)
            return false;
        if (emails != null ? !emails.equals(contact.emails) : contact.emails != null) return false;
        if (telephones != null ? !telephones.equals(contact.telephones) : contact.telephones != null)
            return false;
        return !(socials != null ? !socials.equals(contact.socials) : contact.socials != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (emails != null ? emails.hashCode() : 0);
        result = 31 * result + (telephones != null ? telephones.hashCode() : 0);
        result = 31 * result + (socials != null ? socials.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", emails=" + emails +
                ", telephones=" + telephones +
                ", socials=" + socials +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ArrayList<Email> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<Email> emails) {
        this.emails = emails;
    }

    public ArrayList<Telephone> getTelephones() {
        return telephones;
    }

    public void setTelephones(ArrayList<Telephone> telephones) {
        this.telephones = telephones;
    }

    public ArrayList<Social> getSocials() {
        return socials;
    }

    public void setSocials(ArrayList<Social> socials) {
        this.socials = socials;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeParcelable(this.address, 0);
        dest.writeTypedList(emails);
        dest.writeTypedList(telephones);
        dest.writeTypedList(socials);
    }

    public Contact() {
        address = new Address();
    }

    protected Contact(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.address = in.readParcelable(Address.class.getClassLoader());
        this.emails = in.createTypedArrayList(Email.CREATOR);
        this.telephones = in.createTypedArrayList(Telephone.CREATOR);
        this.socials = in.createTypedArrayList(Social.CREATOR);
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
