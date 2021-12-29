
package org.popins.dev;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.popins.dev.model.Fruit;

import io.smallrye.mutiny.Multi;

/**
 * A REST resource exposing endpoints for creating and retrieving {@link Fruit} objects in the
 * database, leveraging the {@link FruitService} component.
 */
@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResource {

  @Inject FruitService fruitService;

  @GET
  public Multi<Fruit> getAll() {
    //return fruitService.getAll().stream().map(this::convertToDto).collect(Collectors.toList());
	  return fruitService.getAllFruits();
  }

  @POST
  public void add(Fruit fruit) {
    fruitService.save(fruit);
  }

}
