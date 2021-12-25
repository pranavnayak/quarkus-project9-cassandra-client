package org.popins.dev.model;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.datastax.oss.driver.api.mapper.annotations.Update;

@Dao
public interface FruitDao {

  /**
   * Creates or updates the given {@link Fruit} in the database.
   *
   * @param fruit The {@link Fruit} to create or update. Cannot be null.
   */
  @Update
  void update(Fruit fruit);

  /**
   * Finds all the fruits.
   *
   * @return An {@linkplain PagingIterable iterable} containing all the results found in the
   *     database.
   */
  @Select
  PagingIterable<Fruit> findAll();
}
