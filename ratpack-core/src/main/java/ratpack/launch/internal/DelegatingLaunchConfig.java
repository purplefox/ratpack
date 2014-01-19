/*
 * Copyright 2013 the original author or authors.
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

package ratpack.launch.internal;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.EventLoopGroup;
import ratpack.api.Nullable;
import ratpack.file.FileSystemBinding;
import ratpack.handling.Context;
import ratpack.launch.HandlerFactory;
import ratpack.launch.LaunchConfig;

import javax.inject.Provider;
import javax.net.ssl.SSLContext;
import java.net.InetAddress;
import java.net.URI;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class DelegatingLaunchConfig implements LaunchConfig, LaunchConfigInternal {

  private final LaunchConfig launchConfig;

  public DelegatingLaunchConfig(LaunchConfig launchConfig) {
    this.launchConfig = launchConfig;
  }

  @Override
  public FileSystemBinding getBaseDir() {
    return launchConfig.getBaseDir();
  }

  @Override
  public HandlerFactory getHandlerFactory() {
    return launchConfig.getHandlerFactory();
  }

  @Override
  public int getPort() {
    return launchConfig.getPort();
  }

  @Override
  @Nullable
  public InetAddress getAddress() {
    return launchConfig.getAddress();
  }

  @Override
  public boolean isReloadable() {
    return launchConfig.isReloadable();
  }

  @Override
  public int getMainThreads() {
    return launchConfig.getMainThreads();
  }

  @Override
  public EventLoopGroup getEventLoopGroup() {
    return launchConfig.getEventLoopGroup();
  }

  @Override
  public ExecutorService getBackgroundExecutorService() {
    return launchConfig.getBackgroundExecutorService();
  }

  @Override
  public ByteBufAllocator getBufferAllocator() {
    return launchConfig.getBufferAllocator();
  }

  @Override
  public URI getPublicAddress() {
    return launchConfig.getPublicAddress();
  }

  @Override
  public List<String> getIndexFiles() {
    return launchConfig.getIndexFiles();
  }

  @Override
  @Nullable
  public SSLContext getSSLContext() {
    return launchConfig.getSSLContext();
  }

  @Override
  public String getOther(String key, String defaultValue) {
    return launchConfig.getOther(key, defaultValue);
  }

  @Override
  public int getMaxContentLength() {
    return launchConfig.getMaxContentLength();
  }

  @Override
  public Provider<Context> getContextProvider() {
    return launchConfig.getContextProvider();
  }

  @Override
  public ThreadLocal<Context> getContextThreadLocal() {
    return ((LaunchConfigInternal) launchConfig).getContextThreadLocal();
  }

}
