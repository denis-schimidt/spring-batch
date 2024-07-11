package com.schimidt.spring.batch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBatchTest
@SpringBootTest
class BatchApplicationTests {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void shouldExecuteImportCoffeeTaskSuccessfully(@Autowired Job job) throws Exception {
		this.jobLauncherTestUtils.setJob(job);
		var jobExecution = jobLauncherTestUtils.launchJob();
		Assertions.assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());

		// Verify the data in the database
		var count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM coffee", Integer.class);
		Assertions.assertTrue(count > 0, "There should be at least one record in the database");
	}
}
