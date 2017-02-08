package interview.alvarez.pedro.data.cache;

import com.github.pwittchen.prefser.library.Prefser;
import com.github.pwittchen.prefser.library.TypeToken;

import javax.inject.Inject;

import interview.alvarez.pedro.MyApp;

public class DiskCache {

    private Prefser library;

    @Inject
    public DiskCache() {
        library = new Prefser(MyApp.getContext());
    }

    public void clear(){
        library.clear();
    }

    public <T> void put(String key, T data){
        library.put(key,data);
    }

    public <T> T get(String key, Class<T> classOf, T defaultValue){
        return library.get(key, classOf, defaultValue);
    }

    public <T> T get(String key, TypeToken<T> typeToken){
        return library.get(key, typeToken, null);
    }

//    public <T> Observable<T> get(String key){
//        return library.getAndObserve(key, new TypeToken<T>() {
//            @Override
//            public Type getType() {
//                return super.getType();
//            }
//        }, null);
//    }
}
