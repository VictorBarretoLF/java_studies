package com.course.kafka.broker.message;

public class FeedbackRatingOneMessage {

    private String location;

    private double averageRating;

    public FeedbackRatingOneMessage(String location, double averageRating) {
        this.location = location;
        this.averageRating = averageRating;
    }

    public FeedbackRatingOneMessage() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public String toString() {
        return "FeedbackRatingOneMessage{" +
                "location='" + location + '\'' +
                ", averageRating=" + averageRating +
                '}';
    }

}
