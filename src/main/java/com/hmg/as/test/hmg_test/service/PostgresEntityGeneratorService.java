package com.hmg.as.test.hmg_test.service;

import java.io.IOException;
import java.sql.SQLException;

public interface PostgresEntityGeneratorService {
	void generateEntity(String tableName) throws SQLException, IOException;
}
