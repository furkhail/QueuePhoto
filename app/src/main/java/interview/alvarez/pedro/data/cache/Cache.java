package interview.alvarez.pedro.data.cache;

import io.reactivex.Observable;

public interface Cache<T> {

    void clearMemory();

    Observable<T> memory();

    Observable<T> disk();

    Observable<T> network();

}
