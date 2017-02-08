package interview.alvarez.pedro.domain.business;

public class LikeDislike {

    private int id;
    private boolean liked;

    public LikeDislike(int id, boolean liked) {
        this.id = id;
        this.liked = liked;
    }

    public boolean isLiked() {
        return liked;
    }

    public int getId() {
        return id;
    }
}
