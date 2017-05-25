package com.fnm.ecodata.category;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Dev on 28/9/2016.
 */

public class Category extends RealmObject {

    @PrimaryKey
    String id;

    String startDate;
    String dateModified;
    String privacyListing;
    String weChatLink;
    String location;
    String endDate;
    String feeEarlybirdGroup;
    String endTime;
    String startTime;
    String title;
    String feeNormalGroup;
    String instagramLink;
    String linkedinLink;
    String feeStudent;
    String objective;
    String contactEmail;
    String organizer;
    String theme;
    String image;
    String feeGovernment;
    String facebookLink;
    String overview;
    String feeEarlybirdIndividual;
    String feeNormalIndividual;
    String twitterLink;
    String dateCreated;
    boolean isObsolete;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getPrivacyListing() {
        return privacyListing;
    }

    public void setPrivacyListing(String privacyListing) {
        this.privacyListing = privacyListing;
    }

    public String getWeChatLink() {
        return weChatLink;
    }

    public void setWeChatLink(String weChatLink) {
        this.weChatLink = weChatLink;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getFeeEarlybirdGroup() {
        return feeEarlybirdGroup;
    }

    public void setFeeEarlybirdGroup(String feeEarlybirdGroup) {
        this.feeEarlybirdGroup = feeEarlybirdGroup;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFeeNormalGroup() {
        return feeNormalGroup;
    }

    public void setFeeNormalGroup(String feeNormalGroup) {
        this.feeNormalGroup = feeNormalGroup;
    }

    public String getInstagramLink() {
        return instagramLink;
    }

    public void setInstagramLink(String instagramLink) {
        this.instagramLink = instagramLink;
    }

    public String getLinkedinLink() {
        return linkedinLink;
    }

    public void setLinkedinLink(String linkedinLink) {
        this.linkedinLink = linkedinLink;
    }

    public String getFeeStudent() {
        return feeStudent;
    }

    public void setFeeStudent(String feeStudent) {
        this.feeStudent = feeStudent;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFeeGovernment() {
        return feeGovernment;
    }

    public void setFeeGovernment(String feeGovernment) {
        this.feeGovernment = feeGovernment;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFeeEarlybirdIndividual() {
        return feeEarlybirdIndividual;
    }

    public void setFeeEarlybirdIndividual(String feeEarlybirdIndividual) {
        this.feeEarlybirdIndividual = feeEarlybirdIndividual;
    }

    public String getFeeNormalIndividual() {
        return feeNormalIndividual;
    }

    public void setFeeNormalIndividual(String feeNormalIndividual) {
        this.feeNormalIndividual = feeNormalIndividual;
    }

    public String getTwitterLink() {
        return twitterLink;
    }

    public void setTwitterLink(String twitterLink) {
        this.twitterLink = twitterLink;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isObsolete() {
        return isObsolete;
    }

    public void setObsolete(boolean obsolete) {
        isObsolete = obsolete;
    }

    public static Category getEventById(String id, Realm realm){
        return realm.where(Category.class).equalTo("id", id).findFirst();
    }

    public static boolean deleteAllObsoleteEvents(Realm realm){
        return realm.where(Category.class).equalTo("isObsolete", true).findAll().deleteAllFromRealm();
    }

    public static RealmResults getAllEvents(Realm realm){
        return realm.where(Category.class).findAll();
    }
}
