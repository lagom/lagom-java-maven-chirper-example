/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package sample.chirper.friend.impl;

import akka.actor.ActorSystem;
import akka.management.AkkaManagement$;
import akka.management.cluster.bootstrap.ClusterBootstrap$;
import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import play.Application;
import sample.chirper.friend.api.FriendService;

import javax.inject.Inject;

public class FriendModule extends AbstractModule implements ServiceGuiceSupport {
  @Override
  protected void configure() {
    bindService(FriendService.class, FriendServiceImpl.class);
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