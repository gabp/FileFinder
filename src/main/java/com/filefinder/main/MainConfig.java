package com.filefinder.main;

import com.filefinder.nativehook.GlobalKeyListener;
import com.filefinder.services.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.filefinder.services",
                "com.filefinder.nativehook"})
public class MainConfig
{
    @Autowired
    @Qualifier("ManagementServiceImpl")
    private ManagementService managementService;

    @Autowired
    private GlobalKeyListener globalKeyListener;

    private static MainConfig mainConfig;

    MainConfig()
    {
        if(mainConfig == null)
        {
            mainConfig = this;
        }
    }

    public static MainConfig getInstance()
    {
        // should be created by spring
        return mainConfig;
    }

    public ManagementService getManagementService()
    {
        return managementService;
    }

    public GlobalKeyListener getGlobalKeyListener()
    {
        return globalKeyListener;
    }
}
