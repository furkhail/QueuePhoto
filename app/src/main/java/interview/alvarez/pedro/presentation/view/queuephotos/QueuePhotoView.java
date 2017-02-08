package interview.alvarez.pedro.presentation.view.queuephotos;

import interview.alvarez.pedro.presentation.view.LoadDataView;

public interface QueuePhotoView extends LoadDataView {

    void showPhoto(String url);

    void changeLabelLike(int counter);

    void changeLabelDislike(int counter);
}
