package interview.alvarez.pedro.presentation;

import javax.inject.Inject;
import javax.inject.Singleton;

import interview.alvarez.pedro.domain.executor.PostExecutionThread;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

@Singleton
public class UiThread implements PostExecutionThread {

    @Inject
    public UiThread() {}

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}