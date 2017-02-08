package interview.alvarez.pedro.presentation.view.queuephotos;

import android.util.Log;

import javax.inject.Inject;

import interview.alvarez.pedro.R;
import interview.alvarez.pedro.domain.business.LikeDislike;
import interview.alvarez.pedro.domain.interactor.DefaultObserver;
import interview.alvarez.pedro.domain.interactor.likedislike.GetDislikesUseCase;
import interview.alvarez.pedro.domain.interactor.likedislike.GetLikesUseCase;
import interview.alvarez.pedro.domain.interactor.likedislike.LikeDislikePhotoUseCase;
import interview.alvarez.pedro.presentation.base.Presenter;

class QueuePresenter implements Presenter<QueuePhotoView> {

    private static final String TAG = "QueuePresenter";

    private static final String ENDPOINT = "https://source.unsplash.com/random/";

    private QueuePhotoView mQueuePhotoView;

    private GetDislikesUseCase mGetDislikesUseCase;
    private GetLikesUseCase mGetLikesUseCase;
    private LikeDislikePhotoUseCase mLikeDislikePhotoUseCase;

    @Inject
    QueuePresenter(GetDislikesUseCase getDislikesUseCase,
                   GetLikesUseCase getLikesUseCase,
                   LikeDislikePhotoUseCase likeDislikePhotoUseCase) {
        mGetDislikesUseCase = getDislikesUseCase;
        mGetLikesUseCase = getLikesUseCase;
        mLikeDislikePhotoUseCase = likeDislikePhotoUseCase;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        mQueuePhotoView = null;
    }

    @Override
    public void setView(QueuePhotoView view) {
        mQueuePhotoView = view;
    }

    @Override
    public void initialize() {
        mQueuePhotoView.showPhoto(ENDPOINT);
        mGetLikesUseCase.execute(new LikesObserver());
        mGetDislikesUseCase.execute(new DislikesObserver());
    }


    void likePhoto(int code) {
        mLikeDislikePhotoUseCase.execute(new BooleanObserver(), new LikeDislike(code, true));
    }

    void dislikePhoto(int code){
        mLikeDislikePhotoUseCase.execute(new BooleanObserver(), new LikeDislike(code, false));
    }

    private final class BooleanObserver extends DefaultObserver<Boolean>{
        @Override
        public void onError(Throwable exception) {
            super.onError(exception);
            Log.d(TAG, "onError() called with: exception = [" + exception + "]");
        }

        @Override
        public void onNext(Boolean success) {
            if(success) {
                initialize();
            }
            else{
                mQueuePhotoView.showError(mQueuePhotoView.context().getString(R.string.error_cache_disk));
            }
        }
    }

    private final class LikesObserver extends DefaultObserver<Integer>{
        @Override
        public void onError(Throwable exception) {
            super.onError(exception);
            Log.d(TAG, "onError() called with: exception = [" + exception + "]");
        }

        @Override
        public void onNext(Integer likeDislikes) {
            mQueuePhotoView.changeLabelLike(likeDislikes);
        }
    }

    private final class DislikesObserver extends DefaultObserver<Integer>{
        @Override
        public void onError(Throwable exception) {
            super.onError(exception);
            Log.d(TAG, "onError() called with: exception = [" + exception + "]");
        }

        @Override
        public void onNext(Integer likeDislikes) {
            mQueuePhotoView.changeLabelDislike(likeDislikes);
        }
    }
}
