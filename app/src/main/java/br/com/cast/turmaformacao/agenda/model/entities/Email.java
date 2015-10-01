package br.com.cast.turmaformacao.agenda.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Email implements Parcelable {
    private Contact contact;
    private Long id;
    private String endereco_email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Email email = (Email) o;

        if (contact != null ? !contact.equals(email.contact) : email.contact != null) return false;
        if (id != null ? !id.equals(email.id) : email.id != null) return false;
        return !(endereco_email != null ? !endereco_email.equals(email.endereco_email) : email.endereco_email != null);

    }

    @Override
    public int hashCode() {
        int result = contact != null ? contact.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (endereco_email != null ? endereco_email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Email{" +
                "contact=" + contact +
                ", id=" + id +
                ", endereco_email='" + endereco_email + '\'' +
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

    public String getEndereco_email() {
        return endereco_email;
    }

    public void setEndereco_email(String endereco_email) {
        this.endereco_email = endereco_email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.contact, 0);
        dest.writeValue(this.id);
        dest.writeString(this.endereco_email);
    }

    public Email() {
    }

    protected Email(Parcel in) {
        this.contact = in.readParcelable(Contact.class.getClassLoader());
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.endereco_email = in.readString();
    }

    public static final Creator<Email> CREATOR = new Creator<Email>() {
        public Email createFromParcel(Parcel source) {
            return new Email(source);
        }

        public Email[] newArray(int size) {
            return new Email[size];
        }
    };
}
