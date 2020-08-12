package com.example.demojpa;

import com.example.demojpa.Repository.PlantRepository;
import com.example.demojpa.data.Delivery;
import com.example.demojpa.data.Plant;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

@DataJpaTest
class DemojpaApplicationTests {

	@Autowired
	TestEntityManager testEntityManager;

	@Autowired
	PlantRepository plantRepository;

	@Test
	public void testPriceLessThan() {

		Plant p = testEntityManager.persist(new Plant("Foo Leaf", BigDecimal.valueOf(4.99)));
		testEntityManager.persist(new Plant("Bar Weed", BigDecimal.valueOf(5.01)));
		List<Plant> cheapPlants = plantRepository.findByPriceLessThan(BigDecimal.valueOf(5));
		Assertions.assertEquals(1, cheapPlants.size(), "Size");
		Assertions.assertEquals(p.getId(), cheapPlants.get(0).getId(), "Id");

	}

	@Test
	public void testDeliveryCompleted() {

		Plant p = testEntityManager.persist(new Plant("Foo Leaf", BigDecimal.valueOf(4.99)));
		Delivery d = testEntityManager.persist(new Delivery("Leo", "234 abc, city ", LocalDateTime.now()));

		d.setPlants(Lists.newArrayList(p));
		p.setDelivery(d);

		Assertions.assertFalse(plantRepository.deliveryCompleted(p.getId()));
		d.setCompleted(true);
		Assertions.assertTrue(plantRepository.deliveryCompleted(p.getId()));



	}


}
