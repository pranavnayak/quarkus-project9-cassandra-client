
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
