package model;
import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String fullname, email, password, confirm_password, homepage, about, image;

    public User(String fullname, String email, String password, String confirm_password, String homepage, String about) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.confirm_password = confirm_password;
        this.homepage = homepage;
        this.about = about;

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    protected User(Parcel in) {
        fullname = in.readString();
        email = in.readString();
        password = in.readString();
        confirm_password = in.readString();
        homepage = in.readString();
        about = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fullname);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(confirm_password);
        dest.writeString(homepage);
        dest.writeString(about);
    }
}
