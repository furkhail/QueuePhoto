package interview.alvarez.pedro.data.repository;

import javax.inject.Inject;

import interview.alvarez.pedro.data.cache.likedislike.LikeDislikeCache;
import interview.alvarez.pedro.domain.business.LikeDislike;
import interview.alvarez.pedro.domain.repository.LikeDislikeRepository;
import io.reactivex.Observable;

public class LikeDislikeDataRepository implements LikeDislikeRepository{

    private LikeDislikeCache mLikeDislikeCache;

    @Inject
    public LikeDislikeDataRepository(LikeDislikeCache likeDislikeCache){
        mLikeDislikeCache = likeDislikeCache;
    }

    @Override
    public Observable<Integer> getLikes() {
        return mLikeDislikeCache.getLikes();
    }

    @Override
    public Observable<Integer> getDislikes() {
        return mLikeDislikeCache.getDislikes();
    }

    @Override
    public Observable<Boolean> putLikeDislike(LikeDislike likeDislike) {
        return mLikeDislikeCache.putLikeDislike(likeDislike);

    }
}
