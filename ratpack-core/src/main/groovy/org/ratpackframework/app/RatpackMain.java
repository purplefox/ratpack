/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ratpackframework.app;

import org.ratpackframework.app.internal.ConfigLoader;
import org.vertx.java.core.Vertx;

import java.io.File;

public class RatpackMain {

  static void main(String[] args) throws Exception {
    File configFile = args.length == 0 ? new File("config.groovy") : new File(args[0]);
    if (!configFile.exists() && args.length > 0) {
      System.err.println("Config file $configFile.absolutePath does not exist");
      System.exit(1);
    }

    Config config = new ConfigLoader().load(configFile);
    new RatpackAppFactory().create(config).startAndWait();
  }

}
