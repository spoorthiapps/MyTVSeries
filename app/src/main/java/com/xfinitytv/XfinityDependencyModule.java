package com.xfinitytv;

import com.xfinity.sdk.factory.XfinitySDKFactory;
import com.xfinitytv.UIClasses.DetailsFragment;
import com.xfinitytv.UIClasses.ItemFragment;
import com.xfinitytv.dataSource.DataSourceIF;
import com.xfinitytv.dataSource.DataSourceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;

@Module(
        complete = false,
        library = true,
        injects = {
                XfinityRemoteService.class,
                GetProgramListTask.class,
                ItemFragment.class,
                DetailsFragment.class
        })
public class XfinityDependencyModule {

    public XfinityDependencyModule() {

    }

    @Provides
    public SimpleTaskRunner provideSimpleTaskRunner() {
        return new SimpleTaskRunner();
    }

    @Provides
    public XfinitySDKFactory provideXfinitySDKFactory() {
        return new XfinitySDKFactory();
    }

    @Provides
    @Singleton
    public DataSourceIF provideDataSource() {
        return new DataSourceImpl();
    }
}
