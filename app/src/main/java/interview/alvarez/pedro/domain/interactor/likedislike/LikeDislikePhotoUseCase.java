package interview.alvarez.pedro.domain.interactor.likedislike;

import javax.inject.Inject;

import interview.alvarez.pedro.domain.business.LikeDislike;
import interview.alvarez.pedro.domain.executor.PostExecutionThread;
import interview.alvarez.pedro.domain.executor.ThreadExecutor;
import interview.alvarez.pedro.domain.interactor.UseCase;
import interview.alvarez.pedro.domain.repository.LikeDislikeRepository;
import io.reactivex.Observable;

public class LikeDislikePhotoUseCase extends UseCase<Boolean, LikeDislike> {

    private final LikeDislikeRepository mLikeDislikeRepository;

    @Inject
    public LikeDislikePhotoUseCase(ThreadExecutor threadExecutor,
                                   PostExecutionThread postExecutionThread,
                                   LikeDislikeRepository likeDislikeRepository) {
        super(threadExecutor, postExecutionThread);
        mLikeDislikeRepository = likeDislikeRepository;
    }

    @Override
    public Observable<Boolean> buildUseCaseObservable(LikeDislike likeDislike) {
        return mLikeDislikeRepository.putLikeDislike(likeDislike);
    }
}
