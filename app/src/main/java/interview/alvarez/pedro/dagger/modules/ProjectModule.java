package interview.alvarez.pedro.dagger.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import interview.alvarez.pedro.data.cache.DiskCache;
import interview.alvarez.pedro.data.cache.likedislike.LikeDislikeCache;
import interview.alvarez.pedro.data.cache.likedislike.LikeDislikeCacheImpl;
import interview.alvarez.pedro.data.repository.LikeDislikeDataRepository;
import interview.alvarez.pedro.domain.repository.LikeDislikeRepository;

@Module
public class ProjectModule {

    @Provides
    @Singleton
    LikeDislikeCache provideLikeDislikeCache(DiskCache diskCache){
        return new LikeDislikeCacheImpl(diskCache);
    }

    @Provides
    @Singleton
    LikeDislikeRepository provideLikeDislikeRepository(LikeDislikeCache likeDislikeCache){
        return new LikeDislikeDataRepository(likeDislikeCache);
    }
}
