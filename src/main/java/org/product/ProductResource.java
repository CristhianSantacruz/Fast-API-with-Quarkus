package org.product;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("/api")
@Transactional
public class ProductResource {

    @POST
    public Response saveProduct(@Valid ProductEntity product){
        product.persist();
        return Response.status(201).entity(product).build();
    }

    @GET
    @Path("/all")
    public Response getAllProducts(){
        return Response.ok(ProductEntity.listAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getProduct(@PathParam("id") long id){
        Optional<ProductEntity> optional = ProductEntity.findByIdOptional(id);
        return optional.isPresent() ?
                Response.ok(optional.get()).build() :
                Response.status(404).build();
    }
    @PUT
    @Path("/{id}")
    public Response updateProduct(@PathParam("id") long id, @Valid ProductEntity product){
        Optional<ProductEntity> optional = ProductEntity.findByIdOptional(id);
        if(optional.isPresent()){
            ProductEntity productUpdated  = optional.get();
            productUpdated.name = product.name;
            productUpdated.description = product.description;
            productUpdated.price = product.price;
            productUpdated.stock  = product.stock;
            productUpdated.persist();
            return Response.ok(product).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") long id){
        return ProductEntity.deleteById(id) ? Response.ok().build() : Response.status(404).build();
    }
}
