package interview.alvarez.pedro.presentation.view.queuephotos;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import interview.alvarez.pedro.MyApp;
import interview.alvarez.pedro.R;
import interview.alvarez.pedro.dagger.components.ApplicationComponent;
import interview.alvarez.pedro.presentation.base.BaseFragment;

public class QueuePhotosFragment extends BaseFragment implements QueuePhotoView{

    private static final String TAG = "QueuePhotosFragment";

    @BindView(R.id.photo_container)
    ImageView mContainer;

    @BindView(R.id.counter_dislike)
    TextView counterDislike;

    @BindView(R.id.counter_like)
    TextView counterLike;

    @BindView(R.id.icon_dislike)
    ImageButton iconDislike;

    @BindView(R.id.icon_like)
    ImageButton iconLike;

    @Inject
    QueuePresenter mQueuePresenter;

    Unbinder mUnbinder;

    private int mHeight;
    private int mWidth;

    public static QueuePhotosFragment newInstance() {
        Bundle args = new Bundle();
        QueuePhotosFragment fragment = new QueuePhotosFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectComponent(MyApp.get(getActivity()).getApplicationComponent());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (view != null) {
            mUnbinder = ButterKnife.bind(this, view);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mQueuePresenter.setView(this);
        setWidthHeight();
        mQueuePresenter.initialize();
    }

    private void setWidthHeight() {
        Display display = getDisplay();
        Point size = new Point();
        display.getSize(size);
        mWidth = size.x;
        mHeight = size.y;
        Log.i(TAG, "setWidthHeight: "+mWidth+" | "+mHeight);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.photo_container;
    }

    @Override
    protected void injectComponent(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context context() {
        return getContext();
    }

    private Display getDisplay(){
        WindowManager wm = (WindowManager) context().getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay();
    }

    @Override
    public void showPhoto(String url){
        Picasso.with(getContext())
                .load(addDimen(url))
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .placeholder(ContextCompat.getDrawable(getContext(),R.drawable.image_placeholder_500x500))
                .into(mContainer, new Callback() {
                    @Override
                    public void onSuccess() {
                        iconLike.setEnabled(true);
                        iconDislike.setEnabled(true);
                    }

                    @Override
                    public void onError() {

                        iconLike.setEnabled(true);
                        iconDislike.setEnabled(true);
                    }
                });
        Log.i(TAG, "showPhoto: "+addDimen(url));

    }

    @Override
    public void changeLabelLike(int counter) {
        String txt = counter + "";
        counterLike.setText(txt);
    }

    @Override
    public void changeLabelDislike(int counter) {
        String txt = counter + "";
        counterDislike.setText(txt);
    }

    private String addDimen(String url){
        return url + mWidth + "x" + mHeight;
    }

    @OnClick(R.id.icon_like)
    public void clickLike(){
        mQueuePresenter.likePhoto(UUID.randomUUID().hashCode());
        iconLike.setEnabled(false);
        iconDislike.setEnabled(false);
    }

    @OnClick(R.id.icon_dislike)
    public void clickDislike(){
        mQueuePresenter.dislikePhoto(UUID.randomUUID().hashCode());
        iconLike.setEnabled(false);
        iconDislike.setEnabled(false);
    }
}
