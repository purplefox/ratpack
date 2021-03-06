/*
 * Copyright 2014 the original author or authors.
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

package ratpack.registry.internal;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.FluentIterable;
import com.google.common.reflect.TypeToken;
import ratpack.func.Pair;
import ratpack.registry.RegistryBacking;

import java.util.LinkedList;
import java.util.List;

/**
 * Subclass of CachingSupplierRegistry for testing CachingSupplierRegistry
 */
public class CachingBackedRegistryTestImpl extends CachingBackedRegistry {
  final List<Pair<TypeToken<?>, ? extends Supplier<?>>> supplierEntries;

  public CachingBackedRegistryTestImpl() {
    this(new LinkedList<Pair<TypeToken<?>, ? extends Supplier<?>>>());
  }

  public CachingBackedRegistryTestImpl(final List<Pair<TypeToken<?>, ? extends Supplier<?>>> supplierEntries) {
    super(new RegistryBacking() {
      @Override
      public <T> Iterable<Supplier<? extends T>> provide(final TypeToken<T> typeToken) {
        return FluentIterable.from(supplierEntries).filter(new Predicate<Pair<TypeToken<?>, ? extends Supplier<?>>>() {
          @Override
          public boolean apply(Pair<TypeToken<?>, ? extends Supplier<?>> entry) {
            return typeToken.isAssignableFrom(entry.getLeft());
          }
        }).transform(new Function<Pair<TypeToken<?>, ? extends Supplier<?>>, Supplier<? extends T>>() {
          @Override
          @SuppressWarnings("unchecked")
          public Supplier<? extends T> apply(Pair<TypeToken<?>, ? extends Supplier<?>> input) {
            return (Supplier<? extends T>) input.getRight();
          }
        });
      }
    });
    this.supplierEntries = supplierEntries;
  }

  public void register(Object instance) {
    register(TypeToken.of(instance.getClass()), instance);
  }

  public void register(TypeToken<?> type, Object instance) {
    register(type, Suppliers.ofInstance(instance));
  }

  public void register(TypeToken<?> type, Supplier<?> supplier) {
    supplierEntries.add(Pair.<TypeToken<?>, Supplier<?>>of(type, supplier));
  }
}