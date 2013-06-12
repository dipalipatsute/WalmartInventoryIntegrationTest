import static org.junit.Assert.*;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

public class WSTester {

	private String authToken = null;
	private String baseUrl = "http://localhost:8080/walmartInventoryws/services";
	/**
	 * @param args
	 */
	@Before
	public void setup(){
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(baseUrl + "/security/authToken");
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			authToken = response.getEntity(String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetItems(){
		try {			
			WebResource webResource = Client.create().resource(baseUrl+"/items");
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			// Missing security header expect 400 (bad request) response
			Assert.assertEquals(400, response.getStatus());
			
			WebResource apiRoot = Client.create().resource(baseUrl +"/items");
			List<Item> items = apiRoot.accept("application/json").header("auth", authToken).get(new GenericType<List<Item>>(){});
			Assert.assertNotNull(items);
			Assert.assertTrue(items.size()>0);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured when trying to call the web service");			
		}
	}
	
	@Test
	public void testGetItem(){
		try {			
			Item item = requestItem(1);
			Assert.assertNotNull(item);
			Assert.assertEquals(1, item.getId());
			Assert.assertEquals("TV", item.getName());
			Assert.assertEquals(4, item.getStoreItems().size());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured when trying to call the web service");			
		}
	}
	
	@Test
	public void testCreateItem(){
		Item requestItem = requestItem(1);
		requestItem.setId(0);
		requestItem.setName("testProduct");
		
		WebResource apiRoot = Client.create().resource(baseUrl +"/items/createItem");
		Item newItem = apiRoot.accept("application/json").type("application/json").header("auth", authToken).post(Item.class, requestItem);
		Assert.assertNotNull(newItem);
		Assert.assertTrue(newItem.getId()>3);
		Assert.assertEquals("testProduct", newItem.getName());
		
		// Lets remove the item created
		apiRoot = Client.create().resource(baseUrl +"/items/deleteItem/4");
		ClientResponse response = apiRoot.delete(ClientResponse.class);
		//apiRoot.header("auth", authToken).delete(Item.class);
		
	}
	
	private Item requestItem(int itemId){
		WebResource webResource = Client.create().resource(baseUrl+"/items/" + itemId);
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		// Missing security header expect 400 (bad request) response
		Assert.assertEquals(400, response.getStatus());
		
		WebResource apiRoot = Client.create().resource(baseUrl+"/items/1");
		return apiRoot.accept("application/json").header("auth", authToken).get(Item.class);		
	}
}
