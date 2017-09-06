/*
 * JBoss, Home of Professional Open Source
 * Copyright 2017, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.weld.vertx.async;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.weld.vertx.AsyncReference;
import org.jboss.weld.vertx.async.BingProducer.Bing;
import org.jboss.weld.vertx.async.BlockingBarProducer.BlockingBar;

@Dependent
public class Boss {

    static final AtomicBoolean DESTROYED = new AtomicBoolean(false);

    @Inject
    AsyncReference<BlockingFoo> foo;

    @Inject
    AsyncReference<List<Boss>> unsatisfied;

    @Inject
    AsyncReference<Bing> noBing;

    @Inject
    @Juicy
    AsyncReference<Bing> juicyBing;

    @Inject
    @Juicy
    AsyncReference<BlockingBar> juicyBar;

    boolean isReadyToTest() {
        return foo.isDone() && unsatisfied.isDone() && noBing.isDone() && juicyBing.isDone() && juicyBar.isDone();
    }

    @PreDestroy
    void dispose() {
        DESTROYED.set(true);
    }

}
