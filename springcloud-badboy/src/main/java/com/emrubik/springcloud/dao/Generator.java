package com.emrubik.springcloud.dao;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.PostgreSqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class Generator {

    private static void generateByTables(String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:postgresql://10.10.30.248:5432/pudtest?currentSchema=da";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.POSTGRE_SQL)
                .setUrl(dbUrl)
                .setUsername("postgres")
                .setPassword("123456")
                .setDriverName("org.postgresql.Driver");
        dataSourceConfig.setSchemaname("da");
        dataSourceConfig.setTypeConvert(new PostgreSqlTypeConvert(){
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                if(fieldType.contains("numeric")){
                    fieldType = "decimal";
                }
                return super.processTypeConvert(fieldType);
            }
            // 自定义数据库表字段类型转换【可选】
//            @Override
//            public DbColumnType processTypeConvert(String fieldType) {
//                System.out.println("转换类型：" + fieldType);
//                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
//                return super.processTypeConvert(fieldType);
//            }
        });
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(false)
                .setDbColumnUnderline(true)//下划线命名
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        config.setActiveRecord(false)
                .setAuthor("puroc")
                .setOutputDir("/Users/puroc/git/spring-cloud-framework/springcloud-badboy/output")
                .setFileOverride(true)
                .setEnableCache(false);
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
                ).execute();
    }

    public static void main(String[] args) {
        String packageName = "com.emrubik.springcloud.dao";
        generateByTables(packageName, "da_basic_flow_t","da_basic_pressure_t","da_basic_quality_t");
    }
}
