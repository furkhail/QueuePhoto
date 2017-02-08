package interview.alvarez.pedro.dagger.components;

import javax.inject.Singleton;

import dagger.Component;
import interview.alvarez.pedro.dagger.modules.ApplicationModule;
import interview.alvarez.pedro.dagger.modules.ProjectModule;
import interview.alvarez.pedro.presentation.view.activity.MainActivity;
import interview.alvarez.pedro.presentation.view.queuephotos.QueuePhotosFragment;

@Singleton
@Component(modules = {ApplicationModule.class, ProjectModule.class})
public interface ApplicationComponent {
    void inject(MainActivity baseActivity);

    void inject(QueuePhotosFragment queuePhotosFragment);
}