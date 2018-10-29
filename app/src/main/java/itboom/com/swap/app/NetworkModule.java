package itboom.com.swap.app;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    //todo add base url
    private static final String BASE_URL = "http://192.168.174.1:3000/";

    @Provides
    public Retrofit getRetrofitClient(OkHttpClient client, GsonConverterFactory converterFactory, RxJava2CallAdapterFactory adapterFactory){
        return new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(adapterFactory)
                .build();
    }

    @Provides
    public OkHttpClient getHttpClient(){
        return new OkHttpClient()
                .newBuilder()
                .build();
    }

    @Provides
    public GsonConverterFactory getGsonConverterFactory(){
        return GsonConverterFactory.create();
    }

    @Provides
    public RxJava2CallAdapterFactory getRxJavaCallAdapterFactory(){
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    public SwapAPI getApi(Retrofit retrofit){
        return retrofit.create(SwapAPI.class);
    }
}

