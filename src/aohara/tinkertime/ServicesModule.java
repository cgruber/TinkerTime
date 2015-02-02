package aohara.tinkertime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module class ServicesModule {
  @Provides @Singleton Gson gson() {
    return new GsonBuilder().setPrettyPrinting().create();
  }
}
