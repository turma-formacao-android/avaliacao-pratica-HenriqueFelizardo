package br.com.cast.turmaformacao.agenda.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Social implements Parcelable {
    private Long id;
    private String user;
    private Contact contact;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Social social = (Social) o;

        if (contact != null ? !contact.equals(social.contact) : social.contact != null)
            return false;
        if (id != null ? !id.equals(social.id) : social.id != null) return false;
        return !(user != null ? !user.equals(social.user) : social.user != null);

    }

    @Override
    public int hashCode() {
        int result = contact != null ? contact.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Social{" +
                "contact=" + contact +
                ", id=" + id +
                ", user='" + user + '\'' +
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.contact, 0);
        dest.writeValue(this.id);
        dest.writeString(this.user);
    }

    public Social() {
    }

    protected Social(Parcel in) {
        this.contact = in.readParcelable(Contact.class.getClassLoader());
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.user = in.readString();
    }

    public static final Creator<Social> CREATOR = new Creator<Social>() {
        public Social createFromParcel(Parcel source) {
            return new Social(source);
        }

        public Social[] newArray(int size) {
            return new Social[size];
        }
    };
}
