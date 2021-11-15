package com.supernova.mysqlmockdemo.embedded;


import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.MysqldConfig;
import com.wix.mysql.config.SchemaConfig;
import lombok.extern.slf4j.Slf4j;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.ScriptResolver.classPathScript;
import static com.wix.mysql.config.Charset.UTF8;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.config.SchemaConfig.aSchemaConfig;
import static com.wix.mysql.distribution.Version.v8_latest;


@Slf4j
public class EmbeddedMysqlManager {

    private static EmbeddedMysqlManager instance;
    // use "root"is NOT allowed.
    private final String DB_USER = "testRoot";
    private final String DB_PASSWD = "testPwd";
    private EmbeddedMysql mysqld;
    private SchemaConfig schemaConfig;
    private final int dbPort = 4408;
    private final String dbSchemaName = "tiger_db";

    private EmbeddedMysqlManager() {
        startDatabase();
    }

    public static EmbeddedMysqlManager getInstance() {
        if (instance == null) {
            instance = new EmbeddedMysqlManager();
        }
        return instance;
    }

    public void reloadSchema() {
        mysqld.reloadSchema(schemaConfig);
    }

    private void startDatabase() {
        MysqldConfig dbConfig = aMysqldConfig(v8_latest).withCharset(UTF8).withPort(dbPort).withUser(DB_USER, DB_PASSWD)
                .build();

        schemaConfig = aSchemaConfig(dbSchemaName)
                .withScripts(classPathScript("sql/mysql/test-only/tiger_db-20170517.sql")).withCharset(UTF8).build();

        log.info("embedded mysql starting ...");

        // start database now.
        mysqld = anEmbeddedMysql(dbConfig).addSchema(schemaConfig).start();

        log.info("embedded mysql started.");

    }

    String getDbUrl() {
        return "jdbc:mysql://localhost:" + dbPort + "/" + dbSchemaName;
    }

    String getDbUser() {
        return DB_USER;
    }

    String getDbPassword() {
        return DB_PASSWD;
    }
}
