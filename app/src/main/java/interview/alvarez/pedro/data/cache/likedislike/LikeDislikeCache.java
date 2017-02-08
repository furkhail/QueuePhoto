package interview.alvarez.pedro.data.cache.likedislike;

import interview.alvarez.pedro.domain.business.LikeDislike;
import io.reactivex.Observable;

public interface LikeDislikeCache {

    Observable<Integer> getLikes();

    Observable<Integer> getDislikes();

    Observable<Boolean> putLikeDislike(LikeDislike likeDislike);
}
