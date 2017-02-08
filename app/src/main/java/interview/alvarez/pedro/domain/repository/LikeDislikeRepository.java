package interview.alvarez.pedro.domain.repository;

import interview.alvarez.pedro.domain.business.LikeDislike;
import io.reactivex.Observable;

public interface LikeDislikeRepository {

    Observable<Integer> getLikes();

    Observable<Integer> getDislikes();

    Observable<Boolean> putLikeDislike(LikeDislike likeDislike);
}
