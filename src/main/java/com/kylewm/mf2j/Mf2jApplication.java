package com.kylewm.mf2j;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.kylewm.mf2j.Mf2jConfiguration;

public class Mf2jApplication extends Application<Mf2jConfiguration> {
    public static void main(String[] args) throws Exception {
        new Mf2jApplication().run(args);
    }

    @Override
    public String getName() {
        return "mf2j";
    }

    @Override
    public void initialize(Bootstrap<Mf2jConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(Mf2jConfiguration configuration,
                    Environment environment) {
        // nothing to do yet
        environment.jersey().register(ParsingResource.class);
    }

}