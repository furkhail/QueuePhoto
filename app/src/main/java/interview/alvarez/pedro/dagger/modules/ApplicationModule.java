package interview.alvarez.pedro.dagger.modules;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import interview.alvarez.pedro.data.executor.JobExecutor;
import interview.alvarez.pedro.domain.executor.PostExecutionThread;
import interview.alvarez.pedro.domain.executor.ThreadExecutor;
import interview.alvarez.pedro.presentation.UiThread;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.mApplication;
    }

    @Provides
    Resources provideResources() {
        return mApplication.getResources();
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UiThread uiThread) {
        return uiThread;
    }


}