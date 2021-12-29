/*
 * Copyright DataStax, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.popins.dev;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.popins.dev.model.Fruit;

import com.datastax.dse.driver.api.core.cql.reactive.ReactiveRow;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.quarkus.runtime.api.session.QuarkusCqlSession;

import io.smallrye.mutiny.Multi;

/** A service that manages {@link Fruit} objects, leveraging the {@link FruitDao} DAO. */
@ApplicationScoped
public class FruitService {

  
  @Inject
  private QuarkusCqlSession session;

  
  public Multi<Fruit> getAllFruits() {
	    Multi<ReactiveRow> fruits = session.executeReactive("SELECT * from fruit");
	    return fruits.onItem().transform(mapper ->{
	    	return new Fruit(mapper.getString("name"), mapper.getString("description"));
	    });
	  }
  
  public void save(Fruit fruit) {
	  	ResultSet execute = session.execute("INSERT INTO fruit (name, description) VALUES ('"+fruit.getName()+"','"+ fruit.getDescription()+"')");
	  //session.execute("INSERT INTO k1.fruit (name, description) VALUES ('kiwi', 'transamenian fruit')");
  }
} 
