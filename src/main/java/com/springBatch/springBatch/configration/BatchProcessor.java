package com.springBatch.springBatch.configration;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.springBatch.springBatch.entitty.User;
@Component
public class BatchProcessor implements ItemProcessor<User, User>{

	@Override
	public User process(User item) throws Exception {
		String department_name = item.getDepartment_name();
		item.setDepartment_name(department_name.toUpperCase());
		
		System.out.println("item changed "+item.getId());
		return item;
	}

}
