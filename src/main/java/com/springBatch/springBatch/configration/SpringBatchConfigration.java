package com.springBatch.springBatch.configration;



import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.springBatch.springBatch.entitty.User;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfigration {

	@Bean
	public Job job(JobBuilderFactory jobbuilderFactory,StepBuilderFactory stepbuilderFactory,ItemReader<User> itemReader, ItemProcessor<User, User> itemProcessor,ItemWriter<User> itemWriter) {
		
		
		Step step = stepbuilderFactory.get("ETL-File-Load").<User,User>chunk(20).reader(itemReader).processor(itemProcessor).writer(itemWriter).build(); 
		
		return jobbuilderFactory.get("ETL-Load").incrementer(new RunIdIncrementer()).start(step).build();
	}
	
	@Bean
	public FlatFileItemReader<User> itemReader(@Value("${input}") Resource resource){
		
		FlatFileItemReader<User> fileItemReader =new FlatFileItemReader<>();
		fileItemReader.setResource(resource);
		fileItemReader.setName("csv-reader");
		fileItemReader.setLinesToSkip(1);
		fileItemReader.setLineMapper(lineMapper());
		
		return fileItemReader;
	}
	
	@Bean
	public LineMapper<User> lineMapper(){
		
		DefaultLineMapper<User> defaultLineMapper=new DefaultLineMapper<>();
		DelimitedLineTokenizer delimitedLineTokenizer=new DelimitedLineTokenizer();
		
		String names[]= {"id","first_name","department_name"};
		delimitedLineTokenizer.setDelimiter(",");
		delimitedLineTokenizer.setStrict(false);
		delimitedLineTokenizer.setNames(names);
		
		BeanWrapperFieldSetMapper<User> beanWrapperFieldSetMapper=new BeanWrapperFieldSetMapper<>();
		beanWrapperFieldSetMapper.setTargetType(User.class);
		
		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
		
		return defaultLineMapper;
	}
	
}
