/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package sample.chirper.chirp.impl;

import akka.actor.ActorSystem;
import akka.management.AkkaManagement$;
import akka.management.cluster.bootstrap.ClusterBootstrap$;
import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import play.Application;
import sample.chirper.chirp.api.ChirpService;

import javax.inject.Inject;

public class ChirpModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindService(ChirpService.class, ChirpServiceImpl.class);
        bind(ChirpTopic.class).to(ChirpTopicImpl.class);
        bind(ChirpRepository.class).to(ChirpRepositoryImpl.class);
        bind(OnStart.class).asEagerSingleton();
    }
}

class OnStart {
    @Inject
    public OnStart(Application application, ActorSystem actorSystem) {
        doOnStart(application, actorSystem);
    }

    private void doOnStart(Application application, ActorSystem actorSystem) {
        if (application.isProd()) {
            AkkaManagement$.MODULE$.get(actorSystem).start();
            ClusterBootstrap$.MODULE$.get(actorSystem).start();
        }
    }
}