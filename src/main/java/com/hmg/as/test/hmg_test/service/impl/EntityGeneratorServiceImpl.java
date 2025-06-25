package com.hmg.as.test.hmg_test.service.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hmg.as.test.hmg_test.service.EntityGeneratorService;
import com.hmg.as.test.hmg_test.vo.ColumnInfo;

@Service
public class EntityGeneratorServiceImpl implements EntityGeneratorService {

    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    @Value("${spring.datasource.username}")
    private String dbUser;
    @Value("${spring.datasource.password}")
    private String dbPass;

    @Override
    public String generateEntityFromTable(String tableName) {
        String tableNameLower = tableName.toLowerCase(); // 핵심 변경점

        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass)) {
            DatabaseMetaData metaData = conn.getMetaData();

            // 테이블 존재 확인
            ResultSet tables = metaData.getTables(null, null, tableNameLower, new String[]{"TABLE"});
            if (!tables.next()) {
                return "// 테이블 '" + tableName + "' 이(가) 존재하지 않습니다.";
            }

            // 컬럼 조회
            List<ColumnInfo> columns = new ArrayList<>();
            ResultSet columnRs = metaData.getColumns(null, null, tableNameLower, null);
            while (columnRs.next()) {
                columns.add(new ColumnInfo(
                        columnRs.getString("COLUMN_NAME"),
                        columnRs.getString("TYPE_NAME"),
                        columnRs.getInt("COLUMN_SIZE"),
                        "NO".equals(columnRs.getString("IS_NULLABLE"))
                ));
            }

            // PK 조회
            Set<String> primaryKeys = new HashSet<>();
            ResultSet pkRs = metaData.getPrimaryKeys(null, null, tableNameLower);
            while (pkRs.next()) {
                primaryKeys.add(pkRs.getString("COLUMN_NAME"));
            }

            return buildEntityCode(tableNameLower, columns, primaryKeys);

        } catch (SQLException e) {
            return "// 오류 발생: " + e.getMessage();
        }
    }


    private String buildEntityCode(String tableName, List<ColumnInfo> columns, Set<String> primaryKeys) {
        StringBuilder sb = new StringBuilder();
        String className = toCamelCase(tableName, true);

        sb.append("import jakarta.persistence.*;\n");
        sb.append("import lombok.*;\n");
        sb.append("import java.time.*;\n");
        sb.append("import java.math.*;\n\n");

        sb.append("@Entity\n");
        sb.append("@Table(name = \"").append(tableName).append("\")\n");
        sb.append("@Getter\n@Setter\n@NoArgsConstructor\n@AllArgsConstructor\n@Builder\n");
        sb.append("public class ").append(className).append(" {\n\n");

        for (ColumnInfo col : columns) {
            String fieldName = toCamelCase(col.getName(), false);
            String javaType = mapToJavaType(col.getDbType());

            if (primaryKeys.contains(col.getName())) {
                sb.append("    @Id\n");
                sb.append("    @GeneratedValue(strategy = GenerationType.IDENTITY)\n");
            }

            sb.append("    @Column(name = \"").append(col.getName()).append("\"");
            if (col.isNotNull()) sb.append(", nullable = false");
            sb.append(")\n");

            sb.append("    private ").append(javaType).append(" ").append(fieldName).append(";\n\n");
        }

        sb.append("}");
        return sb.toString();
    }



    private String mapToJavaType(String dbType) {
        return switch (dbType.toUpperCase()) {
            case "VARCHAR", "CHAR", "TEXT" -> "String";
            case "INT", "INTEGER" -> "Integer";
            case "BIGINT" -> "Long";
            case "DATETIME", "TIMESTAMP" -> "LocalDateTime";
            case "DATE" -> "LocalDate";
            case "DECIMAL", "NUMERIC" -> "BigDecimal";
            default -> "String";
        };
    }

    private String toCamelCase(String s, boolean upperFirst) {
        String[] parts = s.toLowerCase().split("_");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            if (i == 0 && !upperFirst) sb.append(part);
            else sb.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1));
        }
        return sb.toString();
    }
}
