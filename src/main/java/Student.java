public class Student extends Person {
    private String favoriteSubject;

    public String getFavoriteSubject() { return favoriteSubject; }
    public void setFavoriteSubject(String favoriteSubject) { this.favoriteSubject = favoriteSubject; }

    @Override
    public String getWeekendPlans() { return "wake up early and study all day"; }

    @Override
    public String toString() { return super.toString() + ", favoriteSubject=" + favoriteSubject ; }
}