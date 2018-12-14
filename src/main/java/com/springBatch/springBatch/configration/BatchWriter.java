package com.springBatch.springBatch.configration;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springBatch.springBatch.doa.DoaInterface;
import com.springBatch.springBatch.entitty.User;
@Component
public class BatchWriter implements ItemWriter<User>{

	@Autowired
	private DoaInterface repo;
	
	@Override
	public void write(List<? extends User> items) throws Exception {
		System.out.println("writing ....");
		repo.saveAll(items);
		
	}

}
