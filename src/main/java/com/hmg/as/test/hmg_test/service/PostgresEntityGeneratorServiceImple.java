package com.hmg.as.test.hmg_test.service;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

@Service
public class PostgresEntityGeneratorServiceImple implements PostgresEntityGeneratorService {

    private final DataSource dataSource;

    public PostgresEntityGeneratorServiceImple(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void generateEntity(String tableName) throws SQLException, IOException {
    	String projectPath = System.getProperty("user.dir"); // 프로젝트 루트
    	String outputPath = projectPath + "/src/main/java/com/hmg/as/test/hmg_test/entity/";
    	
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();
            
         // 테이블 존재 여부 확인
            try (ResultSet tables = metaData.getTables(null, null, tableName, new String[]{"TABLE"})) {
                if (!tables.next()) {
                	throw new IllegalArgumentException("❌ 테이블이 존재하지 않습니다: " + tableName);
                }
            }
            
            ResultSet columns = metaData.getColumns(null, null, tableName, null);
            ResultSet pk = metaData.getPrimaryKeys(null, null, tableName);

            StringBuilder sb = new StringBuilder();
            sb.append("package com.hmg.as.test.hmg_test.entity;\n\n");
         // 기본 JPA 어노테이션 import
            sb.append("import jakarta.persistence.*;\n");

            // 사용될 가능성이 있는 타입 import
            sb.append("import jakarta.persistence.Entity;\n");
            sb.append("import jakarta.persistence.Id;\n");
            sb.append("import jakarta.persistence.Table;\n\n");
            
            sb.append("@Entity\n");
            sb.append(String.format("@Table(name = \"%s\")\n", tableName));
            sb.append(String.format("public class %s {\n\n", capitalize(tableName)));

            String pkColumn = "";
            if (pk.next()) {
                pkColumn = pk.getString("COLUMN_NAME");
            }

            while (columns.next()) {
                String colName = columns.getString("COLUMN_NAME");
                String type = columns.getString("TYPE_NAME").toLowerCase(Locale.ROOT);
                String javaType = mapToJavaType(type);

                if (colName.equals(pkColumn)) {
                    sb.append("    @Id\n");
                }

                sb.append(String.format("    @Column(name = \"%s\")\n", colName));
                sb.append(String.format("    private %s %s;\n\n", javaType, colName));
            }

            sb.append("}");

            String fileName = capitalize(tableName) + ".java";
            try (FileWriter writer = new FileWriter(outputPath+ fileName)) {
                writer.write(sb.toString());
                System.out.println("Entity class generated: " + fileName);
            }
        }
    }

    private static String mapToJavaType(String dbType) {
        return switch (dbType) {
            case "varchar", "text" -> "String";
            case "int4", "serial", "integer" -> "Integer";
            case "int8", "bigint" -> "Long";
            case "bool", "boolean" -> "Boolean";
            case "timestamp", "timestamptz" -> "LocalDateTime";
            case "date" -> "LocalDate";
            case "numeric", "decimal" -> "BigDecimal";
            default -> "String";
        };
    }

    private static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
