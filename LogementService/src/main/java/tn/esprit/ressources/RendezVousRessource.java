package tn.esprit.ressources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import tn.esprit.business.RendezVousBusiness;
import tn.esprit.entites.RendezVous;

@Path("/rendezvous")
public class RendezVousRessource {
	
	public static RendezVousBusiness RB = new RendezVousBusiness()  ;
	
	//methode1
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ajouterRendezVous (RendezVous RD) {
		System.out.println(RB.addRendezVous(RD));
		if (RB.addRendezVous(RD))

			return Response.status(Status.CREATED).build();
		return Response.status(Status.NOT_FOUND).build();	
	}
	
	
	//methode2

@GET
@Produces(MediaType.APPLICATION_JSON)
 public Response getListRendezVous() {
	
	List<RendezVous> LRV = RB.getListeRendezVous();
	System.out.print(LRV);
	
	if (LRV.size()!=0)
		return Response.status(Status.OK).entity("").build();
	
	
	return Response.status(Status.NOT_FOUND).build();	
					
	
}
	


//methode4
@GET
@Path("{id}")
@Produces(MediaType.APPLICATION_JSON)
public Response getRendezVousbyID(@PathParam("id") int id) {
	RendezVous rendezVous = RB.getRendezVousById(id);
	if (rendezVous != null ) {
		
		return Response.status(Status.OK).entity(rendezVous).build();
	}
	return Response.status(Status.NO_CONTENT).build(); }
	




//methode5
@DELETE
@Path("{id}")
@Produces(MediaType.APPLICATION_JSON)
public Response supprimerRendezVous(@PathParam("id") int id) {
	
	if (RB.deleteRendezVous(id)) {
		return Response.status(Status.OK).build();}
	
	return Response.status(Status.NO_CONTENT).build();}


@PUT
@Path("{id}")
@Consumes(MediaType.APPLICATION_JSON)
public Response modifierRendezVous(@PathParam("id") int id, RendezVous RD) {
	if (RB.updateRendezVous(id,RD)) {
		return Response.status(Status.OK).build();
	}
	return Response.status(Status.NOT_FOUND).build();
}


/*
//methode3
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response getListeRendezVous(@QueryParam(value = "refLogement") String refLogement) {
	List<RendezVous> ListeRD = new ArrayList<RendezVous>();
	if (refLogement == null) {
		ListeRD = RB.getListeRendezVous();

	} else {
		ListeRD = RB.getListeRendezVousByLogementReference(Integer.parseInt(refLogement));

	}

	if (ListeRD.size() != 0) {
		GenericEntity<List<RendezVous>> result = new GenericEntity<List<RendezVous>>(ListeRD) {};
		return Response.status(Status.OK).entity(result).build();
	}
	return Response.status(Status.NO_CONTENT).build();
	}
*/

}


	



