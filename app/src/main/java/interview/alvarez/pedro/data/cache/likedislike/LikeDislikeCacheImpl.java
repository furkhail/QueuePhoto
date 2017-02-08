package interview.alvarez.pedro.data.cache.likedislike;

import interview.alvarez.pedro.data.cache.DiskCache;
import interview.alvarez.pedro.domain.business.LikeDislike;
import io.reactivex.Observable;

public class LikeDislikeCacheImpl implements LikeDislikeCache{

    private static final String LIKES = "likes";
    private static final String DISLIKES = "dislikes";

    private DiskCache mDiskCache;

    public LikeDislikeCacheImpl(DiskCache diskCache) {
        mDiskCache = diskCache;
    }

    @Override
    public Observable<Integer> getLikes() {
        return Observable.just(mDiskCache.get(LIKES, Integer.class, 0));
    }

    @Override
    public Observable<Integer> getDislikes() {
        return Observable.just(mDiskCache.get(DISLIKES, Integer.class, 0));
    }

    @Override
    public Observable<Boolean> putLikeDislike(LikeDislike likeDislike) {
        try {
            if(likeDislike.isLiked()) {
                Integer likes = mDiskCache.get(LIKES, Integer.class, 0);
                mDiskCache.put(LIKES, ++likes);
            }
            else{
                Integer likes = mDiskCache.get(DISLIKES, Integer.class, 0);
                mDiskCache.put(DISLIKES, ++likes);
            }
            return Observable.just(true);
        }
        catch (Exception e){
            return Observable.just(false);
        }
    }
}
