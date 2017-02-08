package interview.alvarez.pedro.domain.interactor.likedislike;

import javax.inject.Inject;

import interview.alvarez.pedro.domain.executor.PostExecutionThread;
import interview.alvarez.pedro.domain.executor.ThreadExecutor;
import interview.alvarez.pedro.domain.interactor.UseCase;
import interview.alvarez.pedro.domain.repository.LikeDislikeRepository;
import io.reactivex.Observable;

public class GetDislikesUseCase extends UseCase<Integer,Void> {

    private final LikeDislikeRepository mLikeDislikeRepository;

    @Inject
    public GetDislikesUseCase(ThreadExecutor threadExecutor,
                              PostExecutionThread postExecutionThread,
                              LikeDislikeRepository likeDislikeRepository) {
        super(threadExecutor, postExecutionThread);
        mLikeDislikeRepository = likeDislikeRepository;
    }

    @Override
    public Observable<Integer> buildUseCaseObservable(Void aVoid) {
        return mLikeDislikeRepository.getDislikes();
    }
}
