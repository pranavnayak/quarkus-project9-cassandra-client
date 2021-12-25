package org.popins.dev.reactive;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.popins.dev.model.Fruit;
import org.popins.dev.model.FruitDto;

@Path("/reactive-fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReactiveFruitResource {

  @Inject ReactiveFruitService service;

  @GET
  public Multi<FruitDto> getAll() {
    return service.getAll().map(this::convertToDto);
  }

  @POST
  public Uni<Void> add(FruitDto fruitDto) {
    return service.add(convertFromDto(fruitDto));
  }

  private FruitDto convertToDto(Fruit fruit) {
    return new FruitDto(fruit.getName(), fruit.getDescription());
  }

  private Fruit convertFromDto(FruitDto fruitDto) {
    return new Fruit(fruitDto.getName(), fruitDto.getDescription());
  }
}
