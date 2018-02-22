package com.example.m_7el.logintesing.modules;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UserInformation implements Parcelable{

    @SerializedName("brand_private")

    private Boolean brandPrivate;
    @SerializedName("error")
    private String error;
    @SerializedName("created")
    private String created;
    @SerializedName("current_login_at")
    private String currentLoginAt;
    @SerializedName("current_login_ip")
    private String currentLoginIp;
    @SerializedName("email")
    private String email;
    @SerializedName("entry_mode")
    private EntryMode entryMode;
    @SerializedName("feature")
    private Boolean feature;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("id")
    private Integer id;
    @SerializedName("language")
    private String language;
   @SerializedName("last_login_at")
    private String lastLoginAt;
    @SerializedName("last_login_ip")
    private String lastLoginIp;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("login_count")
    private Integer loginCount;
    @SerializedName("phone")
    private String phone;
    @SerializedName("slug")
    private String slug;
    @SerializedName("social_login")
    private Boolean socialLogin;
    @SerializedName("team_user_id")

    private Integer teamUserId;
    @SerializedName("updated")
    private String updated;
    @SerializedName("user_candidate")
    private Boolean userCandidate;
    @SerializedName("user_employer")
    private Boolean userEmployer;
    @SerializedName("user_network")
    private Boolean userNetwork;

    protected UserInformation(Parcel in) {
        byte tmpBrandPrivate = in.readByte();
        brandPrivate = tmpBrandPrivate == 0 ? null : tmpBrandPrivate == 1;
        error = in.readString();
        created = in.readString();
        currentLoginAt = in.readString();
        currentLoginIp = in.readString();
        email = in.readString();
        byte tmpFeature = in.readByte();
        feature = tmpFeature == 0 ? null : tmpFeature == 1;
        firstName = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        language = in.readString();
        lastLoginAt = in.readString();
        lastLoginIp = in.readString();
        lastName = in.readString();
        if (in.readByte() == 0) {
            loginCount = null;
        } else {
            loginCount = in.readInt();
        }
        phone = in.readString();
        slug = in.readString();
        byte tmpSocialLogin = in.readByte();
        socialLogin = tmpSocialLogin == 0 ? null : tmpSocialLogin == 1;
        if (in.readByte() == 0) {
            teamUserId = null;
        } else {
            teamUserId = in.readInt();
        }
        updated = in.readString();
        byte tmpUserCandidate = in.readByte();
        userCandidate = tmpUserCandidate == 0 ? null : tmpUserCandidate == 1;
        byte tmpUserEmployer = in.readByte();
        userEmployer = tmpUserEmployer == 0 ? null : tmpUserEmployer == 1;
        byte tmpUserNetwork = in.readByte();
        userNetwork = tmpUserNetwork == 0 ? null : tmpUserNetwork == 1;
    }

    public static final Creator<UserInformation> CREATOR = new Creator<UserInformation>() {
        @Override
        public UserInformation createFromParcel(Parcel in) {
            return new UserInformation(in);
        }

        @Override
        public UserInformation[] newArray(int size) {
            return new UserInformation[size];
        }
    };

    public Boolean getBrandPrivate() {
        return brandPrivate;
    }

    public void setBrandPrivate(Boolean brandPrivate) {
        this.brandPrivate = brandPrivate;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCurrentLoginAt() {
        return currentLoginAt;
    }

    public void setCurrentLoginAt(String currentLoginAt) {
        this.currentLoginAt = currentLoginAt;
    }

    public String getCurrentLoginIp() {
        return currentLoginIp;
    }

    public void setCurrentLoginIp(String currentLoginIp) {
        this.currentLoginIp = currentLoginIp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EntryMode getEntryMode() {
        return entryMode;
    }

    public void setEntryMode(EntryMode entryMode) {
        this.entryMode = entryMode;
    }

    public Boolean getFeature() {
        return feature;
    }

    public void setFeature(Boolean feature) {
        this.feature = feature;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(String lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Boolean getSocialLogin() {
        return socialLogin;
    }

    public void setSocialLogin(Boolean socialLogin) {
        this.socialLogin = socialLogin;
    }

    public Integer getTeamUserId() {
        return teamUserId;
    }

    public void setTeamUserId(Integer teamUserId) {
        this.teamUserId = teamUserId;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Boolean getUserCandidate() {
        return userCandidate;
    }

    public void setUserCandidate(Boolean userCandidate) {
        this.userCandidate = userCandidate;
    }

    public Boolean getUserEmployer() {
        return userEmployer;
    }

    public void setUserEmployer(Boolean userEmployer) {
        this.userEmployer = userEmployer;
    }

    public Boolean getUserNetwork() {
        return userNetwork;
    }

    public void setUserNetwork(Boolean userNetwork) {
        this.userNetwork = userNetwork;
    }

    public String getError() {
        return error;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (brandPrivate == null ? 0 : brandPrivate ? 1 : 2));
        dest.writeString(error);
        dest.writeString(created);
        dest.writeString(currentLoginAt);
        dest.writeString(currentLoginIp);
        dest.writeString(email);
        dest.writeByte((byte) (feature == null ? 0 : feature ? 1 : 2));
        dest.writeString(firstName);
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(language);
        dest.writeString(lastLoginAt);
        dest.writeString(lastLoginIp);
        dest.writeString(lastName);
        if (loginCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(loginCount);
        }
        dest.writeString(phone);
        dest.writeString(slug);
        dest.writeByte((byte) (socialLogin == null ? 0 : socialLogin ? 1 : 2));
        if (teamUserId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(teamUserId);
        }
        dest.writeString(updated);
        dest.writeByte((byte) (userCandidate == null ? 0 : userCandidate ? 1 : 2));
        dest.writeByte((byte) (userEmployer == null ? 0 : userEmployer ? 1 : 2));
        dest.writeByte((byte) (userNetwork == null ? 0 : userNetwork ? 1 : 2));
    }
}
